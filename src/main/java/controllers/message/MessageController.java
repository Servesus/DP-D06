
package controllers.message;

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
import domain.Box;
import domain.Message;

@Controller
@RequestMapping("message/customer,handyWorker,referee,administrator")
public class MessageController {

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

		return result;
	}

	//TODO
	@RequestMapping(value = "", method = RequestMethod.POST, params = "")
	public ModelAndView delete(final int boxId, final Message message, final BindingResult binding) {
		ModelAndView result;
		Box box;

		box = this.boxService.findOne(boxId);

		try {
			if (box.getMessages().contains(message) && !(box.getName().equals("trashBox"))) {
				this.messageService.moveMessage(message, trashBox);
				result = new ModelAndView("redirect:list.do");
			} else {
				this.messageService.delete(message);
				result = new ModelAndView("redirect:list.do");
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(box, "message.commit.error");
		}
		return result;
	}

}
