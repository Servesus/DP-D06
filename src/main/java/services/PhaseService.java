
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PhaseRepository;
import security.UserAccount;
import domain.FixUpTask;
import domain.Phase;

@Service
@Transactional
public class PhaseService {

	//Managed repository
	@Autowired
	private PhaseRepository		phaseRepository;

	@Autowired
	private FixUpTaskService	fixUpTaskService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	//Simple CRUD methods
	public Phase create(final int idFixUp) {
		Phase result;
		result = new Phase();
		final FixUpTask fixUpTask = this.fixUpTaskService.findOne(idFixUp);
		result.setFixUpTask(fixUpTask);
		return result;
	}

	public Phase save(final Phase phase) {
		Assert.notNull(phase);
		Phase result;
		Date currentMoment;
		currentMoment = new Date();
		Assert.isTrue(phase.getStartMoment().after(currentMoment));
		Assert.isTrue(phase.getStartMoment().before(phase.getFinishMoment()));
		UserAccount userAccount;

		userAccount = this.actorService.getActorLogged().getUserAccount();

		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		result = this.phaseRepository.save(phase);
		if (phase.getId() == 0)
			phase.getFixUpTask().getPhases().add(result);

		return result;
	}
	public List<Phase> findAll() {
		return this.phaseRepository.findAll();
	}

	public Phase findOne(final Integer phaseId) {
		return this.phaseRepository.findOne(phaseId);
	}

	public void delete(final Phase phase) {
		Assert.notNull(phase);
		Assert.isTrue(phase.getId() != 0);
		phase.getFixUpTask().getPhases().remove(phase);
		this.phaseRepository.delete(phase);
	}
	public Collection<Phase> phasesByFixUpTask(final int idFixUpTask) {
		final Collection<Phase> result = this.phaseRepository.findPhasesByFixUpTaskId(idFixUpTask);
		return result;
	}
}
