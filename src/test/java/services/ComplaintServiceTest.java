
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Complaint;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ComplaintServiceTest extends AbstractTest {

	@Autowired
	private ComplaintService	complaintService;


	@Test
	public void testCreateComplaint() {

		super.authenticate("customer1");
		final Complaint result = this.complaintService.create(2696);
		result.setDescription("");
		Assert.notNull(result);
		super.authenticate(null);

	}
	@Test
	public void testSaveComplaint() {

		super.authenticate("customer1");
		final int id = this.getEntityId("fixUpTask1");
		final Complaint a = this.complaintService.create(id);
		final Complaint b = this.complaintService.save(a);
		Assert.isTrue(!b.equals(null));
		super.authenticate(null);
	}
	@Test
	public void testDeleteComplaint() {
		super.authenticate("customer1");
		final int id = this.getEntityId("complaint1");
		final Complaint caso1 = this.complaintService.findOne(id);
		this.complaintService.delete(caso1);
		Assert.isNull(this.complaintService.findOne(id));
		super.authenticate(null);
	}
	@Test
	public void testSelfAssignedComplaint() {
		super.authenticate("referee1");
		Collection<Complaint> result = new ArrayList<Complaint>();
		result = this.complaintService.getComplaintSelfAssigned();
		Assert.isTrue(result.size() >= 0);
		super.authenticate(null);
	}
}
