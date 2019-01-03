
package controllers.handyWorker;

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

import services.HandyWorkerService;
import controllers.AbstractController;
import domain.Application;
import domain.HandyWorker;
import domain.Phase;
import domain.Profile;

@Controller
@RequestMapping("/handyWorker")
public class HandyWorkerController extends AbstractController {

	@Autowired
	private HandyWorkerService	handyWorkerService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		HandyWorker handyWorker;

		handyWorker = this.handyWorkerService.create();
		result = this.createEditModelAndView(handyWorker);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final HandyWorker handyWorker, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(handyWorker);
		else
			try {
				this.handyWorkerService.save(handyWorker);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(handyWorker, "handyWorker.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/handyWorker/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int handyWorkerId) {
		ModelAndView result;
		HandyWorker handyWorker;

		handyWorker = this.handyWorkerService.findOne(handyWorkerId);
		Assert.notNull(handyWorker);
		result = this.createEditModelAndView(handyWorker);

		return result;
	}

	protected ModelAndView createEditModelAndView(final HandyWorker handyWorker) {
		ModelAndView result;
		result = this.createEditModelAndView(handyWorker, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final HandyWorker handyWorker, final String messageCode) {
		ModelAndView result;
		Collection<Profile> profiles;
		Collection<Application> applications;
		Collection<Phase> phases;

		profiles = handyWorker.getProfiles();
		applications = handyWorker.getApplications();
		phases = handyWorker.getPhases();

		result = new ModelAndView("handyWorker/create");
		result.addObject("handyWorker", handyWorker);
		result.addObject("applications", applications);
		result.addObject("phases", phases);
		result.addObject("profiles", profiles);
		result.addObject("message", messageCode);

		return result;
	}
}
