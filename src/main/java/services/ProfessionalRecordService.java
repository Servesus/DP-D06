
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProfessionalRecordRepository;
import security.UserAccount;
import domain.Actor;
import domain.HandyWorker;
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfessionalRecordService {

	//Managed repository
	@Autowired
	private ProfessionalRecordRepository	professionalRecordRepository;
	//Supporting repositories
	@Autowired
	private ActorService					actorService;
	@Autowired
	private HandyWorkerService				handyWorkerService;


	//Simple CRUD methods
	public ProfessionalRecord create() {
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		final ProfessionalRecord professionalRecord = new ProfessionalRecord();
		return professionalRecord;
	}

	public Collection<ProfessionalRecord> findAll() {
		return this.professionalRecordRepository.findAll();
	}

	public ProfessionalRecord findOne(final int professionalRecordID) {
		return this.professionalRecordRepository.findOne(professionalRecordID);
	}

	public ProfessionalRecord save(final ProfessionalRecord professionalRecord) {
		ProfessionalRecord result;
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		final HandyWorker hw = this.handyWorkerService.findOne(this.actorService.getActorLogged().getId());
		if (professionalRecord.getId() == 0) {
			result = this.professionalRecordRepository.save(professionalRecord);
			hw.getCurricula().getProfessionalRecord().add(result);
		} else
			result = this.professionalRecordRepository.save(professionalRecord);
		return result;

	}

	public void delete(final ProfessionalRecord professionalRecord) {
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		Assert.notNull(professionalRecord);
		Assert.isTrue(professionalRecord.getId() != 0);
		hw.getCurricula().getProfessionalRecord().remove(professionalRecord);
		this.professionalRecordRepository.delete(professionalRecord);
	}

	//Other business methods

}
