
package controllers.application;

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

import services.ActorService;
import services.ApplicationService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import controllers.AbstractController;
import domain.Actor;
import domain.Application;
import domain.FixUpTask;
import domain.HandyWorker;

@Controller
@RequestMapping("application")
public class ApplicationController extends AbstractController {

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private HandyWorkerService	handyWorkerService;

	@Autowired
	private FixUpTaskService	fixUpTaskService;


	@RequestMapping(value = "/handyWorker/list", method = RequestMethod.GET)
	public ModelAndView listHandyWorker() {
		ModelAndView result;
		Collection<Application> applications;

		final Actor user = this.actorService.getActorLogged();
		final HandyWorker handyWorker = this.handyWorkerService.findOne(user.getId());

		applications = handyWorker.getApplications();

		result = new ModelAndView("application/handyWorker/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/handyWorker/list.do");

		return result;
	}

	@RequestMapping(value = "/customer/list", method = RequestMethod.GET)
	public ModelAndView listCustomer(@RequestParam final int fixUpTaskId) {
		ModelAndView result;
		Collection<Application> applications;

		final FixUpTask fixUpTask = this.fixUpTaskService.findOne(fixUpTaskId);

		applications = fixUpTask.getApplications();
		result = new ModelAndView("application/customer/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/customer/list.do");

		return result;
	}

	@RequestMapping(value = "/customer/show", method = RequestMethod.GET)
	public ModelAndView showCustomer(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;

		application = this.applicationService.findOne(applicationId);

		result = new ModelAndView("application/customer/show");
		result.addObject("application", application);
		result.addObject("requestURI", "application/customer/show.do");

		return result;
	}

	@RequestMapping(value = "/handyWorker/show", method = RequestMethod.GET)
	public ModelAndView showHandyWorker(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;

		application = this.applicationService.findOne(applicationId);

		result = new ModelAndView("application/handyWorker/show");
		result.addObject("application", application);
		result.addObject("requestURI", "application/handyWorker/show.do");

		return result;
	}

	@RequestMapping(value = "/handyWorker/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int fixUpTaskId) {
		ModelAndView result;
		Application application;

		application = this.applicationService.create(fixUpTaskId);
		result = this.createEditModelAndViewHandyWorker(application);

		return result;
	}

	@RequestMapping(value = "/customer/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;

		application = this.applicationService.findOne(applicationId);
		Assert.notNull(application);
		result = this.createEditModelAndViewCustomer(application);

		return result;
	}

	@RequestMapping(value = "/customer/edit", method = RequestMethod.POST, params = "saveCustomer")
	public ModelAndView saveCustomer(@Valid final Application application, @RequestParam final String comment) {
		ModelAndView result;
		Application commentAdded;

		commentAdded = this.applicationService.addComment(application, comment);

		result = new ModelAndView("/application/customer/edit");
		result.addObject("application", commentAdded);
		result.addObject("requestURI", "application/customer/edit.do?" + application.getId());

		return result;
	}

	@RequestMapping(value = "/handyWorker/save", method = RequestMethod.POST, params = "save")
	public ModelAndView saveHandyWorker(@Valid final Application application, final BindingResult binding) {
		ModelAndView result;
		final FixUpTask fixUpTask = application.getFixUpTask();
		if (binding.hasErrors())
			result = this.createEditModelAndViewHandyWorker(application);
		else
			try {
				this.applicationService.save(application);
				result = new ModelAndView("redirect:application/handyWorker/show.do?" + fixUpTask.getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndViewHandyWorker(application, "application.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/customer/accept", method = RequestMethod.GET)
	public ModelAndView accept(@Valid final Application application) {
		ModelAndView result;
		Application accepted;

		accepted = this.applicationService.acceptApplication(application);

		result = new ModelAndView("/application/customer/show");
		result.addObject("application", accepted);
		result.addObject("requestURI", "application/customer/accept.do");

		return result;
	}

	@RequestMapping(value = "/customer/reject", method = RequestMethod.GET)
	public ModelAndView reject(@Valid final Application application) {
		ModelAndView result;
		final Application rejected;

		rejected = this.applicationService.rejectApplication(application);

		result = new ModelAndView("/application/customer/show");
		result.addObject("application", rejected);
		result.addObject("requestURI", "application/customer/reject.do");

		return result;
	}

	protected ModelAndView createEditModelAndViewCustomer(final Application application) {
		ModelAndView result;

		result = this.createEditModelAndViewCustomer(application, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewCustomer(final Application application, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("application/customer/edit");
		result.addObject("application", application);
		result.addObject("message", messageCode);
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
