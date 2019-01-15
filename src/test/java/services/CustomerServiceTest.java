
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.UserAccount;
import utilities.AbstractTest;
import domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CustomerServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private CustomerService	customerService;


	@Test
	public void createTest() {
		final Customer c = this.customerService.create();

		Assert.notNull(c);
	}

	@Test
	public void saveTest() {
		final Customer c = this.customerService.create();

		final UserAccount userAccount = c.getUserAccount();
		userAccount.setUsername("pruebaRegister");
		userAccount.setPassword("pruebaRegister");
		c.setUserAccount(userAccount);
		c.setName("pruebaRegister");
		c.setSurname("pruebaRegister");

		final Customer test = this.customerService.save(c);

		Assert.isTrue(this.customerService.findAll().contains(test));
	}

}
