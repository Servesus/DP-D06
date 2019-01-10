
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Box;
import domain.Complaint;
import domain.Referee;
import domain.Report;

@Service
@Transactional
public class RefereeService {

	//Managed Repository
	@Autowired
	private RefereeRepository	refereeRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private BoxService			boxService;


	//Simple CRUD methods
	public Referee create() {
		Referee result;
		UserAccount userAccount;
		Authority aut;
		Collection<Authority> auts;

		final UserAccount nowUserAccount = this.actorService.getActorLogged().getUserAccount();

		Assert.isTrue(nowUserAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));

		auts = new ArrayList<Authority>();
		aut = new Authority();
		userAccount = new UserAccount();
		result = new Referee();

		Collection<Report> reports;
		reports = new ArrayList<Report>();

		aut.setAuthority(Authority.REFEREE);
		auts.add(aut);
		userAccount.setAuthorities(auts);

		result.setUserAccount(userAccount);
		result.setReports(reports);
		result.setIsBanned(false);
		result.setIsSuspicious(false);

		return result;
	}
	public Collection<Referee> findAll() {
		Collection<Referee> result;

		result = this.refereeRepository.findAll();

		return result;
	}

	public Referee findOne(final Integer refereeId) {
		Assert.notNull(refereeId);
		Assert.isTrue(refereeId != 0);
		Referee result;

		result = this.refereeRepository.findOne(refereeId);

		return result;
	}

	public Referee save(final Referee r) {
		final Referee result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN") || userAccount.getAuthorities().iterator().next().getAuthority().equals("REFEREE"));
		Assert.notNull(r);

		if (r.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String res = encoder.encodePassword(r.getUserAccount().getPassword(), null);
			r.getUserAccount().setPassword(res);
			final Collection<Box> systemBox = this.boxService.createSystemBoxes();
			r.setBoxes(systemBox);
		}

		result = this.refereeRepository.save(r);
		return result;
	}

	//Other business methods
	public List<Complaint> getComplaintNoSelfAssigned() {
		Collection<Complaint> complaintsWithoutReport;
		complaintsWithoutReport = this.refereeRepository.getComplaintsWithoutReport();
		List<Complaint> result;
		result = new ArrayList<Complaint>();
		result.addAll(complaintsWithoutReport);
		return result;
	}

}
