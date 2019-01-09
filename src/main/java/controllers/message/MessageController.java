
package controllers.message;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Message mesage;

		mesage = this.messageService.create();
		result = this.createEditModelAndView(mesage);

		return result;
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int messageId) {
		final ModelAndView result;
		final Message mesage;

		mesage = this.messageService.findOne(messageId);
		Assert.notNull(mesage);
		result = this.createEditModelAndView(mesage);

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Message mesage, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(mesage);
		else
			try {
				this.messageService.save(mesage);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(mesage, "message.commit.error");
			}

		return result;
	}

	//TODO
	@RequestMapping(value = "/edit", method = RequestMethod.GET, params = "delete")
	public ModelAndView delete(final Message mesage, final BindingResult binding) {
		ModelAndView result;

		try {
			this.messageService.delete(mesage);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(mesage, "message.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Message message) {
		ModelAndView result;

		result = this.createEditModelAndView(message, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Message mesage, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("message/customer,handyWorker,referee,administrator/edit");

		result.addObject("mesage", mesage);
		result.addObject("message", messageCode);

		return result;
	}
}
