
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
import domain.Finder;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FinderServiceTest extends AbstractTest {

	@Autowired
	private FinderService	finderService;


	@Test
	public void testCreateFinder() {
		final Finder f = this.finderService.create();
		Assert.notNull(f);
	}

	@Test
	public void testSaveFinder() {
		final Finder f = this.finderService.create();
		final Date startDate = new GregorianCalendar(2020, Calendar.NOVEMBER, 30).getTime();
		final Date finishDate = new GregorianCalendar(2021, Calendar.NOVEMBER, 30).getTime();
		f.setDateFinishRange(finishDate);
		f.setSingleKeyWord("f");
		f.setDateStartRange(startDate);
		final Finder saved = this.finderService.save(f);
		final Collection<Finder> finders = this.finderService.findAll();
		Assert.isTrue(finders.contains(saved));
	}

}
