
package controllers.referee;

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
import services.RefereeService;
import controllers.AbstractController;
import domain.Actor;
import domain.Profile;
import domain.Referee;
import domain.Report;

@Controller
@RequestMapping("referee")
public class RegisterRefereeController extends AbstractController {

	@Autowired
	private RefereeService	refereeService;
	@Autowired
	private ActorService	actorService;


	@RequestMapping(value = "/administrator/create", method = RequestMethod.GET)
	public ModelAndView createReferee() {
		ModelAndView result;
		Referee referee;

		referee = this.refereeService.create();
		result = this.createEditModelAndView(referee);

		return result;
	}

	@RequestMapping(value = "/administrator/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Referee referee, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(referee);
		else
			try {
				this.refereeService.save(referee);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(referee, "referee.commit.error");
			}
		return result;
	}
	@RequestMapping(value = "/referee/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Referee referee;
		final Actor user = this.actorService.getActorLogged();

		referee = this.refereeService.findOne(user.getId());
		Assert.notNull(referee);
		result = this.createEditModelAndView2(referee);

		return result;
	}
	@RequestMapping(value = "/referee/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveInformation(@Valid final Referee referee, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView2(referee);
		else
			try {
				this.refereeService.save(referee);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView2(referee, "referee.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Referee referee) {
		ModelAndView result;
		result = this.createEditModelAndView(referee, null);
		return result;
	}
	protected ModelAndView createEditModelAndView2(final Referee referee) {
		ModelAndView result;
		result = this.createEditModelAndView2(referee, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Referee referee, final String messageCode) {
		ModelAndView result;
		Collection<Report> reports;
		Collection<Profile> profiles;

		reports = referee.getReports();
		profiles = referee.getProfiles();

		result = new ModelAndView("referee/administrator/create");
		result.addObject("referee", referee);
		result.addObject("reports", reports);
		result.addObject("profiles", profiles);
		result.addObject("message", messageCode);

		return result;
	}
	protected ModelAndView createEditModelAndView2(final Referee referee, final String messageCode) {
		ModelAndView result;
		Collection<Report> reports;
		Collection<Profile> profiles;

		reports = referee.getReports();
		profiles = referee.getProfiles();

		result = new ModelAndView("referee/referee/edit");
		result.addObject("referee", referee);
		result.addObject("reports", reports);
		result.addObject("profiles", profiles);
		result.addObject("message", messageCode);

		return result;
	}
}
