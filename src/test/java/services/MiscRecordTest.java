
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.MiscRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MiscRecordTest extends AbstractTest {

	@Autowired
	private MiscRecordService	miscRecordService;


	@Test
	public void testCreateMiscRecord() {
		super.authenticate("handyWorker1");
		final MiscRecord mR = this.miscRecordService.create();
		Assert.notNull(mR);
	}

	@Test
	public void testSaveMiscRecord() {
		super.authenticate("handyWorker1");
		final MiscRecord mR = this.miscRecordService.create();
		mR.setAttachment("https://www.at.com");
		mR.setTitle("title");
		final MiscRecord saved = this.miscRecordService.save(mR);
		final Collection<MiscRecord> miscs = this.miscRecordService.findAll();
		Assert.isTrue(miscs.contains(saved));
	}

	@Test
	public void testDeleteMiscRecord() {
		super.authenticate("handyWorker1");
		final Integer id = this.getEntityId("miscRecord1");
		final MiscRecord misc = this.miscRecordService.findOne(id);
		this.miscRecordService.delete(misc);
		final MiscRecord deleted = this.miscRecordService.findOne(misc.getId());
		Assert.isNull(deleted);
	}

}
