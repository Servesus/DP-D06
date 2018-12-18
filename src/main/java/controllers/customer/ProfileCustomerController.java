
package controllers.customer;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.CustomerService;
import controllers.AbstractController;
import domain.Complaint;
import domain.CreditCard;
import domain.Customer;
import domain.FixUpTask;

@Controller
@RequestMapping("/profile")
public class ProfileCustomerController extends AbstractController {

	@Autowired
	private CustomerService	customerService;


	@RequestMapping(value = "/action-1", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		Customer customer;
		final Collection<Complaint> complaints = new ArrayList<Complaint>();
		Collection<Complaint> complaintsCollection = new ArrayList<Complaint>();
		final Collection<CreditCard> creditCards = new ArrayList<CreditCard>();
		Collection<CreditCard> creditCardCollection = new ArrayList<CreditCard>();
		final Collection<FixUpTask> fixUpTasks = new ArrayList<FixUpTask>();
		Collection<FixUpTask> fixUpTaskCollection = new ArrayList<FixUpTask>();

		customer = this.customerService.findOne(LoginService.getPrincipal().getId());
		complaintsCollection = customer.getComplaints();
		creditCardCollection = customer.getCreditCards();
		fixUpTaskCollection = customer.getFixUpTasks();
		creditCards.addAll(creditCardCollection);
		complaints.addAll(complaintsCollection);
		fixUpTasks.addAll(fixUpTaskCollection);

		result = new ModelAndView("profile/action-1");
		result.addObject("customer.name", customer.getName());
		result.addObject("customer.surname", customer.getSurname());
		result.addObject("customer.email", customer.getEmail());
		result.addObject("customer.phoneNumber", customer.getPhoneNumber());
		result.addObject("customer.id", customer.getId());
		result.addObject("customer.creditCards", complaints);
		result.addObject("customer.complaints", complaints);
		result.addObject("customer.fixUpTasks", fixUpTasks);

		return result;
	}
}
