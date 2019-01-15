
package controllers.box;

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

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Box box;

		box = this.boxService.create();
		result = this.createEditModelAndView(box);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int boxId) {
		ModelAndView result;
		Box box;

		Actor user;
		user = this.actorService.getActorLogged();

		box = this.boxService.findOne(boxId);

		if (user.getBoxes().contains(box) && box != null)
			result = this.createEditModelAndView(box);
		else
			result = new ModelAndView("redirect:/misc/403");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Box box, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(box);
		else
			try {
				this.boxService.save(box);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(box, "box.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Box box, final BindingResult binding) {
		ModelAndView result;

		try {
			this.boxService.delete(box);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(box, "box.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Box box) {
		ModelAndView result;

		result = this.createEditModelAndView(box, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Box box, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("box/customer,handyWorker,referee,administrator/edit");

		result.addObject("box", box);
		result.addObject("message", messageCode);

		return result;
	}

}
