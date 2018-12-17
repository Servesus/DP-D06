
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EducationalRecordRepository;
import security.UserAccount;
import domain.EducationalRecord;
import domain.HandyWorker;

@Service
@Transactional
public class EducationalRecordService {

	//Managed repository
	@Autowired
	private EducationalRecordRepository	educationalRecordRepository;
	//Supporting repositories
	@Autowired
	private ActorService				actorService;
	@Autowired
	private HandyWorkerService			handyWorkerService;
	@Autowired
	private CurriculaService			curriculaService;


	//Simple CRUD methods
	public EducationalRecord create() {
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		final EducationalRecord educationalRecord = new EducationalRecord();
		educationalRecord.setComments(new ArrayList<String>());
		return educationalRecord;
	}

	public Collection<EducationalRecord> findAll() {
		return this.educationalRecordRepository.findAll();
	}

	public EducationalRecord findOne(final int educationalRecordID) {
		return this.educationalRecordRepository.findOne(educationalRecordID);
	}

	public EducationalRecord save(final EducationalRecord educationalRecord) {
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		final EducationalRecord result = this.educationalRecordRepository.save(educationalRecord);
		Assert.notNull(result);
		final HandyWorker hw = this.handyWorkerService.findOne(this.actorService.getActorLogged().getId());
		if (result.getId() == 0) {
			final List<EducationalRecord> eR = (List<EducationalRecord>) hw.getCurricula().getEducationalRecord();
			eR.add(result);
			hw.getCurricula().setEducationalRecord(eR);
			this.curriculaService.save(hw.getCurricula());
		} else {

			final List<EducationalRecord> eR = (List<EducationalRecord>) hw.getCurricula().getEducationalRecord();
			eR.add(result);
			hw.getCurricula().setEducationalRecord(eR);
			this.curriculaService.save(hw.getCurricula());
		}
		return result;
	}

	public void delete(final EducationalRecord educationalRecord) {
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		Assert.isTrue(educationalRecord.getId() != 0);
		this.educationalRecordRepository.delete(educationalRecord);
	}

	//Other business methods

}
