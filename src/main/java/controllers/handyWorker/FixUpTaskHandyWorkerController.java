
package controllers.handyWorker;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.FixUpTaskService;
import controllers.AbstractController;
import domain.Customer;
import domain.FixUpTask;

@Controller
@RequestMapping("/fixUpTask/handyWorker")
public class FixUpTaskHandyWorkerController extends AbstractController {

	@Autowired
	private FixUpTaskService	fixUpTaskService;
	@Autowired
	private CustomerService		customerService;


	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ModelAndView findAll() {
		ModelAndView result;

		Collection<FixUpTask> fixUpTaskCollection = new ArrayList<FixUpTask>();
		fixUpTaskCollection = this.fixUpTaskService.findAll();

		result = new ModelAndView("fixUpTask/handyWorker/findAll");
		result.addObject("fixUpTasks", fixUpTaskCollection);
		return result;
	}

	@RequestMapping(value = "/relationshipsHandyWorker", method = RequestMethod.GET)
	public ModelAndView findAllRelationships() {
		ModelAndView result;

		Collection<String> collection = new ArrayList<String>();
		collection = this.customerService.CustomerPorFixUpTask();

		result = new ModelAndView("fixUpTask/handyWorker/relationshipsHandyWorker");
		result.addObject("fixCustomers", collection);
		return result;
	}

	@RequestMapping(value = "/customersHandyWorker", method = RequestMethod.GET)
	public ModelAndView findAllCustomers() {
		ModelAndView result;

		Collection<Customer> collection = new ArrayList<Customer>();
		collection = this.customerService.findAll();

		result = new ModelAndView("fixUpTask/handyWorker/customersHandyWorker");
		result.addObject("customers", collection);
		return result;
	}

}
