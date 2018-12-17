
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
		int complaintId= this.getEntityId("complaint1");
		final Note n = this.noteService.create(complaintId);
		Assert.notNull(n);
	}

	@Test
	public void saveTest() {
		super.authenticate("customer1");
		int complaintId = this.getEntityId("complaint1");
		final Note note = this.noteService.create(complaintId);
		String com = "Comentarios de autor";

		note.setAuthorComment(com);
		final Note n = this.noteService.save(note, complaintId);
		final Collection<Note> notes = this.noteService.findAll();
		Assert.isTrue(notes.contains(n));

	}

	@Test
	public void saveTest2() {
		super.authenticate("customer1");
		final int noteId = this.getEntityId("note1");
		final int reportId = this.getEntityId("report1");
		int complaintId = this.getEntityId("complaint1");
		Collection<String> customerComments;

		final Note note = this.noteService.findOne(noteId);

		customerComments = note.getCustomerComments();
		customerComments.add("Nuevo comentario del customer");

		note.setCustomerComments(customerComments);

		final Note n = this.noteService.save(note, complaintId);

		Assert.notNull(this.noteService.findOne(n.getId()));
		Assert.isTrue(this.noteService.findOne(n.getId()).getCustomerComments()
				.equals(customerComments));
		Assert.isTrue(this.reportService.findOne(reportId).getNotes().contains(n));
	}
}
