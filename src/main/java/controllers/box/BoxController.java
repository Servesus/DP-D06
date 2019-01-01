
package controllers.box;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.BoxService;
import controllers.AbstractController;
import domain.Actor;
import domain.Box;

@Controller
@RequestMapping("box/customer,handyWorker,referee,administrator")
public class BoxController extends AbstractController {

	@Autowired
	private BoxService		boxService;

	@Autowired
	private ActorService	actorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Box> boxes;
		Actor user;

		user = this.actorService.getActorLogged();
		boxes = user.getBoxes();

		result = new ModelAndView("box/customer,handyWorker,referee,administrator/list");
		result.addObject("boxes", boxes);
		result.addObject("requestURI", "box/customer,handyWorker,referee,administrator/list.do");

		return result;
	}
}
