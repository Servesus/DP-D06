
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import security.UserAccount;
import domain.Actor;
import domain.Referee;
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


	//Simple CRUD methods
	public Report create() {
		UserAccount userAccount;

		userAccount = this.actorService.getActorLogged().getUserAccount();

		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("REFEREE"));
		Actor a;
		a = this.actorService.getActorLogged();
		Referee r;
		r = this.refereeService.findOne(a.getId());

		Collection<Report> reports;
		reports = r.getReports();
		Report result;

		result = new Report();
		result.setIsFinal(true);
		reports.add(result);
		r.setReports(reports);
		this.refereeService.save(r);

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
		Date currentMoment;

		if (r.getId() == 0) {
			Assert.isTrue(r.getIsFinal() == true);
			currentMoment = new Date();
			r.setMoment(currentMoment);
			result = this.reportRepository.save(r);
		}
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
