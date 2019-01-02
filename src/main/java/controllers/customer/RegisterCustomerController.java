
package controllers.customer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import controllers.AbstractController;
import domain.Complaint;
import domain.CreditCard;
import domain.Customer;
import domain.FixUpTask;
import domain.Profile;

@Controller
@RequestMapping("/customer")
public class RegisterCustomerController extends AbstractController {

	@Autowired
	private CustomerService	customerService;


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
				result = new ModelAndView("redirect:welcome/index");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(customer, "customer.commit.error");
			}
		return result;
	}
	@RequestMapping(value = "/customer/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int customerId) {
		ModelAndView result;
		Customer customer;

		customer = this.customerService.findOne(customerId);
		Assert.notNull(customer);
		result = this.createEditModelAndView(customer);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Customer customer) {
		ModelAndView result;
		result = this.createEditModelAndView(customer, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Customer customer, final String messageCode) {
		ModelAndView result;
		Collection<CreditCard> creditCards;
		Collection<Complaint> complaints;
		Collection<FixUpTask> fixUpTasks;
		Collection<Profile> profiles;

		creditCards = customer.getCreditCards();
		complaints = customer.getComplaints();
		fixUpTasks = customer.getFixUpTasks();
		profiles = customer.getProfiles();

		result = new ModelAndView("customer/customer/edit");
		result.addObject("customer", customer);
		result.addObject("creditCards", creditCards);
		result.addObject("complaints", complaints);
		result.addObject("fixUpTasks", fixUpTasks);
		result.addObject("profiles", profiles);
		result.addObject("message", messageCode);

		return result;
	}
}
