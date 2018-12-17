
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
import domain.EndorserRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EndorserRecordServiceTest extends AbstractTest {

	@Autowired
	private EndorserRecordService	endorserRecordService;


	@Test
	public void createTest() {
		super.authenticate("handyWorker1");
		final EndorserRecord er = this.endorserRecordService.create();
		Assert.notNull(er);
	}

	@Test
	public void saveTest() {
		super.authenticate("handyWorker1");
		final EndorserRecord er = this.endorserRecordService.create();
		er.setEmail("email@email.com");
		er.setFullName("fullName");
		er.setLinkedInProfile("https://linkedin.com/profile");
		er.setPhone("652914588");
		final EndorserRecord saved = this.endorserRecordService.save(er);
		final Collection<EndorserRecord> endorsers = this.endorserRecordService.findAll();
		Assert.isTrue(endorsers.contains(saved));
	}

	@Test
	public void deleteTest() {
		super.authenticate("handyWorker1");
		final Integer id = this.getEntityId("endorserRecord1");
		final EndorserRecord endorser = this.endorserRecordService.findOne(id);
		this.endorserRecordService.delete(endorser);
		final EndorserRecord deleted = this.endorserRecordService.findOne(endorser.getId());
		Assert.isNull(deleted);
	}
}
