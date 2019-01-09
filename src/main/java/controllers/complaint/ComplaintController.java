
package controllers.complaint;

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
import controllers.AbstractController;
import domain.Actor;
import domain.Complaint;
import domain.Customer;

@Controller
@RequestMapping("complaint")
public class ComplaintController extends AbstractController {

	@Autowired
	private ComplaintService	complaintService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CustomerService		customerService;


	@RequestMapping(value = "/customer/list", method = RequestMethod.GET)
	public ModelAndView list() {
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

	@RequestMapping(value = "/customer/edit", method = RequestMethod.GET)
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
				result = new ModelAndView("redirect:confirm.do?=" + complaint.getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(complaint, "complaint.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/customer/confirm", method = RequestMethod.POST, params = "confirm")
	public ModelAndView confirm(@Valid final Complaint complaint) {
		ModelAndView result;
		Complaint confirmed;

		confirmed = this.complaintService.save(complaint);

		result = new ModelAndView("redirect:show.do?=" + confirmed.getId());

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
