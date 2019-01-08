
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("CUSTOMER"));
		final FixUpTask result = new FixUpTask();
		result.setTicker(CurriculaService.generadorDeTickers());
		final Collection<Application> applications = new ArrayList<Application>();
		final Collection<Complaint> complaints = new ArrayList<Complaint>();
		final Collection<Phase> phases = new ArrayList<Phase>();
		result.setApplications(applications);
		result.setComplaints(complaints);
		result.setPhases(phases);
		return result;
	}

	public Collection<FixUpTask> findAll() {
		Collection<FixUpTask> result;
		result = this.fixUpTaskRepository.findAll();
		return result;
	}
	public List<FixUpTask> findAllCustomer(final int customerId) {
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("CUSTOMER"));
		final List<FixUpTask> res = new ArrayList<FixUpTask>();
		final Customer customer = this.customerService.findOne(customerId);
		res.addAll(customer.getFixUpTasks());
		return res;
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
		//		final Actor a = this.actorService.getActorLogged();
		//		final Customer customer = this.customerService.findOne(a.getId());
		//		final Collection<FixUpTask> f = customer.getFixUpTasks();

		//		if (fixUpTask.getId() == 0) {
		//			f.add(result);
		//			customer.setFixUpTasks(f);
		//			this.customerService.save(customer);
		//		}
		return result;
	}
	public void delete(final FixUpTask fixUpTask) {

		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("CUSTOMER"));
		Assert.notNull(fixUpTask);
		assert fixUpTask.getId() != 0;
		Assert.isTrue(this.fixUpTaskRepository.exists(fixUpTask.getId()));
		final Integer idCustomer = this.actorService.getActorLogged().getId();
		final Customer customer = this.customerService.findOne(idCustomer);
		final Collection<FixUpTask> f = customer.getFixUpTasks();
		f.remove(fixUpTask);
		customer.setFixUpTasks(f);
		this.customerService.save(customer);
		this.fixUpTaskRepository.delete(fixUpTask);
	}
}
