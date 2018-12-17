
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.Complaint;
import domain.Customer;
import domain.FixUpTask;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	//A customer must be able to: Manage an arbitrary number of fix-up tasks,
	//which includes listing, showing, creating, updating, and deleting them
	@Query("select f from Customer c join c.fixUpTasks f where c.userAccount.id=?1")
	List<FixUpTask> getFixUpTasks(int userAccountId);

	//Manage the applications for his or her fix-up tasks, which includes listing and updating
	//them
	@Query("select a from Customer c join c.fixUpTasks f join f.applications a where c.userAccount.id=?2")
	List<Application> getApplications(int userAccountId);

	//Manage his or her complaints, which includes listing, showing, and creating them
	@Query("select com from Customer c join c.complaints com where c.userAccount.id=?3")
	List<Complaint> getComplaints(int userAccountId);
}
