
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
import services.MiscRecordService;
import controllers.AbstractController;
import domain.Actor;
import domain.HandyWorker;
import domain.MiscRecord;

@Controller
@RequestMapping("miscRecord/handyWorker")
public class MiscRecordController extends AbstractController {

	@Autowired
	private MiscRecordService	miscRecordService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<MiscRecord> records;

		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());

		records = hw.getCurricula().getMiscRecord();

		result = new ModelAndView("miscRecord/handyWorker/list");
		result.addObject("miscRecord", records);
		result.addObject("requestURI", "miscRecord/handyWorker/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		MiscRecord record;

		record = this.miscRecordService.create();
		result = this.createEditModelAndView(record);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int recordId) {
		ModelAndView result;
		MiscRecord record;

		record = this.miscRecordService.findOne(recordId);
		Assert.notNull(record);
		result = this.createEditModelAndView(record);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MiscRecord miscRecord, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(miscRecord);
		else
			try {
				this.miscRecordService.save(miscRecord);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(miscRecord, "miscRecord.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final MiscRecord record, final BindingResult binding) {
		ModelAndView result;

		try {
			this.miscRecordService.delete(record);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(record, "miscRecord.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final MiscRecord record) {
		ModelAndView result;

		result = this.createEditModelAndView(record, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final MiscRecord record, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("miscRecord/handyWorker/edit");
		result.addObject("miscRecord", record);
		result.addObject("message", messageCode);
		return result;
	}

}
