
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.UserAccount;
import utilities.AbstractTest;
import domain.Actor;
import domain.Administrator;
import domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AdministratorServiceTest extends AbstractTest {

	//Service testing
	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private CustomerService			customerService;


	@Test
	public void create() {
		super.authenticate("admin1");

		Administrator a;
		a = this.administratorService.create();
		a.setName("admin2");
		a.setSurname("admin2");
		a.setEmail("admin2@gmail.com");
		Assert.notNull(a);

		super.authenticate(null);
	}

	@Test
	public void save() {

		super.authenticate("admin1");

		Administrator a;
		Administrator saved;
		UserAccount userAccount;
		Collection<Administrator> admins;

		a = this.administratorService.create();
		userAccount = a.getUserAccount();
		userAccount.setUsername("admin2");
		userAccount.setPassword("02061997");
		a.setUserAccount(userAccount);
		a.setName("admin2");
		a.setSurname("admin2");
		a.setEmail("admin2@gmail.com");

		saved = this.administratorService.save(a);

		admins = this.administratorService.findAll();

		Assert.isTrue(admins.contains(saved));

		super.authenticate(null);
	}
	@Test
	public void findOne() {
		Integer id;
		id = this.getEntityId("admin1");
		Administrator a;
		a = this.administratorService.findOne(id);
		Assert.isTrue(a.getName().equals("admin1"));
		Assert.notNull(a);
	}

	@Test
	public void findAll() {
		Collection<Administrator> admins;
		admins = this.administratorService.findAll();
		Assert.notNull(admins);
	}

	@Test
	public void delete() {
		super.authenticate("admin1");

		Administrator a;
		Integer id;
		id = this.getEntityId("admin1");
		a = this.administratorService.findOne(id);
		this.administratorService.delete(a);
		Assert.isNull(this.administratorService.findOne(id));

		super.authenticate(null);
	}

	@Test
	public void getSuspicious() {
		super.authenticate("admin1");
		List<Actor> suspicious;
		suspicious = this.administratorService.getSuspicious();
		Assert.notNull(suspicious);
		super.authenticate(null);
	}

	@Test
	public void banActor() {
		super.authenticate("admin1");
		Integer id;
		id = this.getEntityId("customer2");
		Customer c;
		c = this.customerService.findOne(id);
		this.administratorService.banActor(id);
		Assert.isTrue(c.getIsBanned());
		super.authenticate(null);
	}

	@Test
	public void unbanActor() {
		super.authenticate("admin1");
		Integer id;
		id = this.getEntityId("customer3");
		Customer c;
		c = this.customerService.findOne(id);
		this.administratorService.unbanActor(id);
		Assert.isTrue(!(c.getIsBanned()));
		super.authenticate(null);
	}

}
