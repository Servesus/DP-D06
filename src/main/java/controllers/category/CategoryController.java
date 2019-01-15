
package controllers.category;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import controllers.AbstractController;
import domain.Category;

@Controller
@RequestMapping("category/administrator")
public class CategoryController extends AbstractController {

	@Autowired
	private CategoryService	categoryService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Category> categories;
		final String language = LocaleContextHolder.getLocale().getLanguage();
		categories = this.categoryService.findAll();

		result = new ModelAndView("category/administrator/list");
		result.addObject("category", categories);
		result.addObject("requestURI", "category/administrator/list.do");
		result.addObject("lang", language);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Category category;

		category = this.categoryService.create();
		result = this.createEditModelAndView(category);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int categoryId) {
		ModelAndView result;
		Category category;

		category = this.categoryService.findOne(categoryId);

		if (category == null)
			result = new ModelAndView("redirect:/misc/403");
		else
			result = this.createEditModelAndView(category);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Category category, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(category);
		else
			try {

				this.categoryService.save(category);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(category, "category.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Category category, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(category);
		else
			try {
				this.categoryService.delete(category);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(category, "category.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int categoryId) {
		ModelAndView result;
		final Category category = this.categoryService.findOne(categoryId);
		final List<Category> childs;

		if (category == null)
			result = new ModelAndView("redirect:/misc/403");
		else {
			childs = (List<Category>) category.getChilds();
			final String language = LocaleContextHolder.getLocale().getLanguage();
			result = new ModelAndView("category/administrator/show");
			result.addObject("nameEN", category.getNameEN());
			result.addObject("nameES", category.getNameES());
			result.addObject("parent", category.getParents());
			result.addObject("childs", childs);
			result.addObject("lang", language);
		}
		return result;
	}
	protected ModelAndView createEditModelAndView(final Category category) {
		ModelAndView result;
		result = this.createEditModelAndView(category, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Category category, final String messageCode) {
		ModelAndView result;
		Collection<Category> parents;

		parents = this.categoryService.findAll();
		parents.remove(category);
		final String language = LocaleContextHolder.getLocale().getLanguage();

		result = new ModelAndView("category/administrator/edit");
		result.addObject("category", category);
		result.addObject("cNames", parents);
		result.addObject("lang", language);
		return result;
	}

}
