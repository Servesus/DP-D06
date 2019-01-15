
package controllers.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.NoteService;

@Controller
@RequestMapping("note/customer,handyworker,referee")
public class NoteController {

	@Autowired
	NoteService	noteService;


	@RequestMapping(value = "/create")
	public ModelAndView create() {
		return null;
	}
}
