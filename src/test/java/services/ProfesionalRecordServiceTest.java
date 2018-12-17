
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.ProfessionalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ProfesionalRecordServiceTest extends AbstractTest {

	@Autowired
	private ProfessionalRecordService	professionalRecordService;


	@Test
	public void create() {
		super.authenticate("handyWorker1");

		ProfessionalRecord p;
		final Date jobBeggining = new GregorianCalendar(2015, Calendar.NOVEMBER, 30).getTime();
		final Date jobEnding = new GregorianCalendar(2016, Calendar.NOVEMBER, 30).getTime();
		p = this.professionalRecordService.create();
		p.setCompanyName("company1");
		p.setJobBeggining(jobBeggining);
		p.setJobEnding(jobEnding);
		p.setRol("Ingeniero");

		Assert.notNull(p);

		super.authenticate(null);
	}

	@Test
	public void findAll() {
		Collection<ProfessionalRecord> professionalRecords;
		professionalRecords = this.professionalRecordService.findAll();
		Assert.notNull(professionalRecords);
	}

	@Test
	public void findOne() {
		Integer id;
		id = this.getEntityId("professionalRecord1");
		ProfessionalRecord p;
		p = this.professionalRecordService.findOne(id);
		Assert.isTrue(p.getCompanyName().equals("Azulejos Martin"));
		Assert.notNull(p);
	}

	@Test
	public void save() {
		super.authenticate("handyWorker1");
		ProfessionalRecord p;
		ProfessionalRecord saved;
		Collection<ProfessionalRecord> professionalRecords;

		p = this.professionalRecordService.create();

		final Date jobBeggining = new GregorianCalendar(2015, Calendar.NOVEMBER, 30).getTime();
		final Date jobEnding = new GregorianCalendar(2016, Calendar.NOVEMBER, 30).getTime();
		p = this.professionalRecordService.create();
		p.setCompanyName("company1");
		p.setJobBeggining(jobBeggining);
		p.setJobEnding(jobEnding);
		p.setRol("Ingeniero");

		saved = this.professionalRecordService.save(p);
		professionalRecords = this.professionalRecordService.findAll();

		Assert.isTrue(professionalRecords.contains(saved));
		super.authenticate(null);
	}

	@Test
	public void delete() {
		super.authenticate("handyWorker1");
		ProfessionalRecord p;
		Integer id;
		id = this.getEntityId("professionalRecord1");
		p = this.professionalRecordService.findOne(id);
		this.professionalRecordService.delete(p);
		Assert.isNull(this.professionalRecordService.findOne(id));
		super.authenticate(null);
	}
}
