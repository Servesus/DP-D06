
package controllers.application;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ApplicationService;
import services.ConfigurationService;
import services.CustomerService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import services.MessageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Application;
import domain.Configuration;
import domain.Customer;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Message;

@Controller
@RequestMapping("application")
public class ApplicationController extends AbstractController {

	@Autowired
	private ApplicationService		applicationService;

	@Autowired
	private FixUpTaskService		fixUpTaskService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private HandyWorkerService		handyWorkerService;

	@Autowired
	private MessageService			messageService;

	@Autowired
	private CustomerService			customerService;

	@Autowired
	private ConfigurationService	configurationService;


	@RequestMapping(value = "/customer/list", method = RequestMethod.GET)
	public ModelAndView listCustomer(@RequestParam final int fixUpTaskId) {
		ModelAndView result;
		Collection<Application> applications;
		final Configuration configuration = this.configurationService.findAll().get(0);

		final FixUpTask fixUpTask = this.fixUpTaskService.findOne(fixUpTaskId);

		if (this.fixUpTaskService.getCustomerFixUpTasks().contains(fixUpTask)) {
			applications = fixUpTask.getApplications();
			result = new ModelAndView("application/customer/list");
			result.addObject("applications", applications);
			result.addObject("priceVAT", configuration.getVATPercent());
			result.addObject("requestURI", "application/customer/list.do");
		} else
			result = new ModelAndView("redirect:/misc/403");
		return result;
	}
	@RequestMapping(value = "/customer/accept", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;
		int customerId;
		Customer customer;

		customerId = this.actorService.getActorLogged().getId();
		customer = this.customerService.findOne(customerId);

		application = this.applicationService.findOne(applicationId);

		if (application == null || !(application.getFixUpTask().getCustomer().equals(customer)))
			result = new ModelAndView("redirect:/misc/403");
		else
			result = this.createEditModelAndView(application);
		return result;
	}
	@RequestMapping(value = "/customer/accept", method = RequestMethod.POST, params = "save")
	public ModelAndView accept(@Valid final Application application, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(application);
		else
			try {
				this.applicationService.acceptApplication(application);
				final Message message = this.messageService.create();
				final Collection<Actor> actors = new ArrayList<Actor>();
				actors.add(application.getHandyWorker());
				actors.add(application.getFixUpTask().getCustomer());
				message.setRecipient(actors);
				message.setPriority(0);
				message.setSubject("Applcation changed \n Una solicitud ha cambiado");
				message.setBody("An application status has been changed \n El estado de una solicitud ha cambiado");
				this.messageService.save(message);
				result = new ModelAndView("redirect:/application/customer/show.do?applicationId=" + application.getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(application);
			}
		return result;
	}

	@RequestMapping(value = "/customer/reject", method = RequestMethod.GET)
	public ModelAndView reject(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;
		int customerId;
		Customer customer;

		customerId = this.actorService.getActorLogged().getId();
		customer = this.customerService.findOne(customerId);
		application = this.applicationService.findOne(applicationId);

		if (application == null || !(application.getFixUpTask().getCustomer().equals(customer)))
			result = new ModelAndView("redirect:/misc/403");
		else
			result = this.createEditModelAndView2(application);

		return result;
	}
	@RequestMapping(value = "/customer/reject", method = RequestMethod.POST, params = "save")
	public ModelAndView reject(@Valid final Application application, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView2(application);
		else
			try {
				this.applicationService.rejectApplication(application);
				final Message message = this.messageService.create();
				final Collection<Actor> actors = new ArrayList<Actor>();
				actors.add(application.getHandyWorker());
				actors.add(application.getFixUpTask().getCustomer());
				message.setRecipient(actors);
				message.setPriority(0);
				message.setSubject("Applcation changed \n Una solicitud ha cambiado");
				message.setBody("An application status has been changed \n El estado de una solicitud ha cambiado");
				this.messageService.save(message);
				result = new ModelAndView("redirect:/application/customer/show.do?applicationId=" + application.getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(application);
			}
		return result;
	}

	@RequestMapping(value = "/customer/show", method = RequestMethod.GET)
	public ModelAndView showCustomer(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;
		int customerId;
		Customer customer;
		final Configuration configuration = this.configurationService.findAll().get(0);

		customerId = this.actorService.getActorLogged().getId();
		customer = this.customerService.findOne(customerId);

		application = this.applicationService.findOne(applicationId);

		if (application == null || !(application.getFixUpTask().getCustomer().equals(customer)))
			result = new ModelAndView("redirect:/misc/403");

		else {
			result = new ModelAndView("application/customer/show");
			result.addObject("application", application);
			result.addObject("priceVAT", configuration.getVATPercent());
			result.addObject("requestURI", "application/customer/show.do");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application) {
		ModelAndView result;

		result = this.createEditModelAndView(application, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application, final String messageCode) {
		ModelAndView result;
		final Collection<String> cards = this.configurationService.findAll().get(0).getcCardsMakes();
		result = new ModelAndView("application/customer/accept");
		result.addObject("application", application);
		result.addObject("creditCards", cards);
		result.addObject("message", messageCode);
		return result;
	}

	protected ModelAndView createEditModelAndView2(final Application application) {
		ModelAndView result;

		result = this.createEditModelAndView2(application, null);
		return result;
	}

	protected ModelAndView createEditModelAndView2(final Application application, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("application/customer/reject");
		result.addObject("application", application);
		result.addObject("message", messageCode);
		return result;
	}

	// AQUI EMPIEZA HANDY WORKER-----------------------------------------
	@RequestMapping(value = "/handyWorker/list", method = RequestMethod.GET)
	public ModelAndView listHandyWorker() {
		ModelAndView result;
		Collection<Application> applications;
		final Configuration configuration = this.configurationService.findAll().get(0);

		final Actor user = this.actorService.getActorLogged();
		final HandyWorker handyWorker = this.handyWorkerService.findOne(user.getId());

		applications = handyWorker.getApplications();

		result = new ModelAndView("application/handyWorker/list");
		result.addObject("applications", applications);
		result.addObject("priceVAT", configuration.getVATPercent());
		result.addObject("requestURI", "application/handyWorker/list.do");

		return result;
	}

	@RequestMapping(value = "/handyWorker/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int fixUpTaskId) {
		ModelAndView result;
		Application application;
		FixUpTask fixUpTask;

		application = this.applicationService.create(fixUpTaskId);
		fixUpTask = this.fixUpTaskService.findOne(fixUpTaskId);

		if (fixUpTask == null)
			result = new ModelAndView("redirect:/misc/403");
		else
			result = this.createEditModelAndViewHandyWorker(application);
		return result;
	}

	@RequestMapping(value = "/handyWorker/edit", method = RequestMethod.POST, params = "saveHandyWorker")
	public ModelAndView save(@Valid final Application application, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndViewHandyWorker(application);
		else
			try {
				this.applicationService.save(application);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndViewHandyWorker(application, "application.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/handyWorker/show", method = RequestMethod.GET)
	public ModelAndView showHandyWorker(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;
		int handyWorkerId;
		HandyWorker handyWorker;
		final Configuration configuration = this.configurationService.findAll().get(0);

		handyWorkerId = this.actorService.getActorLogged().getId();
		handyWorker = this.handyWorkerService.findOne(handyWorkerId);
		application = this.applicationService.findOne(applicationId);

		if (application == null || !(application.getHandyWorker().equals(handyWorker)))
			result = new ModelAndView("redirect:/misc/403");
		else {
			result = new ModelAndView("application/handyWorker/show");
			result.addObject("application", application);
			result.addObject("priceVAT", configuration.getVATPercent());
			result.addObject("requestURI", "application/handyWorker/show.do");
		}

		return result;
	}

	protected ModelAndView createEditModelAndViewHandyWorker(final Application application) {
		ModelAndView result;

		result = this.createEditModelAndViewHandyWorker(application, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewHandyWorker(final Application application, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("application/handyWorker/edit");
		result.addObject("application", application);
		result.addObject("message", messageCode);

		return result;
	}

}
