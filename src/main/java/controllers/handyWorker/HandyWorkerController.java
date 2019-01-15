
package controllers.handyWorker;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CurriculaService;
import services.HandyWorkerService;
import controllers.AbstractController;
import domain.Actor;
import domain.Application;
import domain.Curricula;
import domain.HandyWorker;
import domain.Profile;

@Controller
@RequestMapping("/handyWorker")
public class HandyWorkerController extends AbstractController {

	@Autowired
	private ActorService		actorService;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private CurriculaService	curriculaService;


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
				Curricula curricula = this.curriculaService.create();
				curricula = this.curriculaService.save(curricula);
				handyWorker.setCurricula(curricula);
				this.handyWorkerService.save(handyWorker);
				result = new ModelAndView("redirect:security/login.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(handyWorker, "handyWorker.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/handyWorker/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;

		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());
		Assert.notNull(hw);
		result = this.editModelAndView(hw);

		return result;
	}

	@RequestMapping(value = "/handyWorker/edit", method = RequestMethod.POST, params = "update")
	public ModelAndView update(@Valid final HandyWorker handyWorker, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.editModelAndView(handyWorker);
		else
			try {
				this.handyWorkerService.save(handyWorker);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.editModelAndView(handyWorker, oops.getLocalizedMessage()); //"handyWorker.commit.error"
			}
		return result;
	}

	protected ModelAndView editModelAndView(final HandyWorker handyWorker) {
		ModelAndView result;
		result = this.editModelAndView(handyWorker, null);
		return result;
	}

	protected ModelAndView editModelAndView(final HandyWorker handyWorker, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("handyWorker/handyWorker/edit");
		result.addObject("handyWorker", handyWorker);
		result.addObject("messageCode", messageCode);

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

		profiles = handyWorker.getProfiles();
		applications = handyWorker.getApplications();

		result = new ModelAndView("handyWorker/create");
		result.addObject("handyWorker", handyWorker);
		result.addObject("applications", applications);
		result.addObject("profiles", profiles);
		result.addObject("message", messageCode);

		return result;
	}
}
