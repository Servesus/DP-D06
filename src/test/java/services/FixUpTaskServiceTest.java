
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.FixUpTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FixUpTaskServiceTest extends AbstractTest {

	@Autowired
	private FixUpTaskService	fixUpTaskService;


	@Test
	public void testCreateFixUpTask() {
		super.authenticate("customer1");
		final FixUpTask a = this.fixUpTaskService.create();
		Assert.notNull(a);
		super.authenticate(null);
	}
	@Test
	public void testSaveFixUpTask() {
		super.authenticate("customer1");
		final FixUpTask a = this.fixUpTaskService.create();
		final FixUpTask b = this.fixUpTaskService.save(a);
		Assert.isTrue(!(b.equals(null)));
		super.authenticate(null);

	}
	@Test
	public void testDeleteFixUpTask() {
		super.authenticate("customer1");
		final int id = this.getEntityId("fixUpTask1");
		final FixUpTask a = this.fixUpTaskService.findOne(id);
		this.fixUpTaskService.delete(a);
		Assert.isNull(this.fixUpTaskService.findOne(id));
		super.authenticate(null);

	}

}
