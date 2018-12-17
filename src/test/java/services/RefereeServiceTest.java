
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.UserAccount;
import utilities.AbstractTest;
import domain.Referee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class RefereeServiceTest extends AbstractTest {

	@Autowired
	private RefereeService	refereeService;


	@Test
	public void createTest() {
		super.authenticate("admin1");

		final Referee r = this.refereeService.create();
		Assert.notNull(r);
	}

	@Test
	public void saveTest() {
		super.authenticate("admin1");
		UserAccount userAccount;

		final Referee r = this.refereeService.create();

		userAccount = r.getUserAccount();
		userAccount.setUsername("referee300");
		userAccount.setPassword("123456789");

		r.setUserAccount(userAccount);

		r.setName("Sergio");
		r.setSurname("Pérez");
		r.setEmail("sergio@bananapps.com");
		r.setAddress("Mi calle");
		r.setPhoto("http://www.mifoto.com");
		r.setPhoneNumber("689312351");

		final Referee referee = this.refereeService.save(r);
		final Collection<Referee> referees = this.refereeService.findAll();
		Assert.isTrue(referees.contains(referee));
	}
}
