
package controllers.complaint;

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
import services.ComplaintService;
import services.CustomerService;
import services.HandyWorkerService;
import controllers.AbstractController;
import domain.Actor;
import domain.Application;
import domain.Complaint;
import domain.Customer;
import domain.FixUpTask;
import domain.HandyWorker;

@Controller
@RequestMapping("complaint")
public class ComplaintController extends AbstractController {

	@Autowired
	private ComplaintService	complaintService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	@RequestMapping(value = "/customer/list", method = RequestMethod.GET)
	public ModelAndView listCustomer() {
		ModelAndView result;
		Collection<Complaint> complaints;

		final Actor user = this.actorService.getActorLogged();
		final Customer customer = this.customerService.findOne(user.getId());

		complaints = customer.getComplaints();

		result = new ModelAndView("complaint/customer/list");
		result.addObject("complaints", complaints);
		result.addObject("requestURI", "complaint/customer/list.do");
		return result;
	}

	@RequestMapping(value = "/handyWorker/list", method = RequestMethod.GET)
	public ModelAndView listHandyWorker() {
		ModelAndView result;
		final Collection<Complaint> complaints = new ArrayList<>();
		Collection<Application> applications;
		final Collection<FixUpTask> fixUpTasks = new ArrayList<>();

		final Actor user = this.actorService.getActorLogged();
		final HandyWorker handyWorker = this.handyWorkerService.findOne(user.getId());

		applications = handyWorker.getApplications();
		for (final Application a : applications)
			if (a.getStatus() == 1)
				fixUpTasks.add(a.getFixUpTask());
		for (final FixUpTask f : fixUpTasks)
			complaints.addAll(f.getComplaints());

		result = new ModelAndView("complaint/handyWorker/listHandyWorker");
		result.addObject("complaints", complaints);
		result.addObject("requestURI", "complaint/handyWorker/listHandyWorker.do");
		return result;
	}

	@RequestMapping(value = "/referee/listSelfAssigned", method = RequestMethod.GET)
	public ModelAndView listRefereeAssigned() {
		ModelAndView result;
		Collection<Complaint> complaints;

		complaints = this.complaintService.getComplaintSelfAssigned();

		result = new ModelAndView("complaint/referee/listSelfAssigned");
		result.addObject("complaints", complaints);
		result.addObject("requestURI", "complaint/referee/listSelfAssigned.do");
		return result;
	}

	@RequestMapping(value = "/referee/listAll", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView result;
		Collection<Complaint> complaints;
		final Collection<Complaint> res = new ArrayList<Complaint>();

		complaints = this.complaintService.findAll();
		res.addAll(complaints);

		for (final Complaint c : complaints)
			if (!c.getReports().isEmpty())
				res.remove(c);
		result = new ModelAndView("complaint/referee/listAll");
		result.addObject("complaints", res);
		result.addObject("requestURI", "complaint/referee/listAll.do");
		return result;
	}

	@RequestMapping(value = "/customer/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int complaintId) {
		ModelAndView result;
		Complaint complaint;

		complaint = this.complaintService.findOne(complaintId);

		result = new ModelAndView("complaint/customer/show");
		result.addObject("complaint", complaint);
		result.addObject("requestURI", "complaint/customer/show.do");
		return result;
	}

	@RequestMapping(value = "/customer/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int fixUpTaskId) {
		ModelAndView result;
		Complaint complaint;

		complaint = this.complaintService.create(fixUpTaskId);
		result = this.createEditModelAndView(complaint);

		return result;
	}

	@RequestMapping(value = "/customer/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Complaint complaint, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(complaint);
		else
			try {
				this.complaintService.save(complaint);
				result = new ModelAndView("redirect:list.do?=" + complaint.getFixUpTasks().getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(complaint, "complaint.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Complaint complaint) {
		ModelAndView result;

		result = this.createEditModelAndView(complaint, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Complaint complaint, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("complaint/customer/edit");
		result.addObject("complaint", complaint);
		result.addObject("message", messageCode);

		return result;
	}
}
