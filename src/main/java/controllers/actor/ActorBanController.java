
package controllers.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdministratorService;
import services.CustomerService;
import services.HandyWorkerService;
import services.RefereeService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Customer;
import domain.HandyWorker;
import domain.Referee;

@Controller
@RequestMapping("actor/administrator")
public class ActorBanController extends AbstractController {

	@Autowired
	private ActorService			actorService;

	@Autowired
	private CustomerService			customerService;

	@Autowired
	private RefereeService			refereeService;

	@Autowired
	private HandyWorkerService		handyWorkerService;

	@Autowired
	private AdministratorService	administratorService;


	@RequestMapping(value = "/suspiciouslist", method = RequestMethod.GET)
	public ModelAndView listSuspicious() {
		ModelAndView result;

		result = new ModelAndView("actor/administrator/suspiciouslist");
		result.addObject("requestURI", "actor/administrator/suspiciouslist.do");
		result.addObject("actors", this.actorService.findSuspiciousActors());
		return result;
	}

	@RequestMapping(value = "/bannedlist", method = RequestMethod.GET)
	public ModelAndView listBanned() {
		ModelAndView result;

		result = new ModelAndView("actor/administrator/bannedlist");
		result.addObject("requestURI", "actor/administrator/bannedlist.do");
		result.addObject("actors", this.actorService.findBannedActors());
		return result;
	}

	@RequestMapping(value = "/ban", method = RequestMethod.POST, params = "delete")
	public ModelAndView ban(@RequestParam final int actor) {
		ModelAndView result;
		//Hay que buscar los actores con el findOne de cada tipo de actor
		final Customer c = this.customerService.findOne(actor);
		if (c != null) {
			c.setIsBanned(true);
			this.customerService.save(c);
		} else {
			final HandyWorker hw = this.handyWorkerService.findOne(actor);
			if (hw != null) {
				hw.setIsBanned(true);
				this.handyWorkerService.save(hw);
			} else {
				final Referee r = this.refereeService.findOne(actor);
				if (r != null) {
					r.setIsBanned(true);
					this.refereeService.save(r);
				} else {
					final Administrator admin = this.administratorService.findOne(actor);
					admin.setIsBanned(true);
					this.administratorService.save(admin);
				}
			}
		}

		result = new ModelAndView("redirect:/actor/administrator/bannedlist.do");
		return result;
	}

	@RequestMapping(value = "/unban", method = RequestMethod.POST, params = "delete")
	public ModelAndView unban(@RequestParam final int actor) {
		ModelAndView result;
		//Hay que buscar los actores con el findOne de cada tipo de actor
		final Customer c = this.customerService.findOne(actor);
		if (c != null) {
			c.setIsBanned(false);
			this.customerService.save(c);
		} else {
			final HandyWorker hw = this.handyWorkerService.findOne(actor);
			if (hw != null) {
				hw.setIsBanned(false);
				this.handyWorkerService.save(hw);
			} else {
				final Referee r = this.refereeService.findOne(actor);
				if (r != null) {
					r.setIsBanned(false);
					this.refereeService.save(r);
				} else {
					final Administrator admin = this.administratorService.findOne(actor);
					admin.setIsBanned(false);
					this.administratorService.save(admin);
				}
			}
		}
		result = new ModelAndView("redirect:/actor/administrator/suspiciouslist.do");
		return result;
	}
}
