
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EducationalRecordRepository;
import security.UserAccount;
import domain.Actor;
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


	//Simple CRUD methods
	public EducationalRecord create() {
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		final EducationalRecord educationalRecord = new EducationalRecord();
		return educationalRecord;
	}

	public Collection<EducationalRecord> findAll() {
		return this.educationalRecordRepository.findAll();
	}

	public EducationalRecord findOne(final int educationalRecordID) {
		return this.educationalRecordRepository.findOne(educationalRecordID);
	}

	public EducationalRecord save(final EducationalRecord educationalRecord) {
		EducationalRecord result;
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		final HandyWorker hw = this.handyWorkerService.findOne(this.actorService.getActorLogged().getId());
		if (educationalRecord.getId() == 0) {
			result = this.educationalRecordRepository.saveAndFlush(educationalRecord);
			hw.getCurricula().getEducationalRecord().add(result);
		} else
			result = this.educationalRecordRepository.save(educationalRecord);
		return result;
	}

	public void delete(final EducationalRecord educationalRecord) {
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		Assert.isTrue(educationalRecord.getId() != 0);
		hw.getCurricula().getEducationalRecord().remove(educationalRecord);

		this.educationalRecordRepository.delete(educationalRecord);
	}

	//Other business methods

}
