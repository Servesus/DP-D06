
package controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import controllers.AbstractController;
import domain.Customer;

@Controller
@RequestMapping("/customer/register.do")
public class RegisterCustomerController extends AbstractController {

	@Autowired
	private CustomerService	customerService;


	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		Customer customer;

		customer = this.customerService.create();

		result = new ModelAndView("customer/register");
		result.addObject("customer", customer);
		result.addObject("requestURI", "customer/register.do");

		return result;
	}

}
