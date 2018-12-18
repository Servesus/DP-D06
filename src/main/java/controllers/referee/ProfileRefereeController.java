
package controllers.referee;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.RefereeService;
import controllers.AbstractController;
import domain.Referee;
import domain.Report;

@Controller
@RequestMapping("/profile")
public class ProfileRefereeController extends AbstractController {

	@Autowired
	private RefereeService	refereeService;


	@RequestMapping(value = "/action-1", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		Referee referee;

		final Collection<Report> reports = new ArrayList<Report>();
		Collection<Report> reportsCollection = new ArrayList<Report>();

		referee = this.refereeService.findOne(LoginService.getPrincipal().getId());
		reportsCollection = referee.getReports();

		reports.addAll(reportsCollection);

		result = new ModelAndView("profile/action-1");
		result.addObject("referee.name", referee.getName());
		result.addObject("referee.surname", referee.getSurname());
		result.addObject("referee.email", referee.getEmail());
		result.addObject("referee.phoneNumber", referee.getPhoneNumber());
		result.addObject("referee.id", referee.getId());
		result.addObject("referee.reports", reports);

		return result;
	}
}
