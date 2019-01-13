
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorserRecordRepository;
import security.UserAccount;
import domain.Actor;
import domain.EndorserRecord;
import domain.HandyWorker;

@Service
@Transactional
public class EndorserRecordService {

	//Managed repository
	@Autowired
	private EndorserRecordRepository	endorserRecordRepository;
	//Supporting repositories
	@Autowired
	private ActorService				actorService;
	@Autowired
	private HandyWorkerService			handyWorkerService;


	//Simple CRUD methods
	public EndorserRecord create() {
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		final EndorserRecord endorserRecord = new EndorserRecord();
		return endorserRecord;
	}

	public Collection<EndorserRecord> findAll() {
		return this.endorserRecordRepository.findAll();
	}

	public EndorserRecord findOne(final int endorserRecordID) {
		return this.endorserRecordRepository.findOne(endorserRecordID);
	}

	public EndorserRecord save(final EndorserRecord endorserRecord) {
		EndorserRecord result;
		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		if (endorserRecord.getId() == 0) {
			result = this.endorserRecordRepository.save(endorserRecord);
			hw.getCurricula().getEndorserRecord().add(result);
		} else
			result = this.endorserRecordRepository.save(endorserRecord);
		return result;
	}

	public void delete(final EndorserRecord endorserRecord) {
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		Assert.isTrue(endorserRecord.getId() != 0);
		hw.getCurricula().getEndorserRecord().remove(endorserRecord);
		this.endorserRecordRepository.delete(endorserRecord);
	}

	//Other business methods

}
