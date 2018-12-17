package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Application;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
public class ApplicationServiceTest extends AbstractTest{
	
	@Autowired
	private ApplicationService applicationService;
	
	@Test
	public void createTest(){
		super.authenticate("handyWorker1");
		int futId = this.getEntityId("fixUpTask1");
		Application a = applicationService.create(futId);
		Assert.notNull(a);
	}
	
	@Test
	public void saveTestHandyWorker(){
		super.authenticate("handyWorker1");
		int fixUpTaskId = this.getEntityId("fixUpTask1");
		
		Application result = applicationService.create(fixUpTaskId);
		
		Application a = applicationService.save(result);
		Assert.notNull(applicationService.findOne(a.getId()));
		
	}
	
	@Test
	public void saveTestCustomer(){
		super.authenticate("customer1");
		int applicationId = this.getEntityId("application1");
		
		Application a = applicationService.findOne(applicationId);
		a.setStatus(-1);
		Application result = applicationService.save(a);
		Collection<Application> col = applicationService.findAll();
		
		Assert.isTrue(col.contains(result));
	}
}
