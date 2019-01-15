
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ReportServiceTest extends AbstractTest {

	@Autowired
	private ReportService	reportService;


	@Test
	public void testCreateReport() {
		super.authenticate("referee1");
		final Report r = this.reportService.create();
		Assert.notNull(r);
		super.authenticate(null);
	}
	/*
	 * @Test
	 * public void testSaveReport() {
	 * super.authenticate("referee1");
	 * final Report a = this.reportService.create();
	 * final Report b = this.reportService.save(a);
	 * Assert.notNull(b);
	 * super.authenticate(null);
	 * }
	 */
	@Test
	public void testDeleteReport() {
		super.authenticate("referee1");
		final int id = this.getEntityId("report1");
		final Report a = this.reportService.findOne(id);
		this.reportService.delete(a);
		Assert.isNull(this.reportService.findOne(id));
		super.authenticate(null);
	}

}
