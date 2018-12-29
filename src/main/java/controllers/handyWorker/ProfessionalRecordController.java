
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
import services.HandyWorkerService;
import services.ProfessionalRecordService;
import controllers.AbstractController;
import domain.Actor;
import domain.HandyWorker;
import domain.ProfessionalRecord;

@Controller
@RequestMapping("professionalRecord/handyWorker")
public class ProfessionalRecordController extends AbstractController {

	@Autowired
	ProfessionalRecordService	professionalRecordService;

	@Autowired
	ActorService				actorService;

	@Autowired
	HandyWorkerService			handyWorkerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<ProfessionalRecord> records;

		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());

		records = hw.getCurricula().getProfessionalRecord();

		result = new ModelAndView("professionalRecord/handyWorker/list");
		result.addObject("professionalRecord", records);
		result.addObject("requestURI", "professionalRecord/handyWorker/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ProfessionalRecord record;

		record = this.professionalRecordService.create();
		result = this.createEditModelAndView(record);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int professionalRecordId) {
		ModelAndView result;
		ProfessionalRecord record;

		record = this.professionalRecordService.findOne(professionalRecordId);
		Assert.notNull(record);
		result = this.createEditModelAndView(record);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ProfessionalRecord professionalRecord, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(professionalRecord);
		else
			try {
				this.professionalRecordService.save(professionalRecord);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(professionalRecord, "professionalRecord.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final ProfessionalRecord record, final BindingResult binding) {
		ModelAndView result;

		try {
			this.professionalRecordService.delete(record);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(record, "professionalRecord.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final ProfessionalRecord record) {
		ModelAndView result;

		result = this.createEditModelAndView(record, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ProfessionalRecord record, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("professionalRecord/handyWorker/edit");
		result.addObject("professionalRecord", record);
		result.addObject("message", messageCode);
		return result;
	}
}
