
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
	private FixUpTaskService	fixUpTaskService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


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

	@RequestMapping(value = "/customer/accept", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;

		application = this.applicationService.findOne(applicationId);
		Assert.notNull(application);
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

		application = this.applicationService.findOne(applicationId);
		Assert.notNull(application);
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

		application = this.applicationService.findOne(applicationId);

		result = new ModelAndView("application/customer/show");
		result.addObject("application", application);
		result.addObject("requestURI", "application/customer/show.do");

		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application) {
		ModelAndView result;

		result = this.createEditModelAndView(application, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("application/customer/accept");
		result.addObject("application", application);
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

		final Actor user = this.actorService.getActorLogged();
		final HandyWorker handyWorker = this.handyWorkerService.findOne(user.getId());

		applications = handyWorker.getApplications();

		result = new ModelAndView("application/handyWorker/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/handyWorker/list.do");

		return result;
	}

}
