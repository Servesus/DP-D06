
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Complaint;
import domain.Customer;
import domain.HandyWorker;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	/* Q1 */

	//The average of the number of fix-up tasks per user
	@Query("select avg(1.0*(select count(f) from FixUpTask f where f.customer.id = c.id)) from Customer c")
	Double getAvgOfFixUpTasksPerUser();

	//The minimum of the number of fix-up tasks per user
	@Query("select min(1.0*(select count(f) from FixUpTask f where f.customer.id = c.id)) from Customer c")
	Double getMinOfFixUpTasksPerUser();

	//The maximum of the number of fix-up tasks per user
	@Query("select max(1.0*(select count(f) from FixUpTask f where f.customer.id = c.id)) from Customer c")
	Double getMaxOfFixUpTasksPerUser();

	//The standard deviation of the number of fix-up tasks per user
	@Query("select stddev(1.0*(select count(f) from FixUpTask f where f.customer.id = c.id)) from Customer c")
	Double getStddevOfFixUpTasksPerUser();

	/* Q2 */

	//The average of the number of applications per fix-up task.
	@Query("select avg(f.applications.size) from FixUpTask f")
	Double getAvgApplicationsPerFixUpTask();

	//The maximum of the number of applications per fix-up task.
	@Query("select max(f.applications.size) from FixUpTask f")
	Double getMaxApplicationsPerFixUpTask();

	//The minimum of the number of applications per fix-up task.
	@Query("select min(f.applications.size) from FixUpTask f")
	Double getMinApplicationsPerFixUpTask();

	//The standard deviation of the number of applications per fix-up task.
	@Query("select stddev(f.applications.size) from FixUpTask f")
	Double getStddevApplicationsPerFixUpTask();

	/* Q3 */

	//The average of the maximum price per fix-up task.
	@Query("select avg(f.maxPrice) from FixUpTask f")
	Double getAvgMaxPricePerFixUpTask();

	//The maximum of the maximum price per fix-up task.
	@Query("select max(f.maxPrice) from FixUpTask f")
	Double getMaxMaxPricePerFixUpTask();

	//The minimum of the maximum price per fix-up task.
	@Query("select min(f.maxPrice) from FixUpTask f")
	Double getMinMaxPricePerFixUpTask();

	//The standard deviation of the maximum price per fix-up task.
	@Query("select stddev(f.maxPrice) from FixUpTask f")
	Double getStddevMaxPricePerFixUpTask();

	/* Q4 */

	//The average of price offered in the applications
	@Query("select avg(a.price) from Application a")
	Double getAvgPriceOfferedOfApplication();

	//The minimum of price offered in the applications
	@Query("select min(a.price) from Application a")
	Double getMinPriceOfferedOfApplication();

	//The maximum of price offered in the applications
	@Query("select max(a.price) from Application a")
	Double getMaxPriceOfferedOfApplication();

	//The standard deviation of the price offered in the applications
	@Query("select stddev(a.price) from Application a")
	Double getStddevPriceOfferedOfApplciation();

	/* Q5 */

	//The ratio of pending applications:
	@Query("select 100.0*(select count(a) from Application a where a.status = 0)/count(a) from Application a")
	Double getRatioOfPendingApplications();

	/* Q6 */

	//The ratio of accepted applications:
	@Query("select 100.0*(select count(a) from Application a where a.status = 1)/count(a) from Application a")
	Double getRatioOfAcceptedApplications();

	/* Q7 */

	//The ratio of rejected applications:
	@Query("select 100.0*(select count(a) from Application a where a.status = -1)/count(a) from Application a")
	Double getRatioOfRejectedApplications();

	/* Q8 */

	//The ratio of pending applications that cannot change its status because their time period has elapsed
	@Query("select 100.0*(select count(a) from Application a  where (a.status = 0) and (a.fixUpTask.startDate < CURRENT_TIMESTAMP()))/count(a) from Application a")
	Double getRatioOfPendingApplicationsCanNotChangeStatus();

	/* Q9 */

	//The listing of customers who have published at least 10% more fix-up tasks than the average, ordered by number of applications.
	@Query("select c from Customer c where((select count(f) from FixUpTask f)*1.0 >= 1.1*(select avg(1.0*(select count(f) from FixUpTask f where f.customer.id = c.id)) from Customer c)) group by c.id order by 1.0*(select count(a) from Application a) DESC")
	List<Customer> getCustomerMoreAcceptedThanAvg();

	/* Q10 */

	//Listing of handy workers who have got accepted at least 10% more ap-plications than the average, ordered by number of applications
	@Query("select h from HandyWorker h join h.applications a where ((select count(a) from HandyWorker h where a.status=1 and a.handyWorker=h)/(h.applications.size)>=(select 1.1*(select count(h2) from HandyWorker h2 join h2.applications a2 where (a2.status=1))/count(a) from HandyWorker a)) order by a.size desc")
	List<HandyWorker> getHwMoreAcceptedThanAvg();

	/* Q11 */

	//The average of the complaints per fix-up task.
	@Query("select avg(1.0*(select count(c) from Complaint c where c.fixUpTasks = f.id))from FixUpTask f")
	Double getAvgComplaintsPerFixUpTask();

	//The maximum of complaints per fix-up task.
	@Query("select max(1.0*(select count(c) from Complaint c where c.fixUpTasks = f.id))from FixUpTask f")
	Double getMaxComplaintsPerFixUpTask();

	//The minimum of the complaints per fix-up task.
	@Query("select min(1.0*(select count(c) from Complaint c where c.fixUpTasks = f.id))from FixUpTask f")
	Double getMinComplaintsPerFixUpTask();

	//The standard deviation of the complaints per fix-up task.
	@Query("select stddev(1.0*(select count(c) from Complaint c where c.fixUpTasks = f.id))from FixUpTask f")
	Double getStddevComplaintsPerFixUpTask();

	/* Q12 */

	//The average of the number of notes per referee report
	@Query("select avg(r.notes.size) from Report r")
	Double getAvgNotesPerRefereeReport();

	//The maximum of the number of notes per referee report
	@Query("select max(r.notes.size) from Report r")
	Double getMaxNotesPerRefereeReport();

	//The minimum of the number of notes per referee report
	@Query("select min(r.notes.size) from Report r")
	Double getMinNotesPerRefereeReport();

	//The standard deviation of the number of notes per referee report
	@Query("select stddev(r.notes.size) from Report r")
	Double getStddevNotesPerRefereeReport();

	/* Q13 */

	// The ratio of fix-up tasks with a complaint.
	@Query("select 100.0*(select count(f) from FixUpTask f where f.complaints.size > 0)/count(f) from FixUpTask f")
	Double getRatioFixUpTaskWithComplaint();

	/* Q14 */

	//The top-three customers in terms of complaints.
	@Query("select c from Complaint c group by c.customer.id order by count(c) DESC")
	List<Complaint> getTop3CustomersOfComplaints();

	/* Q15 */

	//The top-three handy workers in terms of complaints.
	@Query("select h from HandyWorker h join h.applications a join a.fixUpTask t group by h.id order by t.complaints.size DESC")
	List<HandyWorker> getTop3HandyWorkerOfComplaints();

	/* Q16 */
	//The average of the number of notes per referee report
	@Query("select avg(r.notes.size) from Report r")
	Double getAvgNotes();

	//The maximum of the number of notes per referee report
	@Query("select max(r.notes.size) from Report r")
	Integer getMaxNotes();

	//The minimum of the number of notes per referee report
	@Query("select min(r.notes.size) from Report r")
	Integer getMinNotes();

	//The standard deviation of the number of notes per referee report
	@Query("select stddev(r.notes.size) from Report r")
	Double getSteddevNotes();

}
