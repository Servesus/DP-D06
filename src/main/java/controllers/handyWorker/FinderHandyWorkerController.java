
package controllers.handyWorker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CategoryService;
import services.ConfigurationService;
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
	private FinderService			finderService;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private HandyWorkerService		handyWorkerService;
	@Autowired
	private CategoryService			categoryService;
	@Autowired
	private WarrantyService			warrantyService;
	@Autowired
	private ConfigurationService	configurationService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		//sacar fixuptask de un finder asociado a un HW
		ModelAndView result;
		Collection<FixUpTask> fixUpTasks;
		final Actor user = this.actorService.getActorLogged();
		final HandyWorker hw = this.handyWorkerService.findOne(user.getId());
		Finder finder;
		finder = hw.getFinder();
		//borrar si ha pasado x tiempo;
		final Date fechaActual = new Date();
		final Date lastUpdate = finder.getLastUpdate();
		final int horasDeGuardado = this.configurationService.findAll().get(0).getMaxTime();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(lastUpdate);
		calendar.add(Calendar.HOUR, horasDeGuardado);
		final Date fechaLimite = calendar.getTime();

		if (fechaLimite.after(fechaActual)) {
			finder.setFixUpTask(new ArrayList<FixUpTask>());
			this.finderService.save(finder);
		}

		fixUpTasks = finder.getFixUpTask();
		//meterlo en result
		result = new ModelAndView("finder/handyWorker/list");
		result.addObject("fixUpTasks", fixUpTasks);
		result.addObject("finder", finder);
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
				if (finder.getRangeStart() == null)
					finder.setRangeStart(0);
				if (finder.getRangeFinish() == null)
					finder.setRangeFinish(99999);
				if (finder.getDateStartRange() == null)
					finder.setDateStartRange(new Date(946681200000L)); //01/01/2000
				if (finder.getDateFinishRange() == null)
					finder.setDateFinishRange(new Date()); //fecha actual

				this.finderService.save(finder);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(finder, oops.getLocalizedMessage());
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
		final List<String> allCategoriesNamesES = new ArrayList<String>();
		final List<String> allCategoriesNamesEN = new ArrayList<String>();
		for (int i = 0; i < allCategories.size(); i++) {
			allCategoriesNamesES.add(allCategories.get(i).getNameES());
			allCategoriesNamesEN.add(allCategories.get(i).getNameEN());
		}
		final List<Warranty> allWarranties = (List<Warranty>) this.warrantyService.findAll();
		final List<String> allWarrantiesTitles = new ArrayList<String>();
		for (int i = 0; i < allWarranties.size(); i++)
			allWarrantiesTitles.add(allWarranties.get(i).getTitle());
		final String language = LocaleContextHolder.getLocale().getLanguage();

		result = new ModelAndView("finder/handyWorker/edit");
		result.addObject("finder", finder);
		if (language.equals("es"))
			result.addObject("cNames", allCategoriesNamesES);
		else if (language.equals("en"))
			result.addObject("cNames", allCategoriesNamesEN);
		result.addObject("wTitles", allWarrantiesTitles);
		result.addObject("messageCode", messageCode);

		return result;
	}
}
