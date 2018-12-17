
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PersonalRecordRepository;
import security.UserAccount;
import domain.PersonalRecord;

@Service
@Transactional
public class PersonalRecordService {

	//Managed repository
	@Autowired
	private PersonalRecordRepository	personalRecordRepository;
	//Supporting repositories
	@Autowired
	private ActorService				actorService;


	//Simple CRUD methods
	public PersonalRecord create() {
		final PersonalRecord personalRecord = new PersonalRecord();
		return personalRecord;
	}

	public Collection<PersonalRecord> findAll() {
		return this.personalRecordRepository.findAll();
	}

	public PersonalRecord findOne(final int personalRecordID) {
		return this.personalRecordRepository.findOne(personalRecordID);
	}

	public PersonalRecord save(final PersonalRecord personalRecord) {
		//UserAccount userAccount;
		//userAccount = this.actorService.getActorLogged().getUserAccount();
		//Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		final PersonalRecord result;
		//Assert.isNull(result);
		//final HandyWorker hw = this.handyWorkerService.findOne(this.actorService.getActorLogged().getId());
		//if (result.getId() == 0) {
		//hw.getCurricula().setPersonalRecord(result);
		//this.curriculaService.save(hw.getCurricula());
		/*
		 * } else {
		 * Assert.isTrue(hw.getCurricula().getPersonalRecord().getId() == result.getId());
		 * hw.getCurricula().setPersonalRecord(result);
		 * this.curriculaService.save(hw.getCurricula());
		 * }
		 */
		result = this.personalRecordRepository.save(personalRecord);
		return result;
	}
	public void delete(final PersonalRecord personalRecord) {
		Assert.isTrue(personalRecord.getId() != 0);
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		this.personalRecordRepository.delete(personalRecord);
	}

	//Other business methods

}
