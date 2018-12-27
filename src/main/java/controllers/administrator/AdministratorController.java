
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import controllers.AbstractController;
import domain.Administrator;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createAdministrator() {
		ModelAndView result;
		Administrator administrator;

		administrator = this.administratorService.create();

		result = new ModelAndView("administrator/create");
		result.addObject("administrator", administrator);
		result.addObject("requestURI", "administrator/administrator/create.do");

		return result;
	}

}
