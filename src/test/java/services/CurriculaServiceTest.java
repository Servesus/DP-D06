
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Curricula;
import domain.PersonalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CurriculaServiceTest extends AbstractTest {

	@Autowired
	private CurriculaService	curriculaService;


	@Test
	public void testCreateCurricula() {

		//super.authenticate("handyWorker1");
		final Curricula result = this.curriculaService.create();
		Assert.isTrue(result.getTicker() != null && result.getPersonalRecord() != null && result.getEducationalRecord() != null && result.getEducationalRecord().isEmpty() && result.getEndorserRecord() != null && result.getEndorserRecord().isEmpty()
			&& result.getEndorserRecord() != null && result.getEndorserRecord().isEmpty() && result.getProfessionalRecord() != null && result.getProfessionalRecord().isEmpty() && result.getMiscRecord() != null && result.getMiscRecord().isEmpty());

	}
	/*
	 * @Test
	 * public void testFindOneCurricula() {
	 * 
	 * final PersonalRecord personalRecord1 = this.personalRecordService.findOne(this.getEntityId("personalRecord1"));
	 * final EducationalRecord educationalRecord1 = this.educationalRecordService.findOne(this.getEntityId("educationalRecord1"));
	 * final EndorserRecord endorserRecord1 = this.endorserRecordService.findOne(this.getEntityId("endorserRecord1"));
	 * final EndorserRecord endorserRecord2 = this.endorserRecordService.findOne(this.getEntityId("endorserRecord2"));
	 * final ProfessionalRecord professionalRecord1 = this.professionalRecordService.findOne(this.getEntityId("personalRecord1"));
	 * final MiscRecord miscRecord1 = this.miscRecordService.findOne(this.getEntityId("miscRecord1"));
	 * final Curricula result = this.curriculaService.findOne(this.getEntityId("curricula1"));
	 * Assert.isTrue(result.getTicker() == "111118-e63ty6" && result.getPersonalRecord().equals(personalRecord1) && result.getEducationalRecord().contains(educationalRecord1) && result.getEndorserRecord().contains(endorserRecord1)
	 * && result.getEndorserRecord().contains(endorserRecord2) && result.getProfessionalRecord().contains(professionalRecord1) && result.getMiscRecord().contains(miscRecord1));
	 * 
	 * }
	 */
	/*
	 * @Test
	 * public void testFindAllCurricula() {
	 * 
	 * final Complaint[] metodo = (Complaint[]) this.complaintService.findAll().toArray();
	 * final FixUpTask[] apoyo = (FixUpTask[]) this.fixUpTaskService.findAll().toArray();
	 * for (int i = 0; i < apoyo.length; i++)
	 * comparador += apoyo[i].getComplaints().size();
	 * Assert.isTrue(metodo.length == comparador);
	 * 
	 * }
	 */
	@Test
	public void testSaveCurricula() {
		//super.authenticate("handyWorker1");
		final Curricula curricula = this.curriculaService.create();
		final PersonalRecord pR = curricula.getPersonalRecord();
		pR.setName("Manuel");
		pR.setPhone("666554433");
		pR.setPhoto("https://www.fotito.com");
		pR.setEmail("a@e.com");
		pR.setLinkedInProfile("https://www.linkedin.com/profile");
		pR.setMiddleName("Manuel");
		pR.setSurname("Manuel");
		final Curricula saved = this.curriculaService.save(curricula);
		final Collection<Curricula> curriculas = this.curriculaService.findAll();
		Assert.isTrue(curriculas.contains(saved));

	}
}
