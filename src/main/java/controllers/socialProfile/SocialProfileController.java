
package controllers.socialProfile;

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
import services.ProfileService;
import controllers.AbstractController;
import domain.Actor;
import domain.Profile;

@Controller
@RequestMapping("socialProfile")
public class SocialProfileController extends AbstractController {

	@Autowired
	private ProfileService	profileService;

	@Autowired
	private ActorService	actorService;


	@RequestMapping(value = "handyWorker,customer,admin,referee/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Profile> profiles;

		final Actor user = this.actorService.getActorLogged();

		profiles = user.getProfiles();

		result = new ModelAndView("socialProfile/handyWorker,customer,admin,referee/list");
		result.addObject("socialProfiles", profiles);
		result.addObject("requestURI", "socialProfile/handyWorker,customer,admin,referee/list.do");

		return result;
	}

	@RequestMapping(value = "handyWorker,customer,admin,referee/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Profile profile;

		profile = this.profileService.create();
		result = this.createEditModelAndView(profile);

		return result;
	}

	@RequestMapping(value = "handyWorker,customer,admin,referee/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int socialProfileId) {
		ModelAndView result;
		Profile profile;
		Actor user;

		user = this.actorService.getActorLogged();
		profile = this.profileService.findOne(socialProfileId);

		if (user.getProfiles().contains(profile))
			result = this.createEditModelAndView(profile);
		else
			result = new ModelAndView("redirect:/misc/403");
		return result;

	}
	@RequestMapping(value = "handyWorker,customer,admin,referee/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Profile profile, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(profile);
		else
			try {
				this.profileService.save(profile);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(profile, "profile.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "handyWorker,customer,admin,referee/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Profile profile, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(profile);
		else
			try {
				this.profileService.delete(profile);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(profile, "profile.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Profile profile) {
		ModelAndView result;

		result = this.createEditModelAndView(profile, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Profile profile, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("socialProfile/handyWorker,customer,admin,referee/edit");
		result.addObject("socialProfile", profile);
		result.addObject("message", messageCode);
		return result;
	}
}
