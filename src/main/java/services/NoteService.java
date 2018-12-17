
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import security.UserAccount;
import domain.Application;
import domain.Complaint;
import domain.Customer;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Note;
import domain.Report;

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
	
	@Autowired
	private ComplaintService complaintService;


	//Simple CRUD methods
	public Note create(int complaintId) {
		UserAccount userAccount;
		Note result;
	
		Collection<String> customerComments= new ArrayList<String>();
		Collection<String> hwComments= new ArrayList<String>();
		Collection<String> refereeComments= new ArrayList<String>();
		Date moment;
		Customer customer = complaintService.findOne(complaintId).getCustomer();
		HandyWorker handyWorker= null;
		Collection<Application> apps = complaintService.findOne(complaintId)
				.getFixUpTasks().getApplications();
		
		for(int i =0;i<apps.size();i++){
			Application iterador= apps.iterator().next();
			if(iterador.getStatus()==1){
				handyWorker= iterador.getHandyWorker();
				}
			}

		userAccount = this.actorService.getActorLogged().getUserAccount();

		Assert.isTrue(userAccount.equals(customer.getUserAccount()) || 
				userAccount.equals(handyWorker.getUserAccount()));
		
		result = new Note();
		moment = new Date();

		result.setCustomerComments(customerComments);
		result.setHwComments(hwComments);
		result.setRefereeComments(refereeComments);
		result.setMoment(moment);
		return result;
	}

	public List<Note> findAll() {
		return this.noteRepository.findAll();
	}

	public Note findOne(final Integer noteId) {
		Assert.isTrue(noteId != 0);
		return this.noteRepository.findOne(noteId);
	}

	public Note save(final Note n, int complaintId) {
		Assert.notNull(n);
		Note result;
		Report report= null;
		UserAccount userAccount;
		Complaint c = complaintService.findOne(complaintId);
		Collection<Report> reports = reportService.findAll();
		
		for(int i =0;i<reports.size();i++){
			Report iterador= reports.iterator().next();
			if(iterador.getComplaint().equals(c)){
				report= iterador;
				}
			}
		
		FixUpTask f = c.getFixUpTasks();
		Collection<Application> apps = f.getApplications();
		HandyWorker handyWorker=null;
		Customer customer = c.getCustomer();
		
		noteRepository.findOne(n.getId());
		for(int i =0;i<apps.size();i++){
		Application iterador= apps.iterator().next();
		if(iterador.getStatus()==1){
			handyWorker= iterador.getHandyWorker();
			}
		}
		
		userAccount = this.actorService.getActorLogged().getUserAccount();
		
		Assert.isTrue(userAccount.equals(customer.getUserAccount()) || 
				userAccount.equals(handyWorker.getUserAccount()));
		
		Collection<Note> notes = new ArrayList<Note>();
		
		notes= report.getNotes();
		
		result = this.noteRepository.save(n);

		notes.add(result);
		notes.remove(n);
		report.setNotes(notes);

		this.reportService.save(report);

		report.setNotes(notes);
		
		reportService.save(report);

		return result;
	}
}
