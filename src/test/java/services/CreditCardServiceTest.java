package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.CreditCard;
import domain.Customer;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
public class CreditCardServiceTest extends AbstractTest{
	
	@Autowired
	private CreditCardService creditCardService;
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	public void createTest(){
		super.authenticate("customer1");
		CreditCard creditCard= creditCardService.create();
		Assert.notNull(creditCard);
		
	}
	
	@Test
	public void saveTest(){
		super.authenticate("customer1");
		int id = this.getEntityId("customer1");
		
		CreditCard c = creditCardService.create();
		
		c.setHolderName("holderNameTest");
		c.setBrandName("VISA");
		c.setNumber("4609805199761841");
		c.setExpirationYear(2020);
		c.setMonth(12);
		c.setCvv(893);
		
		CreditCard result = creditCardService.save(c);
		
		Customer customer= customerService.findOne(id);
		Assert.isTrue(customer.getCreditCards().contains(result));
	}
	
	@Test
	public void deleteTest(){
		super.authenticate("customer1");
		int id = this.getEntityId("creditCard1");
		CreditCard c = creditCardService.findOne(id);
		int idCustomer = this.getEntityId("customer1");
		
		Customer customer= customerService.findOne(idCustomer);
		
		creditCardService.delete(c);
		
		Assert.isNull(creditCardService.findOne(id));
		Assert.isTrue(!customer.getCreditCards().contains(c));
	}

}
