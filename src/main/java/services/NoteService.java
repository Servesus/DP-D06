
package services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import domain.Note;

@Service
@Transactional
public class NoteService {

	//Managed Repository
	@Autowired
	private NoteRepository	noteRepository;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private ReportService	reportService;


	//Simple CRUD methods
	public Note create() {
		final Note n = new Note();
		n.setMoment(new Date());
		return n;
	}

	public List<Note> findAll() {
		return this.noteRepository.findAll();
	}

	public Note findOne(final Integer noteId) {
		Assert.isTrue(noteId != 0);
		return this.noteRepository.findOne(noteId);
	}

	public Note save(final Note n, final int reportId) {
		final Note saved = this.noteRepository.save(n);
		if (n.getId() == 0)
			this.reportService.findOne(reportId).getNotes().add(saved);
		return saved;
	}
}
