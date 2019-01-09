
package controllers.referee;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RefereeService;
import controllers.AbstractController;
import domain.Profile;
import domain.Referee;
import domain.Report;

@Controller
@RequestMapping("referee")
public class RegisterRefereeController extends AbstractController {

	@Autowired
	private RefereeService	refereeService;


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

	//	@RequestMapping(value = "/referee/edit", method = RequestMethod.GET)
	//	public ModelAndView edit(@RequestParam final int refereeId) {
	//		ModelAndView result;
	//		Referee referee;
	//
	//		referee = this.refereeService.findOne(refereeId);
	//		Assert.notNull(referee);
	//		result = this.createEditModelAndView(referee);
	//
	//		return result;
	//	}

	protected ModelAndView createEditModelAndView(final Referee referee) {
		ModelAndView result;
		result = this.createEditModelAndView(referee, null);
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
}
