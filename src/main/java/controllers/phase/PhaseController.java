
package controllers.phase;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FixUpTaskService;
import services.PhaseService;
import controllers.AbstractController;
import domain.FixUpTask;
import domain.Phase;

@Controller
@RequestMapping("phase")
public class PhaseController extends AbstractController {

	@Autowired
	private PhaseService		phaseService;

	@Autowired
	private FixUpTaskService	fixUpTaskService;


	@RequestMapping(value = "/handyWorker/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int fixUpTaskId) {
		ModelAndView result;
		Collection<Phase> phases;

		final FixUpTask fixUpTask = this.fixUpTaskService.findOne(fixUpTaskId);

		if (fixUpTask == null)
			result = new ModelAndView("redirect:/misc/403");
		else {
			phases = fixUpTask.getPhases();
			result = new ModelAndView("phase/handyWorker/list");
			result.addObject("phases", phases);
			result.addObject("phase.fixUpTask.id", fixUpTaskId);
			result.addObject("requestURI", "phase/handyWorker/list.do");
		}
		return result;
	}
	@RequestMapping(value = "/handyWorker/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int phaseId) {
		final ModelAndView result;
		Phase phase;

		phase = this.phaseService.findOne(phaseId);

		result = new ModelAndView("phase/handyWorker/show");
		result.addObject("phase", phase);
		result.addObject("requestURI", "phase/handyWorker/show.do");
		return result;
	}
	@RequestMapping(value = "/handyWorker/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int fixUpTaskId) {
		ModelAndView result;
		Phase phase;

		phase = this.phaseService.create(fixUpTaskId);

		if (phase == null)
			result = new ModelAndView("redirect:/misc/403");
		else
			result = this.createEditModelAndView(phase);
		return result;
	}

	@RequestMapping(value = "/handyWorker/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Phase phase, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(phase);
		try {
			this.phaseService.save(phase);
			result = new ModelAndView("redirect:list.do?fixUpTaskId=" + phase.getFixUpTask().getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(phase, "phase.commit.error");
		}
		return result;
	}

	@RequestMapping(value = "/handyWorker/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int phaseId) {
		ModelAndView result;
		Phase phase;

		phase = this.phaseService.findOne(phaseId);
		if (phase == null)
			result = new ModelAndView("redirect:/misc/403");
		else
			result = this.createEditModelAndView(phase);
		return result;
	}

	@RequestMapping(value = "/handyWorker/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Phase phase, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(phase);
		try {
			this.phaseService.delete(phase);
			result = new ModelAndView("redirect:list.do?fixUpTaskId=" + phase.getFixUpTask().getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(phase, "phase.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Phase phase) {
		ModelAndView result;

		result = this.createEditModelAndView(phase, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Phase phase, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("phase/handyWorker/edit");
		result.addObject("phase", phase);
		result.addObject("message", messageCode);

		return result;
	}

}
