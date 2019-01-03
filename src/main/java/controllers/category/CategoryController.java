
package controllers.category;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import controllers.AbstractController;
import domain.Category;

@Controller
@RequestMapping("category/administrator")
public class CategoryController extends AbstractController {

	@Autowired
	CategoryService	categoryService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Category> categories;

		categories = this.categoryService.findAll();

		result = new ModelAndView("category/administrator/list");
		result.addObject("category", categories);
		result.addObject("requestURI", "category/administrator/list.do");

		return result;
	}

	@RequestMapping(value = "/create")
	public ModelAndView create() {
		return null;
	}
}
