
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
import services.EndorserRecordService;
import services.HandyWorkerService;
import controllers.AbstractController;
import domain.Actor;
import domain.EndorserRecord;
import domain.HandyWorker;

@Controller
@RequestMapping("endorserRecord/handyWorker")
public class EndorserRecordController extends AbstractController {

	@Autowired
	EndorserRecordService	endorserRecordService;

	@Autowired
	ActorService			actorService;

	@Autowired
	HandyWorkerService		handyWorkerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<EndorserRecord> records;

		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());

		records = hw.getCurricula().getEndorserRecord();

		result = new ModelAndView("endorserRecord/handyWorker/list");
		result.addObject("endorserRecord", records);
		result.addObject("requestURI", "endorserRecord/handyWorker/list.do");

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		EndorserRecord record;

		record = this.endorserRecordService.create();
		result = this.createEditModelAndView(record);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int endorserRecordId) {
		ModelAndView result;
		EndorserRecord record;

		record = this.endorserRecordService.findOne(endorserRecordId);
		Assert.notNull(record);
		result = this.createEditModelAndView(record);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final EndorserRecord endorserRecord, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(endorserRecord);
		else
			try {
				this.endorserRecordService.save(endorserRecord);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(endorserRecord, "endorserRecord.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final EndorserRecord record, final BindingResult binding) {
		ModelAndView result;

		try {
			this.endorserRecordService.delete(record);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(record, "endorserRecord.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final EndorserRecord record) {
		ModelAndView result;

		result = this.createEditModelAndView(record, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final EndorserRecord record, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("endorserRecord/handyWorker/edit");
		result.addObject("endorserRecord", record);
		result.addObject("message", messageCode);
		return result;
	}

}
