
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	@Autowired
	private ApplicationService	applicationService;


	@Test
	public void createTest() {
		super.authenticate("handyWorker1");
		final int futId = this.getEntityId("fixUpTask1");
		final Application a = this.applicationService.create(futId);
		Assert.notNull(a);
	}

	@Test
	public void saveTestHandyWorker() {
		super.authenticate("handyWorker1");
		final int fixUpTaskId = this.getEntityId("fixUpTask1");

		final Application result = this.applicationService.create(fixUpTaskId);

		final Application a = this.applicationService.save(result);
		Assert.notNull(this.applicationService.findOne(a.getId()));

	}

	@Test
	public void saveTestCustomer() {
		super.authenticate("customer1");
		final int applicationId = this.getEntityId("application1");

		final Application a = this.applicationService.findOne(applicationId);

		a.setStatus(-1);
		final Application result = this.applicationService.save(a);
		final Collection<Application> col = this.applicationService.findAll();

		Assert.isTrue(col.contains(result));
	}
}
