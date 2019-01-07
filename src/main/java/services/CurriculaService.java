
package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CurriculaRepository;
import domain.Curricula;
import domain.EducationalRecord;
import domain.EndorserRecord;
import domain.MiscRecord;
import domain.ProfessionalRecord;

@Service
@Transactional
public class CurriculaService {

	//Managed repository
	@Autowired
	private CurriculaRepository		curriculaRepository;

	//Supporting services
	@Autowired
	private PersonalRecordService	personalRecordService;


	//Simple CRUD methods
	public Curricula create() {
		final Curricula curricula = new Curricula();

		curricula.setTicker(CurriculaService.generadorDeTickers());
		curricula.setEducationalRecord(new ArrayList<EducationalRecord>());
		curricula.setEndorserRecord(new ArrayList<EndorserRecord>());
		curricula.setProfessionalRecord(new ArrayList<ProfessionalRecord>());
		curricula.setMiscRecord(new ArrayList<MiscRecord>());
		return curricula;
	}
	public Collection<Curricula> findAll() {
		return this.curriculaRepository.findAll();
	}

	public Curricula findOne(final int curriculaID) {
		return this.curriculaRepository.findOne(curriculaID);
	}

	public Curricula save(final Curricula curricula) {
		Curricula result;
		result = this.curriculaRepository.save(curricula);

		//final HandyWorker hw = this.handyWorkerService.findOne(this.actorService.getActorLogged().getId());
		//UserAccount userAccount;
		//userAccount = this.actorService.getActorLogged().getUserAccount();
		//Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
		//hw.setCurricula(result);
		//this.handyWorkerService.save(hw);
		//Assert.isTrue(hw.getCurricula().getId() == result.getId());
		return result;
	}

	/*
	 * public void delete(final Curricula curricula) {
	 * Assert.isTrue(curricula.getId() != 0);
	 * UserAccount userAccount;
	 * userAccount = this.actorService.getActorLogged().getUserAccount();
	 * Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("HANDYWORKER"));
	 * final HandyWorker hw = this.handyWorkerService.findOne(this.actorService.getActorLogged().getId());
	 * Assert.isTrue(hw.getCurricula().getId() == curricula.getId());
	 * 
	 * this.curriculaRepository.delete(curricula);
	 * }
	 */

	//Other business methods

	public static String generadorDeTickers() {
		String dateRes = "";
		String numericRes = "";
		final String alphanumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdfghijklmnopqrstuvwxyz";
		dateRes = new SimpleDateFormat("yyMMdd").format(Calendar.getInstance().getTime());

		for (int i = 0; i < 6; i++) {
			final Random random = new Random();
			numericRes = numericRes + alphanumeric.charAt(random.nextInt(alphanumeric.length() - 1));
		}

		return dateRes + "-" + numericRes;
	}

}
