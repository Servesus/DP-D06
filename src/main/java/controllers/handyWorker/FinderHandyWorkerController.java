
package controllers.handyWorker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CategoryService;
import services.FinderService;
import services.HandyWorkerService;
import services.WarrantyService;
import controllers.AbstractController;
import domain.Actor;
import domain.Category;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Warranty;

@Controller
@RequestMapping("finder/handyWorker")
public class FinderHandyWorkerController extends AbstractController {

	@Autowired
	private FinderService		finderService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private CategoryService		categoryService;
	@Autowired
	private WarrantyService		warrantyService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		//sacar fixuptask de un finder asociado a un HW
		ModelAndView result;
		Collection<FixUpTask> fixUpTasks;
		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());
		fixUpTasks = hw.getFinder().getFixUpTask();
		//meterlo en result
		result = new ModelAndView("finder/handyWorker/list");
		result.addObject("fixUpTasks", fixUpTasks);
		result.addObject("requestURI", "finder/handyWorker/list.do");
		//return
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Finder finder;

		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());

		finder = hw.getFinder();
		Assert.notNull(finder);
		result = this.createEditModelAndView(finder);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Finder finder, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(finder);
		else
			try {
				this.finderService.save(finder);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(finder, "finder.update.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Finder finder) {
		ModelAndView result;

		result = this.createEditModelAndView(finder, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Finder finder, final String messageCode) {
		ModelAndView result;
		//Collection<FixUpTask> fixUpTasks;

		//fixUpTasks = finder.getFixUpTask();
		final List<Category> allCategories = (List<Category>) this.categoryService.findAll();
		final List<String> allCategoriesNames = new ArrayList<String>();
		for (int i = 0; i < allCategories.size(); i++)
			allCategoriesNames.add(allCategories.get(i).getName());
		final List<Warranty> allWarranties = (List<Warranty>) this.warrantyService.findAll();
		final List<String> allWarrantiesTitles = new ArrayList<String>();
		for (int i = 0; i < allWarranties.size(); i++)
			allWarrantiesTitles.add(allWarranties.get(i).getTitle());

		result = new ModelAndView("finder/handyWorker/edit");
		result.addObject("finder", finder);
		result.addObject("cNames", allCategoriesNames);
		result.addObject("wTitles", allWarrantiesTitles);
		result.addObject("messageCode", messageCode);

		return result;
	}

}
