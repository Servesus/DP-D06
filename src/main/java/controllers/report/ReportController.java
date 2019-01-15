
package controllers.report;

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

import services.ComplaintService;
import services.ReportService;
import controllers.AbstractController;
import domain.Complaint;
import domain.Report;

@Controller
@RequestMapping("report")
public class ReportController extends AbstractController {

	@Autowired
	private ReportService		reportService;

	@Autowired
	private ComplaintService	complaintService;


	@RequestMapping(value = "/referee/list", method = RequestMethod.POST, params = "list")
	public ModelAndView list(@RequestParam final int complaintId) {
		//List<Report> reports = new ArrayList<>();
		Collection<Report> reports;
		Complaint complaint;
		ModelAndView result;

		complaint = this.complaintService.findOne(complaintId);
		//		reports = (List<Report>) complaint.getReports();
		//		final List<Report> sublist = reports.subList(1, reports.size() - 1);
		reports = complaint.getReports();

		result = new ModelAndView("report/referee/list");
		result.addObject("reports", reports);
		result.addObject("requestURI", "report/referee/list.do");

		return result;
	}

	@RequestMapping(value = "/referee/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Report report;

		report = this.reportService.create();

		result = this.createEditModelAndView(report);

		return result;
	}

	@RequestMapping(value = "/referee/edit", method = RequestMethod.POST, params = "edit")
	public ModelAndView edit(@RequestParam final int reportId) {
		ModelAndView result;
		Report report;

		report = this.reportService.findOne(reportId);
		Assert.notNull(report);
		result = this.createEditModelAndView(report);
		return result;
	}

	@RequestMapping(value = "/referee/edit", method = RequestMethod.POST, params = "draft")
	public ModelAndView saveDraft(@Valid final Report report, final BindingResult binding, final int complaintId) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(report);
		try {
			report.setIsFinal(false);
			report.setComplaint(this.complaintService.findOne(complaintId));
			this.reportService.save(report);
			result = new ModelAndView("complaint/referee/listSelfAssign.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(report, "report.commit.error");
		}
		return result;
	}

	@RequestMapping(value = "/referee/edit", method = RequestMethod.POST, params = "final")
	public ModelAndView saveFinal(@Valid final Report report, final BindingResult binding, final int complaintId) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(report);
		try {
			report.setIsFinal(true);
			report.setComplaint(this.complaintService.findOne(complaintId));
			this.reportService.save(report);
			result = new ModelAndView("complaint/referee/listSelfAssign.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(report, "report.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Report report) {
		ModelAndView result;

		result = this.createEditModelAndView(report, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Report report, final String messageCode) {
		final ModelAndView

		result = new ModelAndView("report/referee/edit");
		result.addObject("report", report);
		result.addObject("message", messageCode);

		return result;
	}

}
