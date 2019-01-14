
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationService;
import domain.Configuration;

@Controller
public class MasterPageController extends AbstractController {

	@Autowired
	private ConfigurationService	configurationService;


	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView result;

		result = this.editModelAndView(this.configurationService.findAll().get(0));

		return result;
	}

	protected ModelAndView editModelAndView(final Configuration config) {
		ModelAndView result;
		result = this.editModelAndView(config, null);
		return result;
	}

	protected ModelAndView editModelAndView(final Configuration config, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView();
		result.addObject("configuration", config);
		result.addObject("messageCode", messageCode);

		return result;
	}

}
