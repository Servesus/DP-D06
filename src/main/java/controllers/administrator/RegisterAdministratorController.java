
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Profile;

@Controller
@RequestMapping("administrator")
public class RegisterAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;


	//Para registrarse como administador, primero un admin llama al create del servicio
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Administrator administrator;

		administrator = this.administratorService.create();
		result = this.createEditModelAndView(administrator);

		return result;
	}

	//Luego hay que rellenar el formulario y guardarlo en la base de datos
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(administrator);
		else
			try {
				this.administratorService.save(administrator);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(administrator, "administrator.commit.error");
			}
		return result;
	}

	/*
	 * @RequestMapping(value = "/edit", method = RequestMethod.GET)
	 * public ModelAndView edit(@RequestParam final int administratorId) {
	 * ModelAndView result;
	 * Administrator administrator;
	 * 
	 * administrator = this.administratorService.findOne(administratorId);
	 * Assert.notNull(administrator);
	 * result = this.createEditModelAndView(administrator);
	 * 
	 * return result;
	 * }
	 */

	protected ModelAndView createEditModelAndView(final Administrator administrator) {
		ModelAndView result;
		result = this.createEditModelAndView(administrator, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Administrator administrator, final String messageCode) {
		ModelAndView result;
		Collection<Profile> profiles;
		profiles = administrator.getProfiles();

		result = new ModelAndView("administrator/create");
		result.addObject("administrator", administrator);
		result.addObject("profiles", profiles);
		result.addObject("message", messageCode);

		return result;
	}
}
