
package controllers.message;

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
import services.BoxService;
import services.MessageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Box;
import domain.Message;

@Controller
@RequestMapping("message")
public class MessageController extends AbstractController {

	@Autowired
	MessageService	messageService;

	@Autowired
	BoxService		boxService;

	@Autowired
	ActorService	actorService;


	@RequestMapping(value = "/customer,handyWorker,referee,administrator/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int boxId) {
		ModelAndView result;
		Box box;
		Actor user;

		user = this.actorService.getActorLogged();
		box = this.boxService.findOne(boxId);

		if (!(user.getBoxes().contains(box)))
			result = new ModelAndView("redirect:/misc/403");
		else {

			result = new ModelAndView("message/customer,handyWorker,referee,administrator/list");

			result.addObject("messages", box.getMessages());
			result.addObject("requestURI", "message/customer,handyWorker,referee,administrator/list.do");
			result.addObject("boxId", boxId);
			result.addObject("boxes", this.actorService.getActorLogged().getBoxes());
		}
		return result;
	}

	@RequestMapping(value = "/customer,handyWorker,referee,administrator/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Message mesage;

		mesage = this.messageService.create();
		result = this.createEditModelAndView(mesage);
		return result;
	}

	@RequestMapping(value = "/administrator/createbroadcast")
	public ModelAndView createBroadcast() {
		final ModelAndView result;
		Message mesage;
		mesage = this.messageService.create();
		result = this.createEditModelAndView2(mesage);

		return result;
	}

	@RequestMapping(value = "/administrator/createbroadcast", method = RequestMethod.POST, params = "send")
	public ModelAndView saveBroadcast(@Valid final Message mesage, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView2(mesage);
		else
			try {
				this.messageService.adminMessage(mesage);
				result = new ModelAndView("redirect:/box/customer,handyWorker,referee,administrator/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView2(mesage, "mesage.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/customer,handyWorker,referee,administrator/create", method = RequestMethod.POST, params = "send")
	public ModelAndView save(@Valid final Message mesage, @RequestParam final String recipients, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(mesage);
		else
			try {
				final Collection<Actor> actors = new ArrayList<Actor>();
				if (recipients.contains(",")) {
					final String[] recipient = recipients.split(",");
					for (final String s : recipient)
						actors.add(this.actorService.findByUsername(s));
				} else
					actors.add(this.actorService.findByUsername(recipients));
				mesage.setRecipient(actors);
				mesage.setSender(this.actorService.getActorLogged());
				this.messageService.save(mesage);
				result = new ModelAndView("redirect:/box/customer,handyWorker,referee,administrator/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(mesage, "mesage.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/customer,handyWorker,referee,administrator/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam final int boxId, @RequestParam final int mesage) {
		ModelAndView result;
		final Message message = this.messageService.findOne(mesage);
		final Box b = this.boxService.findOne(boxId);
		this.messageService.deleteMessage(message, b);
		result = new ModelAndView("redirect:/box/customer,handyWorker,referee,administrator/list.do");

		return result;
	}

	@RequestMapping(value = "/customer,handyWorker,referee,administrator/move", method = RequestMethod.POST, params = "move")
	public ModelAndView move(@RequestParam final int boxId, @RequestParam final int mesage, @RequestParam final Box box) {
		ModelAndView result;
		final Message message = this.messageService.findOne(mesage);
		final Box originBox = this.boxService.findOne(boxId);
		this.messageService.moveMessage(message, box, originBox);
		result = new ModelAndView("redirect:/box/customer,handyWorker,referee,administrator/list.do");

		return result;
	}

	@RequestMapping(value = "/customer,handyWorker,referee,administrator/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int messageId) {
		ModelAndView result;
		Message mesage;

		mesage = this.messageService.findOne(messageId);

		if (mesage == null)
			result = new ModelAndView("redirect:/misc/403");
		else {

			result = new ModelAndView("message/customer,handyWorker,referee,administrator/show");

			result.addObject("sendDate", mesage.getSendDate());
			result.addObject("recipient", mesage.getRecipient());
			result.addObject("sender", mesage.getSender().getUserAccount().getUsername());
			result.addObject("subject", mesage.getSubject());
			result.addObject("body", mesage.getBody());
			result.addObject("priority", mesage.getPriority());
			result.addObject("tags", mesage.getTags());

		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Message mesage) {
		ModelAndView result;

		result = this.createEditModelAndView(mesage, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Message mesage, final String messageCode) {
		ModelAndView result;
		final Collection<Actor> actors = this.actorService.findAll();
		result = new ModelAndView("message/customer,handyWorker,referee,administrator/create");
		result.addObject("actors", actors);
		result.addObject("mesage", mesage);
		result.addObject("message", messageCode);

		return result;
	}

	protected ModelAndView createEditModelAndView2(final Message mesage) {
		ModelAndView result;

		result = this.createEditModelAndView2(mesage, null);

		return result;
	}

	protected ModelAndView createEditModelAndView2(final Message mesage, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("message/administrator/createbroadcast");
		result.addObject("mesage", mesage);
		result.addObject("message", messageCode);

		return result;
	}
}
