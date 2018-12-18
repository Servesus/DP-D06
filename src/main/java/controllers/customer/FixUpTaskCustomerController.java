
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
import services.FixUpTaskService;
import controllers.AbstractController;
import domain.Customer;
import domain.FixUpTask;

@Controller
@RequestMapping("/fixUpTask/customer")
public class FixUpTaskCustomerController extends AbstractController {

	@Autowired
	private FixUpTaskService	fixUpTaskService;
	@Autowired
	private CustomerService		customerService;


	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ModelAndView findAll() {
		ModelAndView result;
		Customer customer;

		final Collection<FixUpTask> fixUpTasks = new ArrayList<FixUpTask>();
		Collection<FixUpTask> fixUpTaskCollection = new ArrayList<FixUpTask>();

		customer = this.customerService.findOne(LoginService.getPrincipal().getId());
		fixUpTaskCollection = customer.getFixUpTasks();
		fixUpTasks.addAll(fixUpTaskCollection);

		result = new ModelAndView("fixUpTask/customer/findAll");
		result.addObject("fixUpTasks", fixUpTasks);
		return result;
	}

	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public ModelAndView findAll(final Integer rowId) {
		ModelAndView result;
		final FixUpTask fix = this.fixUpTaskService.findOne(rowId);

		result = new ModelAndView("fixUpTask/customer/findAll");
		result.addObject("fixUpTask.startDate", fix.getStartDate());
		result.addObject("fixUpTask.description", fix.getDescription());
		result.addObject("fixUpTask.address", fix.getAddress());
		result.addObject("fixUpTask.maxPrice", fix.getMaxPrice());
		result.addObject("fixUpTask.estimatedDate", fix.getEstimatedDate());
		result.addObject("fixUpTask.category", fix.getCategory());
		result.addObject("fixUpTask.warranty", fix.getWarranty());
		result.addObject("fixUpTask.complaints", fix.getComplaints());
		result.addObject("fixUpTask.phases", fix.getPhases());
		return result;
	}
}
