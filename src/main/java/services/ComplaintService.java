
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ComplaintRepository;
import security.UserAccount;
import domain.Complaint;
import domain.FixUpTask;
import domain.Referee;
import domain.Report;

@Service
@Transactional
public class ComplaintService {

	@Autowired
	private ComplaintRepository	complaintRepository;

	//Supporting services
	@Autowired
	private FixUpTaskService	fixUpTaskService;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private RefereeService		refereeService;


	public Complaint create(final Integer idFixUpTask) {

		final Complaint result = new Complaint();
		result.setTicker(CurriculaService.generadorDeTickers());
		final Date moment = new Date();
		final Integer idCustomer = this.actorService.getActorLogged().getId();
		final Collection<String> attch = new ArrayList<String>();
		final Collection<Report> reports = new ArrayList<Report>();
		result.setAttachment(attch);
		result.setReports(reports);
		result.setMoment(moment);
		result.setFixUpTasks(this.fixUpTaskService.findOne(idFixUpTask));
		result.setCustomer(this.customerService.findOne(idCustomer));

		return result;
	}

	public Collection<Complaint> findAll() {
		Collection<Complaint> result;
		Assert.notNull(this.complaintRepository);
		result = this.complaintRepository.findAll();
		return result;
	}

	public Complaint findOne(final int complaintId) {
		Complaint result;
		Assert.notNull(this.complaintRepository);
		result = this.complaintRepository.findOne(complaintId);
		return result;

	}

	public Complaint save(final Complaint complaint) {

		Complaint result;
		UserAccount userAccount;

		userAccount = this.actorService.getActorLogged().getUserAccount();

		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("CUSTOMER"));
		Assert.notNull(complaint);
		if (complaint.getId() == 0) {
			final Complaint result1 = this.complaintRepository.save(complaint);
			final FixUpTask f = complaint.getFixUpTasks();
			final Collection<Complaint> c = f.getComplaints();
			c.add(result1);
			f.setComplaints(c);
			this.fixUpTaskService.save(f);
		}
		result = this.complaintRepository.save(complaint);
		return result;
	}

	public void delete(final Complaint complaint) {

		Assert.notNull(complaint);
		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("CUSTOMER"));
		assert complaint.getId() != 0;
		Assert.isTrue(this.complaintRepository.exists(complaint.getId()));
		final FixUpTask f = complaint.getFixUpTasks();
		final Collection<Complaint> c = f.getComplaints();
		c.remove(complaint);
		f.setComplaints(c);
		this.fixUpTaskService.save(f);
		this.complaintRepository.delete(complaint);
	}

	public List<Complaint> getComplaintSelfAssigned() {

		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("REFEREE"));
		final Integer idReferee = this.actorService.getActorLogged().getId();
		final Referee referee = this.refereeService.findOne(idReferee);
		final List<Complaint> result = new ArrayList<Complaint>();
		Collection<Report> result1 = new ArrayList<Report>();
		result1 = referee.getReports();
		for (final Report r : result1)
			result.add(r.getComplaint());
		return result;
	}
}
