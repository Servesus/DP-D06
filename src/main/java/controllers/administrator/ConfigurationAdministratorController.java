
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdministratorService;
import services.ConfigurationService;
import controllers.AbstractController;
import domain.Actor;
import domain.Administrator;
import domain.Configuration;

@Controller
@RequestMapping("configuration/administrator")
public class ConfigurationAdministratorController extends AbstractController {

	@Autowired
	private ActorService			actorService;
	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private ConfigurationService	configurationService;


	//private final String			pageName	= this.configurationService.findAll().get(0).getPageName();

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;

		final Actor user = this.actorService.getActorLogged();
		final Administrator admin = this.administratorService.findOne(user.getId());
		Assert.notNull(admin);

		result = this.editModelAndView(this.configurationService.findAll().get(0));

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView update(@Valid final Configuration config, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.editModelAndView(config);
		else
			try {
				this.configurationService.save(config);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.editModelAndView(config, "configuration.edit.error"); //"Administrator.commit.error"
			}
		return result;
	}
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView result;

		final Configuration config = this.configurationService.findAll().get(0);

		result = new ModelAndView("configuration/administrator/show");
		result.addObject("configuration", config);

		return result;
	}

	protected ModelAndView editModelAndView(final Configuration config) {
		ModelAndView result;
		result = this.editModelAndView(config, null);
		return result;
	}

	protected ModelAndView editModelAndView(final Configuration config, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("configuration/administrator/edit");
		result.addObject("configuration", config);
		result.addObject("messageCode", messageCode);

		return result;
	}
}
