
package controllers.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BoxService;
import services.MessageService;
import domain.Box;

@Controller
@RequestMapping("message/customer,handyWorker,referee,administrator")
public class MessageController {

	@Autowired
	MessageService	messageService;

	@Autowired
	BoxService		boxService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int boxId) {
		final ModelAndView result;
		Box box;

		box = this.boxService.findOne(boxId);

		result = new ModelAndView("message/customer,handyWorker,referee,administrator/list");

		result.addObject("messages", box.getMessages());
		result.addObject("requestURI", "message/customer,handyWorker,referee,administrator/list.do");

		return result;
	}
}
