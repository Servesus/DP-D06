
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.CreditCard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CreditCardServiceTest extends AbstractTest {

	@Autowired
	private CreditCardService	creditCardService;

	@Autowired
	private CustomerService		customerService;


	@Test
	public void createTest() {
		super.authenticate("customer1");
		final CreditCard creditCard = this.creditCardService.create();
		Assert.notNull(creditCard);

	}

	@Test
	public void saveTest() {
		super.authenticate("customer1");
		final int id = this.getEntityId("customer1");

		final CreditCard c = this.creditCardService.create();

		c.setHolderName("holderNameTest");
		c.setBrandName("VISA");
		c.setNumber("4609805199761841");
		c.setExpirationYear(2020);
		c.setMonth(12);
		c.setCvv(893);

		final CreditCard result = this.creditCardService.save(c);
		Assert.notNull(result);

	}

}
