
package controllers.administrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import controllers.AbstractController;
import domain.Complaint;
import domain.Customer;
import domain.HandyWorker;

@Controller
@RequestMapping("administrator")
public class DashboardAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;


	//Operaciones Dashboard
	@RequestMapping(value = "/administrator/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {

		final ModelAndView result;

		final Double AvgOfFixUpTasksPerUser = this.administratorService.getStatsFixUpTasksPerUser().get(0);
		final Double MinOfFixUpTasksPerUser = this.administratorService.getStatsFixUpTasksPerUser().get(1);
		final Double MaxOfFixUpTasksPerUser = this.administratorService.getStatsFixUpTasksPerUser().get(2);
		final Double StddevOfFixUpTasksPerUser = this.administratorService.getStatsFixUpTasksPerUser().get(3);
		final Double AvgApplicationsPerFixUpTask = this.administratorService.getStatsApplicationsPerFixUpTask().get(0);
		final Double MaxApplicationsPerFixUpTask = this.administratorService.getStatsApplicationsPerFixUpTask().get(2);
		final Double MinApplicationsPerFixUpTask = this.administratorService.getStatsApplicationsPerFixUpTask().get(1);
		final Double StddevApplicationsPerFixUpTask = this.administratorService.getStatsApplicationsPerFixUpTask().get(3);
		final Double AvgMaxPricePerFixUpTask = this.administratorService.getStatsMaxPriceOfFixUpTask().get(0);
		final Double MaxMaxPricePerFixUpTask = this.administratorService.getStatsMaxPriceOfFixUpTask().get(2);
		final Double MinMaxPricePerFixUpTask = this.administratorService.getStatsMaxPriceOfFixUpTask().get(1);
		final Double StddevMaxPricePerFixUpTask = this.administratorService.getStatsMaxPriceOfFixUpTask().get(3);
		final Double AvgPriceOfferedOfApplication = this.administratorService.getStatsPriceOfferedInApplications().get(0);
		final Double MinPriceOfferedOfApplication = this.administratorService.getStatsPriceOfferedInApplications().get(1);
		final Double MaxPriceOfferedOfApplication = this.administratorService.getStatsPriceOfferedInApplications().get(2);
		final Double StddevPriceOfferedOfApplciation = this.administratorService.getStatsPriceOfferedInApplications().get(3);
		final Double RatioOfPendingApplications = this.administratorService.getRatioPendingApplications();
		final Double RatioOfAcceptedApplications = this.administratorService.getRatioAcceptedApplications();
		final Double RatioOfRejectedApplications = this.administratorService.getRatioRejectedApplications();
		final Double RatioOfPendingApplicationsCanNotChangeStatus = this.administratorService.getRatioOfPendingApplicationsCanNotChangeStatus();
		final Double AvgComplaintsPerFixUpTask = this.administratorService.getStatsComplaintsPerFixUpTask().get(0);
		final Double MaxComplaintsPerFixUpTask = this.administratorService.getStatsComplaintsPerFixUpTask().get(2);
		final Double MinComplaintsPerFixUpTask = this.administratorService.getStatsComplaintsPerFixUpTask().get(1);
		final Double StddevComplaintsPerFixUpTask = this.administratorService.getStatsComplaintsPerFixUpTask().get(3);
		final Double AvgNotesPerRefereeReport = this.administratorService.getStatsNotesPerRefereeReport().get(0);
		final Double MaxNotesPerRefereeReport = this.administratorService.getStatsNotesPerRefereeReport().get(2);
		final Double MinNotesPerRefereeReport = this.administratorService.getStatsNotesPerRefereeReport().get(1);
		final Double StddevNotesPerRefereeReport = this.administratorService.getStatsNotesPerRefereeReport().get(3);
		final Double RatioFixUpTaskWithComplaint = this.administratorService.getRatioFixUpTaskWithComplaint();
		final List<Customer> CustomerMoreAcceptedThanAvg = this.administratorService.customerMoreAcceptedThanAvg();
		final List<HandyWorker> HwMoreAcceptedThanAvg = this.administratorService.getHwMoreAcceptedThanAvg();
		final List<Complaint> Top3CustomersOfComplaints = this.administratorService.getTop3CustomersOfComplaints();
		final List<HandyWorker> Top3HandyWorkerOfComplaints = this.administratorService.getTop3HandyWorkerOfComplaints();

		result = new ModelAndView("administrator/administrator/dashboard");
		result.addObject("AvgOfFixUpTasksPerUser", AvgOfFixUpTasksPerUser);
		result.addObject("MinOfFixUpTasksPerUser", MinOfFixUpTasksPerUser);
		result.addObject("MaxOfFixUpTasksPerUser", MaxOfFixUpTasksPerUser);
		result.addObject("StddevOfFixUpTasksPerUser", StddevOfFixUpTasksPerUser);
		result.addObject("AvgApplicationsPerFixUpTask", AvgApplicationsPerFixUpTask);
		result.addObject("MaxApplicationsPerFixUpTask", MaxApplicationsPerFixUpTask);
		result.addObject("MinApplicationsPerFixUpTask", MinApplicationsPerFixUpTask);
		result.addObject("StddevApplicationsPerFixUpTask", StddevApplicationsPerFixUpTask);
		result.addObject("AvgMaxPricePerFixUpTask", AvgMaxPricePerFixUpTask);
		result.addObject("MaxMaxPricePerFixUpTask", MaxMaxPricePerFixUpTask);
		result.addObject("MinMaxPricePerFixUpTask", MinMaxPricePerFixUpTask);
		result.addObject("StddevMaxPricePerFixUpTask", StddevMaxPricePerFixUpTask);
		result.addObject("AvgPriceOfferedOfApplication", AvgPriceOfferedOfApplication);
		result.addObject("MinPriceOfferedOfApplication", MinPriceOfferedOfApplication);
		result.addObject("MaxPriceOfferedOfApplication", MaxPriceOfferedOfApplication);
		result.addObject("StddevPriceOfferedOfApplciation", StddevPriceOfferedOfApplciation);
		result.addObject("RatioOfPendingApplications", RatioOfPendingApplications);
		result.addObject("RatioOfAcceptedApplications", RatioOfAcceptedApplications);
		result.addObject("RatioOfRejectedApplications", RatioOfRejectedApplications);
		result.addObject("RatioOfPendingApplicationsCanNotChangeStatus", RatioOfPendingApplicationsCanNotChangeStatus);
		result.addObject("AvgComplaintsPerFixUpTask", AvgComplaintsPerFixUpTask);
		result.addObject("MaxComplaintsPerFixUpTask", MaxComplaintsPerFixUpTask);
		result.addObject("MinComplaintsPerFixUpTask", MinComplaintsPerFixUpTask);
		result.addObject("StddevComplaintsPerFixUpTask", StddevComplaintsPerFixUpTask);
		result.addObject("AvgNotesPerRefereeReport", AvgNotesPerRefereeReport);
		result.addObject("MaxNotesPerRefereeReport", MaxNotesPerRefereeReport);
		result.addObject("MinNotesPerRefereeReport", MinNotesPerRefereeReport);
		result.addObject("StddevNotesPerRefereeReport", StddevNotesPerRefereeReport);
		result.addObject("RatioFixUpTaskWithComplaint", RatioFixUpTaskWithComplaint);
		result.addObject("CustomerMoreAcceptedThanAvg", CustomerMoreAcceptedThanAvg);
		result.addObject("HwMoreAcceptedThanAvg", HwMoreAcceptedThanAvg);
		result.addObject("Top3CustomersOfComplaints", Top3CustomersOfComplaints);
		result.addObject("Top3HandyWorkerOfComplaints", Top3HandyWorkerOfComplaints);

		return result;
	}
}
