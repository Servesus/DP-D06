
package controllers.referee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RefereeService;
import controllers.AbstractController;
import domain.Referee;

@Controller
@RequestMapping("/referee/administrator")
public class RegisterRefereeController extends AbstractController {

	@Autowired
	private RefereeService	refereeService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createReferee() {
		ModelAndView result;
		Referee referee;

		referee = this.refereeService.create();

		result = new ModelAndView("referee/create");
		result.addObject("referee", referee);
		result.addObject("requestURI", "referee/administrator/create.do");

		return result;
	}

}
