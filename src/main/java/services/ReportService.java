
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import security.UserAccount;
import domain.Report;

@Service
@Transactional
public class ReportService {

	//Managed Repository
	@Autowired
	private ReportRepository	reportRepository;

	//Supporting services
	@Autowired
	private ActorService		actorService;
	@Autowired
	private RefereeService		refereeService;
	@Autowired
	private ComplaintService	complaintService;


	//Simple CRUD methods
	public Report create() {
		final Report result = new Report();
		final Date moment = new Date();
		final Integer refereeId = this.actorService.getActorLogged().getId();

		result.setMoment(moment);
		result.setReferee(this.refereeService.findOne(refereeId));
		result.setIsFinal(false);
		return result;
	}

	public Collection<Report> findAll() {
		Collection<Report> result;

		result = this.reportRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Report findOne(final Integer reportId) {
		Assert.isTrue(reportId != 0);
		Report result;

		result = this.reportRepository.findOne(reportId);

		return result;
	}

	public Report save(final Report r) {
		Assert.notNull(r);
		Report result;

		result = this.reportRepository.save(r);

		return result;
	}

	public void delete(final Report r) {
		UserAccount userAccount;

		userAccount = this.actorService.getActorLogged().getUserAccount();

		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("REFEREE"));
		Assert.isTrue(r.getIsFinal() == true);
		Assert.notNull(r);
		Assert.isTrue(r.getId() != 0);
		this.reportRepository.delete(r);
	}

}
