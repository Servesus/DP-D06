
package controllers.handyWorker;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.EducationalRecordService;
import services.HandyWorkerService;
import controllers.AbstractController;
import domain.Actor;
import domain.EducationalRecord;
import domain.HandyWorker;

@Controller
@RequestMapping("educationalRecord/handyWorker")
public class EducationalRecordController extends AbstractController {

	@Autowired
	private EducationalRecordService	educationalRecordService;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private HandyWorkerService			handyWorkerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<EducationalRecord> records;

		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());

		records = hw.getCurricula().getEducationalRecord();

		result = new ModelAndView("educationalRecord/handyWorker/list");
		result.addObject("educationalRecord", records);
		result.addObject("requestURI", "educationalRecord/handyWorker/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		EducationalRecord record;

		record = this.educationalRecordService.create();
		result = this.createEditModelAndView(record);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int educationalRecordId) {
		ModelAndView result;
		EducationalRecord record;

		record = this.educationalRecordService.findOne(educationalRecordId);
		Assert.notNull(record);
		result = this.createEditModelAndView(record);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final EducationalRecord educationalRecord, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(educationalRecord);
		else
			try {
				this.educationalRecordService.save(educationalRecord);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(educationalRecord, "educationalRecord.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final EducationalRecord record, final BindingResult binding) {
		ModelAndView result;

		try {
			this.educationalRecordService.delete(record);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(record, "educationalRecord.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final EducationalRecord record) {
		ModelAndView result;

		result = this.createEditModelAndView(record, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final EducationalRecord record, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("educationalRecord/handyWorker/edit");
		result.addObject("educationalRecord", record);
		result.addObject("message", messageCode);
		return result;
	}

}
