
package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.AdministratorService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Category;

@Controller
@RequestMapping("/profile")
public class ProfileAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;


	@RequestMapping(value = "/action-1", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		Administrator administrador;
		final Collection<String> categories = new ArrayList<>();
		Collection<Category> categoriesCollection = new ArrayList<>();

		administrador = this.administratorService.findOne(LoginService.getPrincipal().getId());
		categoriesCollection = administrador.getCategories();
		for (final Category c : categoriesCollection)
			categories.add(c.getName());

		result = new ModelAndView("profile/action-1");
		result.addObject("administrator.name", administrador.getName());
		result.addObject("administrator.surname", administrador.getSurname());
		result.addObject("administrator.email", administrador.getEmail());
		result.addObject("administrator.phoneNumber", administrador.getPhoneNumber());
		result.addObject("administrator.id", administrador.getId());
		result.addObject("administrator.categories", categories);

		return result;
	}
}
