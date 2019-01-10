
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.UserAccount;
import domain.Actor;
import domain.Application;
import domain.Box;
import domain.Complaint;
import domain.Customer;
import domain.FixUpTask;
import domain.Profile;

@Service
@Transactional
public class CustomerService {

	//Managed repositories
	@Autowired
	private CustomerRepository	customerRepository;

	//Supporting services
	@Autowired
	private FixUpTaskService	fixUpTaskService;

	@Autowired
	private BoxService			boxService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ComplaintService	complaintService;


	public Customer create() {
		Customer result;
		Authority auth;
		UserAccount userAccount;
		Collection<Authority> authorities;
		Collection<Profile> profiles;
		Collection<Box> boxes;
		Collection<Complaint> complaints;
		Collection<FixUpTask> fixUpTasks;

		result = new Customer();
		userAccount = new UserAccount();
		auth = new Authority();
		authorities = new ArrayList<Authority>();

		profiles = new ArrayList<Profile>();
		boxes = new ArrayList<Box>();
		complaints = new ArrayList<Complaint>();
		fixUpTasks = new ArrayList<FixUpTask>();

		auth.setAuthority(Authority.CUSTOMER);
		authorities.add(auth);
		userAccount.setAuthorities(authorities);

		result.setUserAccount(userAccount);
		result.setIsBanned(false);
		result.setIsSuspicious(false);
		result.setProfiles(profiles);
		result.setBoxes(boxes);
		result.setComplaints(complaints);
		result.setFixUpTasks(fixUpTasks);

		return result;
	}

	public Collection<Customer> findAll() {
		Collection<Customer> result;

		result = this.customerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Customer findOne(final int customerId) {
		Assert.isTrue(customerId != 0);
		Customer result;

		result = this.customerRepository.findOne(customerId);

		return result;
	}

	public Customer save(final Customer customer) {
		Assert.notNull(customer);
		Customer result;

		if (customer.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String res = encoder.encodePassword(customer.getUserAccount().getPassword(), null);
			customer.getUserAccount().setPassword(res);
			final Collection<Box> systemBox = this.boxService.createSystemBoxes();
			customer.setBoxes(systemBox);
		}

		result = this.customerRepository.save(customer);

		return result;
	}

	public List<FixUpTask> showFixUpTasks() {
		Actor actor;
		List<FixUpTask> result;

		actor = this.actorService.getActorLogged();
		final int userAccountId = actor.getUserAccount().getId();

		result = this.customerRepository.getFixUpTasks(userAccountId);

		return result;
	}

	public FixUpTask getFixUpTask(final int fixUpTaskId) {
		FixUpTask result;
		List<FixUpTask> fixUpTasks;
		final List<Integer> ids = new ArrayList<Integer>();
		int i = 0;

		fixUpTasks = this.showFixUpTasks();

		while (i < fixUpTasks.size()) {
			ids.add(fixUpTasks.get(i).getId());
			i++;
		}

		Assert.isTrue(ids.contains(fixUpTaskId));

		result = this.fixUpTaskService.findOne(fixUpTaskId);

		return result;
	}

	public Customer getCustomerLogged() {
		Actor actor;
		Customer result;

		actor = this.actorService.getActorLogged();

		result = this.findOne(actor.getId());

		return result;
	}

	public List<Application> showApplications() {
		List<Application> result;
		Customer customer;

		customer = this.getCustomerLogged();

		result = this.customerRepository.getApplications(customer.getUserAccount().getId());

		return result;
	}

	public List<Complaint> showComplaints() {
		List<Complaint> result;
		Customer customer;

		customer = this.getCustomerLogged();

		result = this.customerRepository.getComplaints(customer.getUserAccount().getId());

		return result;
	}

	public Complaint getComplaint(final int complaintId) {
		Complaint result;
		List<Complaint> complaints;
		final List<Integer> ids = new ArrayList<Integer>();
		int i = 0;

		complaints = this.showComplaints();

		while (i < complaints.size()) {
			ids.add(complaints.get(i).getId());
			i++;
		}

		Assert.isTrue(ids.contains(complaintId));

		result = this.complaintService.findOne(complaintId);

		return result;
	}

	public List<String> CustomerPorFixUpTask() {
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		final List<String> res = new ArrayList<>();
		Collection<FixUpTask> a = new ArrayList<FixUpTask>();
		a = this.fixUpTaskService.findAll();
		Collection<Customer> b = new ArrayList<Customer>();
		b = this.customerRepository.findAll();
		for (final FixUpTask f : a)
			for (final Customer c : b)
				if (c.getFixUpTasks().contains(f)) {
					res.add("FixUpTask with id:" + f.getId() + " is assigned to " + c.getName());
					break;
				}
		return res;
	}

}
