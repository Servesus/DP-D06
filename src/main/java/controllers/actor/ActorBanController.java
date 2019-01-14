
package controllers.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import controllers.AbstractController;
import domain.Actor;

@Controller
@RequestMapping("actor/administrator")
public class ActorBanController extends AbstractController {

	@Autowired
	ActorService	actorService;


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

		final Actor banned = this.actorService.findOne(actor);
		try {
			//Hay que buscar los actores con el findOne de cada tipo de actor
		} catch (final Throwable oops) {

		}
		banned.setIsBanned(true);
		result = new ModelAndView("actor/administrator/bannedlist");
		return result;
	}

	@RequestMapping(value = "/unban", method = RequestMethod.POST, params = "delete")
	public ModelAndView unban(@RequestParam final int actor) {
		ModelAndView result;

		final Actor banned = this.actorService.findOne(actor);
		banned.setIsBanned(false);
		result = new ModelAndView("actor/administrator/suspiciouslist");
		return result;
	}
}
