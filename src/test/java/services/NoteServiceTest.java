
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
import domain.Note;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class NoteServiceTest extends AbstractTest {

	@Autowired
	private NoteService		noteService;

	@Autowired
	private ReportService	reportService;


	@Test
	public void createTest() {
		super.authenticate("customer1");
		final Note n = this.noteService.create();
		Assert.notNull(n);
	}

	@Test
	public void saveTest() {
		super.authenticate("customer1");
		final int reportId = this.getEntityId("report1");
		final Note note = this.noteService.create();
		final String com = "Comentarios de autor";
		note.setCustomerComments(com);
		final Note n = this.noteService.save(note, reportId);
		final Collection<Note> notes = this.noteService.findAll();
		Assert.isTrue(notes.contains(n));

	}

}
