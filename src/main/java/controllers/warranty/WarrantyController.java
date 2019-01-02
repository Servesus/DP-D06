
package controllers.warranty;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.WarrantyService;
import controllers.AbstractController;
import domain.Warranty;

@Controller
@RequestMapping("warranty/administrator")
public class WarrantyController extends AbstractController {

	@Autowired
	WarrantyService	warrantyService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Warranty> warranties;

		warranties = this.warrantyService.findAll();

		result = new ModelAndView("warranty/administrator/list");
		result.addObject("warranty", warranties);
		result.addObject("requestURI", "warranty/administrator/list.do");

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Warranty warranty;

		warranty = this.warrantyService.create();
		result = this.createEditModelAndView(warranty);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int warrantyId) {
		ModelAndView result;
		Warranty warranty;

		warranty = this.warrantyService.findOne(warrantyId);
		Assert.notNull(warranty);
		result = this.createEditModelAndView(warranty);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "draft")
	public ModelAndView saveDraft(@Valid final Warranty warranty, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(warranty);
		else
			try {
				warranty.setIsFinal(false);
				this.warrantyService.save(warranty);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(warranty, "warranty.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "final")
	public ModelAndView saveFinal(@Valid final Warranty warranty, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(warranty);
		else
			try {
				warranty.setIsFinal(true);
				this.warrantyService.save(warranty);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(warranty, "warranty.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Warranty warranty, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(warranty);
		else
			try {
				this.warrantyService.delete(warranty);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(warranty, "warranty.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Warranty warranty) {
		ModelAndView result;
		result = this.createEditModelAndView(warranty, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final Warranty warranty, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("warranty/administrator/edit");
		result.addObject("warranty", warranty);
		result.addObject("message", messageCode);
		return result;
	}

}
