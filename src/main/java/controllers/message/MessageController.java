
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
@RequestMapping("message/customer,handyWorker,referee,administrator")
public class MessageController extends AbstractController {

	@Autowired
	MessageService	messageService;

	@Autowired
	BoxService		boxService;

	@Autowired
	ActorService	actorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int boxId) {
		ModelAndView result;
		Box box;

		box = this.boxService.findOne(boxId);

		result = new ModelAndView("message/customer,handyWorker,referee,administrator/list");

		result.addObject("messages", box.getMessages());
		result.addObject("requestURI", "message/customer,handyWorker,referee,administrator/list.do");
		result.addObject("boxId", boxId);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Message mesage;

		mesage = this.messageService.create();
		result = this.createEditModelAndView(mesage);
		return result;
	}

	//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	//	public ModelAndView edit(@RequestParam final int messageId) {
	//		final ModelAndView result;
	//		final Message mesage;
	//
	//		mesage = this.messageService.findOne(messageId);
	//		Assert.notNull(mesage);
	//		result = this.createEditModelAndView(mesage);
	//
	//		return result;
	//	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "send")
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

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam final int boxId, @RequestParam final int mesage) {
		ModelAndView result;
		final Message message = this.messageService.findOne(mesage);
		final Box b = this.boxService.findOne(boxId);
		this.messageService.deleteMessage(message, b);
		result = new ModelAndView("redirect:/box/customer,handyWorker,referee,administrator/list.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int messageId) {
		ModelAndView result;
		Message mesage;

		mesage = this.messageService.findOne(messageId);

		result = new ModelAndView("message/customer,handyWorker,referee,administrator/show");

		result.addObject("sendDate", mesage.getSendDate());
		result.addObject("recipient", mesage.getRecipient());
		result.addObject("sender", mesage.getSender().getUserAccount().getUsername());
		result.addObject("subject", mesage.getSubject());
		result.addObject("body", mesage.getBody());
		result.addObject("priority", mesage.getPriority());

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
}
