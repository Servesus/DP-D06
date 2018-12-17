
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
import domain.Phase;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PhaseServiceTest extends AbstractTest {

	//Service Testing
	@Autowired
	private PhaseService	phaseService;


	//2696 id fixUp crear fase 5
	@Test
	public void testCreatePhase() {
		final int fixUpTaskId = this.getEntityId("fixUpTask1");
		final Phase p;
		p = this.phaseService.create(fixUpTaskId);
		Assert.notNull(p.getFixUpTask());
		Assert.notNull(p);
	}

	@Test
	public void testSavePhase() {
		Phase phase;
		final Phase saved;
		Collection<Phase> phases;
		final int fixUpTaskId = this.getEntityId("fixUpTask1");
		phase = this.phaseService.create(fixUpTaskId);

		final Date startDate = new GregorianCalendar(2020, Calendar.NOVEMBER, 30).getTime();
		final Date finishDate = new GregorianCalendar(2021, Calendar.NOVEMBER, 30).getTime();
		phase.setTitle("phase5");
		phase.setDescription("description");
		phase.setStartMoment(startDate);
		phase.setFinishMoment(finishDate);
		phase.setNumber(5);
		super.authenticate("handyWorker1");
		saved = this.phaseService.save(phase);
		phases = this.phaseService.findAll();
		Assert.isTrue(phases.contains(saved));
		super.authenticate(null);

	}
	@Test
	public void testDeletePhase() {
		super.authenticate("handyWorker1");
		final int phaseId = this.getEntityId("phase1");
		final Phase delete = this.phaseService.findOne(phaseId);
		this.phaseService.delete(delete);
		final Collection<Phase> phases = this.phaseService.findAll();
		Assert.isTrue(!phases.contains(delete));
		super.authenticate(null);

	}
}
