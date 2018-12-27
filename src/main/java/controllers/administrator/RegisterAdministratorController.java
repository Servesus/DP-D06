
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
import domain.Category;
import domain.Profile;

@Controller
@RequestMapping("/administrator")
public class RegisterAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;


	//Para registrarse como administador, primero un admin llama al create del servicio
	@RequestMapping(value = "/administrator/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Administrator administrator;

		administrator = this.administratorService.create();
		result = this.createEditModelAndView(administrator);

		return result;
	}

	//Luego hay que rellenar el formulario y guardarlo en la base de datos
	@RequestMapping(value = "/administrator/register", method = RequestMethod.POST, params = "save")
	public ModelAndView register(@Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(administrator);
		else
			try {
				this.administratorService.save(administrator);
				result = new ModelAndView("redirect:master-page");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(administrator, "administrator.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Administrator administrator) {
		ModelAndView result;
		result = this.createEditModelAndView(administrator, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Administrator administrator, final String messageCode) {
		ModelAndView result;
		Collection<Category> categories;
		Collection<Profile> profiles;

		categories = administrator.getCategories();
		profiles = administrator.getProfiles();

		result = new ModelAndView("customer/edit");
		result.addObject("categories", categories);
		result.addObject("profiles", profiles);
		result.addObject("message", messageCode);

		return result;
	}
}
