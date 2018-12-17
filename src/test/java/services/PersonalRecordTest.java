
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
import domain.PersonalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class PersonalRecordTest extends AbstractTest {

	@Autowired
	private PersonalRecordService	personalRecord;


	@Test
	public void testCreatePersonalRecord() {
		final PersonalRecord per = this.personalRecord.create();
		Assert.notNull(per);
	}
	@Test
	public void testSavePersonalRecord() {
		final PersonalRecord pers = this.personalRecord.create();
		pers.setEmail("email@email.com");
		pers.setLinkedInProfile("https://www.linkedin.com/perfil");
		pers.setMiddleName("middlename");
		pers.setName("name");
		pers.setPhone("652915587");
		pers.setPhoto("https://google.com/photo");
		pers.setSurname("surname");
		final PersonalRecord saved = this.personalRecord.save(pers);
		final Collection<PersonalRecord> personals = this.personalRecord.findAll();
		Assert.isTrue(personals.contains(saved));
	}
	@Test
	public void testDeletePersonalRecord() {
		super.authenticate("handyWorker1");
		final Integer id = this.getEntityId("personalRecord1");
		final PersonalRecord per = this.personalRecord.findOne(id);
		this.personalRecord.delete(per);
		final PersonalRecord deleted = this.personalRecord.findOne(per.getId());
		Assert.isNull(deleted);
	}
}
