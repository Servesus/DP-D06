
package controllers.handyWorker;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

		result = new ModelAndView("miscRecord/list");
		result.addObject("miscRecord", records);
		result.addObject("requestURI", "miscRecord/handyWorker/list.do");

		return result;
	}

}
