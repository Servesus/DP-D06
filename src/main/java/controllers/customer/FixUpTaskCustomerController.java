
package controllers.customer;

import java.util.ArrayList;
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

import security.LoginService;
import services.CustomerService;
import services.FixUpTaskService;
import controllers.AbstractController;
import domain.Application;
import domain.Complaint;
import domain.Customer;
import domain.FixUpTask;
import domain.Phase;

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
	public ModelAndView findAll(@RequestParam final int rowId) {
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

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final FixUpTask fix = this.fixUpTaskService.create();
		result = this.createEditModelAndView(fix);
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int fixUpTaskId) {
		ModelAndView result;
		final FixUpTask fix = this.fixUpTaskService.findOne(fixUpTaskId);
		Assert.notNull(fix);
		result = this.createEditModelAndView(fix);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final FixUpTask fixUpTask, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(fixUpTask);
		else
			try {
				this.fixUpTaskService.save(fixUpTask);
				result = new ModelAndView("redirect:findAll.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(fixUpTask, "fixUpTask.commit.error");
			}
		return result;
	}
	protected ModelAndView createEditModelAndView(final FixUpTask fix) {
		ModelAndView result;
		result = this.createEditModelAndView(fix, null);
		return result;
	}
	protected ModelAndView createEditModelAndView(final FixUpTask fix, final String messageCode) {
		final ModelAndView result;
		final Collection<Application> applications = fix.getApplications();
		final Collection<Complaint> complaints = fix.getComplaints();
		final Collection<Phase> phases = fix.getPhases();
		result = new ModelAndView("fixUpTask/customer/create");
		result.addObject("applications", applications);
		result.addObject("complaints", complaints);
		result.addObject("phases", phases);
		return result;
	}

}
