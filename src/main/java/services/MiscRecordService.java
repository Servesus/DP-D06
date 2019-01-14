
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MiscRecordRepository;
import security.UserAccount;
import domain.Actor;
import domain.HandyWorker;
import domain.MiscRecord;

@Service
@Transactional
public class MiscRecordService {

	//Managed repository
	@Autowired
	private MiscRecordRepository	miscRecordRepository;
	//Supporting repositories
	@Autowired
	private ActorService			actorService;
	@Autowired
	private HandyWorkerService		handyWorkerService;


	//Simple CRUD methods
	public MiscRecord create() {
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		final MiscRecord miscRecord = new MiscRecord();
		return miscRecord;
	}

	public Collection<MiscRecord> findAll() {
		return this.miscRecordRepository.findAll();
	}

	public MiscRecord findOne(final int miscRecordID) {
		return this.miscRecordRepository.findOne(miscRecordID);
	}

	public MiscRecord save(final MiscRecord miscRecord) {
		MiscRecord result;
		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		if (miscRecord.getId() == 0) {
			result = this.miscRecordRepository.save(miscRecord);
			hw.getCurricula().getMiscRecord().add(result);

		} else
			result = this.miscRecordRepository.save(miscRecord);
		return result;
	}

	public void delete(final MiscRecord miscRecord) {
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		Assert.isTrue(this.handyWorkerService.findOne(this.actorService.getActorLogged().getId()).getCurricula() != null);
		Assert.isTrue(miscRecord.getId() != 0);
		hw.getCurricula().getMiscRecord().remove(miscRecord);

		this.miscRecordRepository.delete(miscRecord);
	}

	//Other business methods

}
