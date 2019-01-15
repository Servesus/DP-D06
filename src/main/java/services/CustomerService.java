
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
import domain.Box;
import domain.Customer;
import domain.FixUpTask;
import domain.Profile;

@Service
@Transactional
public class CustomerService {

	//Managed repositories
	@Autowired
	private CustomerRepository		customerRepository;

	//Supporting services
	@Autowired
	private FixUpTaskService		fixUpTaskService;

	@Autowired
	private BoxService				boxService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private ComplaintService		complaintService;

	@Autowired
	private ConfigurationService	configurationService;


	public Customer create() {
		Customer result;
		Authority auth;
		UserAccount userAccount;
		Collection<Authority> authorities;
		Collection<Profile> profiles;
		Collection<Box> boxes;

		result = new Customer();
		userAccount = new UserAccount();
		auth = new Authority();
		authorities = new ArrayList<Authority>();

		profiles = new ArrayList<Profile>();
		boxes = new ArrayList<Box>();

		auth.setAuthority(Authority.CUSTOMER);
		authorities.add(auth);
		userAccount.setAuthorities(authorities);

		result.setUserAccount(userAccount);
		result.setIsBanned(false);
		result.setIsSuspicious(false);
		result.setProfiles(profiles);
		result.setBoxes(boxes);

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
		final char[] c = customer.getPhoneNumber().toCharArray();
		if (c[0] != '+') {
			final Integer i = this.configurationService.findAll().get(0).getPhoneCCode();
			final String s = i.toString();
			customer.setPhoneNumber("+" + s + " " + customer.getPhoneNumber());
		}
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
		List<FixUpTask> result;
		result = (List<FixUpTask>) this.fixUpTaskService.getCustomerFixUpTasks();

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

}
