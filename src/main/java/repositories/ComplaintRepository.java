
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

	@Query("select c from Complaint c where c.customer.id=?1")
	public Collection<Complaint> getCustomerComplaints(int complaintId);
}
