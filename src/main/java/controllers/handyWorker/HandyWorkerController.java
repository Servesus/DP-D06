
package controllers.handyWorker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.HandyWorkerService;
import controllers.AbstractController;
import domain.HandyWorker;

@Controller
@RequestMapping("/handyWorker")
public class HandyWorkerController extends AbstractController {

	@Autowired
	private HandyWorkerService	handyWorkerService;


	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		HandyWorker handyWorker;

		handyWorker = this.handyWorkerService.create();

		result = new ModelAndView("handyWorker/register");
		result.addObject("handyWorker", handyWorker);
		result.addObject("requestURI", "handyWorker/register.do");

		return result;
	}

}
