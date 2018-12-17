
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.FixUpTask;
import domain.HandyWorker;

@Service
@Transactional
public class ApplicationService {

	//Managed respositories
	@Autowired
	private ApplicationRepository	applicationRepository;

	@Autowired
	private FixUpTaskService		fixUpTaskService;

	@Autowired
	private HandyWorkerService		handyWorkerService;

	@Autowired
	private ActorService			actorService;


	public Application create(final int fixUpTaskId) {
		Application result;
		Collection<String> customerComments;
		Collection<String> hwComments;
		FixUpTask fixUpTask;
		HandyWorker handyWorker;
		UserAccount userAccount;

		userAccount = this.actorService.getActorLogged().getUserAccount();

		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority()
				.equals("HANDYWORKER"));

		fixUpTask = this.fixUpTaskService.findOne(fixUpTaskId);
		handyWorker = this.handyWorkerService.findOne(this.actorService.getActorLogged().getId());

		final Date moment = new Date();
		final Integer status = 0;
		customerComments = new ArrayList<String>();
		hwComments = new ArrayList<String>();

		result = new Application();

		result.setMoment(moment);
		result.setStatus(status);
		result.setCustomerComments(customerComments);
		result.setHwComments(hwComments);
		result.setFixUpTask(fixUpTask);
		result.setHandyWorker(handyWorker);

		return result;
	}

	public Collection<Application> findAll() {
		Collection<Application> result;

		result = this.applicationRepository.findAll();

		return result;
	}

	public Application findOne(final int applicationId) {
		Application result;

		result = this.applicationRepository.findOne(applicationId);

		return result;
	}

	public Application save(final Application application) {
		Assert.notNull(application);
		UserAccount userAccount;
		FixUpTask fixUpTask;
		HandyWorker handyWorker;

		userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().iterator().next()
				.getAuthority().equals("CUSTOMER") || 
				userAccount.getAuthorities().iterator().next()
				.getAuthority().equals("HANDYWORKER"));

		Application result;

		fixUpTask = application.getFixUpTask();
		handyWorker = application.getHandyWorker();

		result = this.applicationRepository.save(application);

		if (application.getId() == 0) {
			final Collection<Application> apps = fixUpTask.getApplications();
			apps.add(result);
			fixUpTask.setApplications(apps);
			this.fixUpTaskService.save(fixUpTask);
		}

		if (application.getId() == 0) {
			final Collection<Application> apps = handyWorker.getApplications();
			apps.add(result);
			handyWorker.setApplications(apps);
			this.handyWorkerService.save(handyWorker);
		}

		return result;
	}

}
