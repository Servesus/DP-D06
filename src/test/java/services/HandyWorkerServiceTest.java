
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Curricula;
import domain.HandyWorker;
import domain.PersonalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class HandyWorkerServiceTest extends AbstractTest {

	//Service under test
	@Autowired
	private HandyWorkerService	handyWorkerService;


	@Test
	public void testCreate() {
		final HandyWorker h = this.handyWorkerService.create();
		Assert.notNull(h);
	}

	@Test
	public void testSave() {
		final HandyWorker h = this.handyWorkerService.create();
		HandyWorker saved;
		h.setName("name");
		h.setMiddleName("middleName");
		h.setSurname("surname");
		h.setPhoto("https://www.google.com/photo");
		h.setEmail("a@email.com");
		h.setPhoneNumber("652914587");
		h.setAddress("hola");
		h.getUserAccount().setUsername("username");
		h.getUserAccount().setPassword("password123");

		final Curricula curricula = h.getCurricula();
		final PersonalRecord pers = curricula.getPersonalRecord();
		pers.setEmail("email@email.com");
		pers.setLinkedInProfile("https://www.linkedin.com/perfil");
		pers.setMiddleName("middlename");
		pers.setName("name");
		pers.setPhone("652915587");
		pers.setPhoto("https://google.com/photo");
		pers.setSurname("surname");
		curricula.setPersonalRecord(pers);
		h.setCurricula(curricula);
		saved = this.handyWorkerService.save(h);
		final Collection<HandyWorker> hws = this.handyWorkerService.findAll();
		Assert.isTrue(hws.contains(saved));

	}

}
