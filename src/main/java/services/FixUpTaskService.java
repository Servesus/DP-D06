
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FixUpTaskRepository;
import security.UserAccount;
import domain.Application;
import domain.Complaint;
import domain.Customer;
import domain.FixUpTask;
import domain.Phase;

@Service
@Transactional
public class FixUpTaskService {

	@Autowired
	private FixUpTaskRepository	fixUpTaskRepository;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private CustomerService		customerService;


	public FixUpTask create() {
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		final Customer c = this.customerService.findOne(this.actorService.getActorLogged().getId());
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("CUSTOMER"));
		final FixUpTask result = new FixUpTask();
		result.setTicker(CurriculaService.generadorDeTickers());
		final Collection<Application> applications = new ArrayList<Application>();
		final Collection<Complaint> complaints = new ArrayList<Complaint>();
		final Collection<Phase> phases = new ArrayList<Phase>();
		result.setApplications(applications);
		result.setComplaints(complaints);
		result.setCustomer(c);
		result.setPhases(phases);
		return result;
	}

	public Collection<FixUpTask> findAll() {
		Collection<FixUpTask> result;
		result = this.fixUpTaskRepository.findAll();
		return result;
	}
	public FixUpTask findOne(final int fixUpTaskId) {
		FixUpTask result;
		Assert.notNull(this.fixUpTaskRepository);
		result = this.fixUpTaskRepository.findOne(fixUpTaskId);
		return result;

	}

	public FixUpTask save(final FixUpTask fixUpTask) {

		FixUpTask result;
		UserAccount userAccount;

		userAccount = this.actorService.getActorLogged().getUserAccount();

		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("CUSTOMER") || userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));

		Assert.notNull(fixUpTask);
		result = this.fixUpTaskRepository.save(fixUpTask);
		return result;
	}
	public void delete(final FixUpTask fixUpTask) {

		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("CUSTOMER"));
		Assert.notNull(fixUpTask);
		assert fixUpTask.getId() != 0;
		Assert.isTrue(this.fixUpTaskRepository.exists(fixUpTask.getId()));
		this.fixUpTaskRepository.delete(fixUpTask);
	}

	public Collection<FixUpTask> getCustomerFixUpTasks() {
		final Customer c = this.customerService.findOne(this.actorService.getActorLogged().getId());
		Assert.notNull(c);
		return this.fixUpTaskRepository.getCustomerFixUpTasks(c.getId());
	}
}
