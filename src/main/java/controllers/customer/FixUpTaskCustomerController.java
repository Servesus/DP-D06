
package controllers.customer;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CategoryService;
import services.CustomerService;
import services.FixUpTaskService;
import services.WarrantyService;
import controllers.AbstractController;
import domain.Actor;
import domain.Category;
import domain.Customer;
import domain.FixUpTask;
import domain.Warranty;

@Controller
@RequestMapping("fixUpTask/customer")
public class FixUpTaskCustomerController extends AbstractController {

	@Autowired
	private FixUpTaskService	fixUpTaskService;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private WarrantyService		warrantyService;
	@Autowired
	private CategoryService		categoryService;


	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ModelAndView findAll() {
		ModelAndView result;
		Customer customer;
		final Actor user = this.actorService.getActorLogged();

		Collection<FixUpTask> fixUpTaskCollection = new ArrayList<FixUpTask>();

		customer = this.customerService.findOne(user.getId());
		Assert.notNull(customer);
		fixUpTaskCollection = this.fixUpTaskService.getCustomerFixUpTasks();
		Assert.notNull(fixUpTaskCollection);

		result = new ModelAndView("fixUpTask/customer/findAll");
		result.addObject("fixUpTasks", fixUpTaskCollection);
		return result;
	}

	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public ModelAndView findAll(@RequestParam final int fixUpTaskId) {
		ModelAndView result;
		final String language = LocaleContextHolder.getLocale().getLanguage();
		final FixUpTask fix = this.fixUpTaskService.findOne(fixUpTaskId);
		Customer customer;
		Actor user;

		user = this.actorService.getActorLogged();
		customer = this.customerService.findOne(user.getId());

		if (fix == null || !(fix.getCustomer().equals(customer)))
			result = new ModelAndView("redirect:/misc/403");
		else {
			result = new ModelAndView("fixUpTask/customer/findOne");
			result.addObject("fixUpTask", fix);
			result.addObject("lang", language);
		}
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
		Customer customer;
		Actor user;

		user = this.actorService.getActorLogged();
		customer = this.customerService.findOne(user.getId());
		final FixUpTask fix = this.fixUpTaskService.findOne(fixUpTaskId);

		if (fix == null || !(fix.getCustomer().equals(customer)))
			result = new ModelAndView("redirect:/misc/403");
		else
			result = this.createEditModelAndView(fix);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
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
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final FixUpTask fixUpTask, final BindingResult binding) {
		ModelAndView result;

		try {
			this.fixUpTaskService.delete(fixUpTask);
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
		Collection<Warranty> warranties;
		warranties = this.warrantyService.findAll();
		Collection<Category> categories;
		categories = this.categoryService.findAll();
		result = new ModelAndView("fixUpTask/customer/create");
		result.addObject("message", messageCode);
		result.addObject("warranties", warranties);
		result.addObject("categories", categories);
		result.addObject("fixUpTask", fix);
		return result;
	}

}
