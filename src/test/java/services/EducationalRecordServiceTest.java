
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.EducationalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EducationalRecordServiceTest extends AbstractTest {

	@Autowired
	private EducationalRecordService	educationalRecordService;


	@Test
	public void testCreateEducationalRecord() {
		super.authenticate("handyWorker1");
		final EducationalRecord a = this.educationalRecordService.create();
		Assert.notNull(a);
		super.authenticate(null);
	}
	@Test
	public void testSaveEducationalRecord() {
		super.authenticate("handyWorker1");
		final EducationalRecord a = this.educationalRecordService.create();
		final EducationalRecord b = this.educationalRecordService.save(a);
		Assert.notNull(b);
		super.authenticate(null);
	}
	@Test
	public void testDeleteEducationalRecord() {
		super.authenticate("handyWorker1");
		final int id = this.getEntityId("educationalRecord1");
		final EducationalRecord a = this.educationalRecordService.findOne(id);
		this.educationalRecordService.delete(a);
		Assert.isNull(this.educationalRecordService.findOne(id));
		super.authenticate(null);
	}

}
