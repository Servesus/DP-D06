
package controllers.handyWorker;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.HandyWorkerService;
import controllers.AbstractController;
import domain.Application;
import domain.HandyWorker;
import domain.Phase;

@Controller
@RequestMapping("/profile")
public class ProfileHandyWorkerController extends AbstractController {

	@Autowired
	private HandyWorkerService	handyWorkerService;


	@RequestMapping(value = "/action-1", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		HandyWorker handyWorker;

		final Collection<Application> applications = new ArrayList<Application>();
		Collection<Application> applicationsCollection = new ArrayList<Application>();
		final Collection<Phase> phases = new ArrayList<Phase>();
		Collection<Phase> phasesCollection = new ArrayList<Phase>();

		handyWorker = this.handyWorkerService.findOne(LoginService.getPrincipal().getId());
		applicationsCollection = handyWorker.getApplications();
		phasesCollection = handyWorker.getPhases();

		applications.addAll(applicationsCollection);
		phases.addAll(phasesCollection);

		result = new ModelAndView("profile/action-1");
		result.addObject("handyWorker.name", handyWorker.getName());
		result.addObject("handyWorker.surname", handyWorker.getSurname());
		result.addObject("handyWorker.email", handyWorker.getEmail());
		result.addObject("handyWorker.phoneNumber", handyWorker.getPhoneNumber());
		result.addObject("handyWorker.id", handyWorker.getId());
		result.addObject("handyWorker.applications", applications);
		result.addObject("handyWorker.phases", phases);

		return result;
	}
}
