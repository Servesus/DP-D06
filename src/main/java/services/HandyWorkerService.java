
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.HandyWorkerRepository;
import security.Authority;
import security.UserAccount;
import security.UserAccountService;
import domain.Application;
import domain.Box;
import domain.Complaint;
import domain.Curricula;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Phase;
import domain.Profile;

@Service
@Transactional
public class HandyWorkerService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private HandyWorkerRepository	handyWorkerRepository;
	// Supporting services ----------------------------------------------------
	@Autowired
	private FinderService			finderService;
	@Autowired
	private BoxService				boxService;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private CurriculaService		curriculaService;
	@Autowired
	private UserAccountService		userAccountService;


	// Constructors -----------------------------------------------------------
	public HandyWorkerService() {
		super();
	}
	// Simple CRUD methods ----------------------------------------------------

	public HandyWorker create() {
		HandyWorker result;
		UserAccount user;
		Authority aut;
		Collection<Authority> auts;
		Finder finder;
		final Curricula curricula;
		final Collection<Application> apps = new ArrayList<Application>();
		final Collection<Phase> phases = new ArrayList<Phase>();
		final Collection<Profile> profiles = new ArrayList<Profile>();

		auts = new ArrayList<Authority>();
		aut = new Authority();
		user = new UserAccount();
		result = new HandyWorker();
		finder = this.finderService.create();
		curricula = this.curriculaService.create();

		aut.setAuthority(Authority.HANDYWORKER);
		auts.add(aut);
		user.setAuthorities(auts);

		result.setUserAccount(user);
		result.setIsBanned(false);
		result.setIsSuspicious(false);
		result.setFinder(finder);
		result.setApplications(apps);
		result.setPhases(phases);
		result.setCurricula(curricula);
		result.setProfiles(profiles);
		return result;
	}

	public Collection<HandyWorker> findAll() {
		Collection<HandyWorker> result;

		result = this.handyWorkerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public HandyWorker findOne(final int handyWorkerId) {
		HandyWorker result;

		result = this.handyWorkerRepository.findOne(handyWorkerId);
		Assert.notNull(result);

		return result;
	}

	public HandyWorker save(final HandyWorker handyWorker) {
		Assert.notNull(handyWorker);

		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String res = encoder.encodePassword(handyWorker.getUserAccount().getPassword(), null);
		handyWorker.getUserAccount().setPassword(res);
		final HandyWorker result;

		if (handyWorker.getId() == 0) {
			final UserAccount userAccount = this.userAccountService.save(handyWorker.getUserAccount());
			handyWorker.setUserAccount(userAccount);
			Collection<Box> boxSystem;
			boxSystem = this.boxService.createSystemBoxes();
			handyWorker.setBoxes(boxSystem);
			Finder finder;
			finder = handyWorker.getFinder();
			finder = this.finderService.save(finder);
			Curricula curricula;
			curricula = handyWorker.getCurricula();
			curricula = this.curriculaService.save(curricula);
			handyWorker.setCurricula(curricula);
			handyWorker.setFinder(finder);
		}
		result = this.handyWorkerRepository.save(handyWorker);
		return result;
	}

	public void delete(final HandyWorker handyWorker) {
		Assert.notNull(handyWorker);
		Assert.isTrue(handyWorker.getId() != 0);

		this.handyWorkerRepository.delete(handyWorker);
	}
	// Other business methods -------------------------------------------------

	public List<Application> showApplicationsByHandyWorker() {
		final Integer id = this.actorService.getActorLogged().getId();
		return this.handyWorkerRepository.getApplicationsById(id);
	}
	public Collection<FixUpTask> showFixUpTasksInFinder() {
		final Integer id = this.actorService.getActorLogged().getId();
		return this.handyWorkerRepository.fixUpTasksInFinder(id);
	}
	public Collection<Complaint> showComplaintsByHW() {
		final Collection<Complaint> result = new ArrayList<Complaint>();
		final Integer id = this.actorService.getActorLogged().getId();
		final List<Application> applications = this.handyWorkerRepository.getAcceptedApplicationsByHW(id);
		for (final Application a : applications)
			result.addAll(a.getFixUpTask().getComplaints());
		return result;
	}
}
