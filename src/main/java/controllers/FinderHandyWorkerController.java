package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.FixUpTask;
import domain.HandyWorker;

import services.ActorService;
import services.FinderService;

@Controller
@RequestMapping("finder/handyWorker/")
public class FinderHandyWorkerController extends AbstractController{
	
	@Autowired
	private FinderService finderService;
	@Autowired
	private ActorService actorService;
	
	@RequestMapping(value ="/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<FixUpTask> fixUpTask;
		Actor user = this.actorService.getActorLogged();
		
		
		//fixUpTask = this.finderService.findOne(id)
		return null;
	}

}
