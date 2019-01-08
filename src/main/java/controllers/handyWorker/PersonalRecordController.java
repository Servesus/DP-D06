
package controllers.handyWorker;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.HandyWorkerService;
import services.PersonalRecordService;
import controllers.AbstractController;
import domain.Actor;
import domain.HandyWorker;
import domain.PersonalRecord;

@Controller
@RequestMapping("personalRecord/handyWorker")
public class PersonalRecordController extends AbstractController {

	@Autowired
	private ActorService			actorService;
	@Autowired
	private HandyWorkerService		handyWorkerService;
	@Autowired
	private PersonalRecordService	personalRecordService;


	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		PersonalRecord personalRecord;

		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());

		personalRecord = hw.getCurricula().getPersonalRecord();
		if (personalRecord == null) {
			personalRecord = this.personalRecordService.create();
			personalRecord.setEmail(hw.getEmail());
			personalRecord.setMiddleName(hw.getMiddleName());
			personalRecord.setName(hw.getName());
			personalRecord.setPhone(hw.getPhoneNumber());
			personalRecord.setSurname(hw.getSurname());
			personalRecord.setPhoto(hw.getPhoto());
		}
		result = this.createEditModelAndView(personalRecord);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final PersonalRecord personalRecord, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(personalRecord);
		else
			try {
				this.personalRecordService.save(personalRecord);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(personalRecord, "personalRecord.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final PersonalRecord personalRecord) {
		ModelAndView result;

		result = this.createEditModelAndView(personalRecord, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final PersonalRecord personalRecord, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("personalRecord/handyWorker/edit");
		result.addObject("personalRecord", personalRecord);
		result.addObject("messageCode", messageCode);

		return result;
	}
}
