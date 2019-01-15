
package controllers.customer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CustomerService;
import controllers.AbstractController;
import domain.Actor;
import domain.Customer;
import domain.Profile;

@Controller
@RequestMapping("customer")
public class RegisterCustomerController extends AbstractController {

	@Autowired
	private CustomerService	customerService;
	@Autowired
	private ActorService	actorService;


	//Para registrarse como cliente, primero se llama al create del servicio
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Customer customer;

		customer = this.customerService.create();
		result = this.createEditModelAndView(customer);

		return result;
	}

	//Luego hay que rellenar el formulario y guardarlo en la base de datos
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Customer customer, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(customer);
		else
			try {
				this.customerService.save(customer);
				result = new ModelAndView("redirect:security/login.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(customer, "customer.commit.error");
			}
		return result;
	}
	@RequestMapping(value = "/customer/editPersonalData", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		final Actor user = this.actorService.getActorLogged();
		Customer customer;

		customer = this.customerService.findOne(user.getId());
		Assert.notNull(customer);
		result = new ModelAndView("customer/customer/editPersonalData");
		result.addObject("customer", customer);

		return result;
	}
	@RequestMapping(value = "/customer/editPersonalData", method = RequestMethod.POST, params = "save")
	public ModelAndView savePersonalData(@Valid final Customer customer, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView2(customer, binding.getAllErrors().get(0).getDefaultMessage());
		else
			try {
				this.customerService.save(customer);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView2(customer, "customer.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Customer customer) {
		ModelAndView result;
		result = this.createEditModelAndView(customer, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Customer customer, final String messageCode) {
		ModelAndView result;
		Collection<Profile> profiles;

		profiles = customer.getProfiles();

		result = new ModelAndView("customer/create");
		result.addObject("customer", customer);
		result.addObject("profiles", profiles);
		result.addObject("message", messageCode);

		return result;
	}
	protected ModelAndView createEditModelAndView2(final Customer customer) {
		ModelAndView result;
		result = this.createEditModelAndView2(customer, null);
		return result;
	}
	protected ModelAndView createEditModelAndView2(final Customer customer, final String messageCode) {
		ModelAndView result;
		Collection<Profile> profiles;

		profiles = customer.getProfiles();

		result = new ModelAndView("customer/customer/editPersonalData");
		result.addObject("customer", customer);
		result.addObject("profiles", profiles);
		result.addObject("message", messageCode);

		return result;
	}
}
