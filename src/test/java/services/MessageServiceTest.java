
package services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Box;
import domain.Curricula;
import domain.HandyWorker;
import domain.Message;
import domain.PersonalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MessageServiceTest extends AbstractTest {

	@Autowired
	private MessageService		messageService;
	@Autowired
	private HandyWorkerService	handyWorkerService;


	@Test
	public void testCreateMessage() {
		super.authenticate("handyWorker1");
		final Message message = this.messageService.create();
		Assert.isTrue(message.getBody() == null && message.getRecipient().isEmpty() && message.getSubject() == null);
	}

	@Test
	public void testSaveMessage() {
		//crear handyWorkers con cajas por defecto
		//h1
		final HandyWorker h1 = this.handyWorkerService.create();
		HandyWorker saved;
		h1.setName("name1");
		h1.setMiddleName("middleName1");
		h1.setSurname("surname1");
		h1.setPhoto("https://www.google.com/photo1");
		h1.setEmail("a@email.com");
		h1.setPhoneNumber("652914587");
		h1.setAddress("hola1");
		h1.getUserAccount().setUsername("manuelmanuel");
		h1.getUserAccount().setPassword("password1231");

		final Curricula curricula = h1.getCurricula();
		final PersonalRecord pers = curricula.getPersonalRecord();
		pers.setEmail("email@email.com");
		pers.setLinkedInProfile("https://www.linkedin.com/perfil");
		pers.setMiddleName("middlename");
		pers.setName("name1");
		pers.setPhone("652915587");
		pers.setPhoto("https://google.com/photo1");
		pers.setSurname("surname1");
		curricula.setPersonalRecord(pers);
		h1.setCurricula(curricula);
		saved = this.handyWorkerService.save(h1);
		//h2
		final HandyWorker h2 = this.handyWorkerService.create();
		HandyWorker saved1;
		h2.setName("name2");
		h2.setMiddleName("middleName2");
		h2.setSurname("surname2");
		h2.setPhoto("https://www.google.com/photo2");
		h2.setEmail("b@email.com");
		h2.setPhoneNumber("652914587");
		h2.setAddress("hola2");
		h2.getUserAccount().setUsername("mecagoentus");
		h2.getUserAccount().setPassword("password1232");

		final Curricula curricula1 = h2.getCurricula();
		final PersonalRecord pers1 = curricula1.getPersonalRecord();
		pers1.setEmail("email@email.com");
		pers1.setLinkedInProfile("https://www.linke2din.com/perfil");
		pers1.setMiddleName("middlename2");
		pers1.setName("name2");
		pers1.setPhone("652915587");
		pers1.setPhoto("https://google.com/photo2");
		pers1.setSurname("surname2");
		curricula1.setPersonalRecord(pers1);
		h2.setCurricula(curricula1);
		saved1 = this.handyWorkerService.save(h2);
		//test save message
		super.authenticate("manuelmanuel");
		final HandyWorker receiver = saved1;
		final Message message = this.messageService.create();
		message.setPriority(0);
		message.setBody("Esto es una prueba");
		message.getRecipient().add(receiver);
		message.setSubject("Prueba");
		final Message spam = this.messageService.create();
		spam.setPriority(0);
		spam.setBody("Vendo viagra");
		spam.getRecipient().add(receiver);
		spam.setSubject("Hola");
		final Message savedS = this.messageService.save(spam);
		final Message savedM = this.messageService.save(message);
		final HandyWorker sender = saved;
		final List<Box> senderBoxes = (List<Box>) sender.getBoxes();
		final List<Box> receiverBoxes = (List<Box>) receiver.getBoxes();
		Assert.isTrue(senderBoxes.get(1).getMessages().contains(savedS) && senderBoxes.get(1).getMessages().contains(savedM) && receiverBoxes.get(0).getMessages().contains(savedM) && receiverBoxes.get(3).getMessages().contains(savedS));

	}

}
