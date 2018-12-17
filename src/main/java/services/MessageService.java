
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Box;
import domain.Message;

@Service
@Transactional
public class MessageService {

	//Managed repository
	@Autowired
	private MessageRepository		messageRepository;
	//Services
	@Autowired
	private ActorService			actorService;
	@Autowired
	private BoxService				boxService;
	@Autowired
	private AdministratorService	administratorService;


	//Simple CRUD Methods
	public Message create() {
		//Assert.isTrue
		Message result;
		result = new Message();
		final Actor sender = this.actorService.getActorLogged();
		final Date actualDate = Calendar.getInstance().getTime();
		result.setSendDate(actualDate);
		result.setSender(sender);
		result.setRecipient(new ArrayList<Actor>());
		return result;
	}

	public List<Message> findAll() {
		return this.messageRepository.findAll();
	}

	public Message findOne(final Integer arg0) {
		return this.messageRepository.findOne(arg0);
	}

	public Message save(final Message message) {
		//Declarar result
		final Date actualDate = Calendar.getInstance().getTime();
		message.setSendDate(actualDate);
		final Message result = this.messageRepository.save(message);
		//Asserts y sacar sender y recipient
		Assert.notNull(result);
		Assert.isTrue(result.getId() != 0);
		final Actor sender = result.getSender();
		final List<Actor> recipients = (List<Actor>) result.getRecipient();
		for (int i = 0; i < recipients.size(); i++) {
			final Actor a = recipients.get(i);
			Assert.isTrue(a.getId() != 0);
		}
		//Set sender message
		result.setSender(sender);
		//meter message outbox sender
		final List<Box> boxesS = (List<Box>) sender.getBoxes();
		final Box outBoxS = boxesS.get(1);
		final List<Message> m = (List<Message>) outBoxS.getMessages();
		m.add(result);
		outBoxS.setMessages(m);
		this.boxService.save(outBoxS);
		//lista de palabras spam
		final String[] spam = {
			"sex", "viagra", "cialis", "one million", "you've been selected", "Nigeria", "sexo", "un millón", "ha sido seleccionado"
		};
		//meter message inbox/spambox recipient
		boolean msgIsSpam = false;
		for (int i = 0; i < spam.length; i++)
			if (result.getSubject().contains(spam[i]) || result.getBody().contains(spam[i]))
				msgIsSpam = true;
		if (msgIsSpam)
			for (int i = 0; i < recipients.size(); i++) {
				final Actor a = recipients.get(i);
				final List<Box> boxesR = (List<Box>) a.getBoxes();
				final Box spamBoxRx = boxesR.get(3);
				final List<Message> m1 = (List<Message>) spamBoxRx.getMessages();
				m1.add(result);
				spamBoxRx.setMessages(m1);
				this.boxService.saveRecipient(spamBoxRx, a);
			}
		else
			for (int i = 0; i < recipients.size(); i++) {
				final Actor a = recipients.get(i);
				final List<Box> boxesR = (List<Box>) a.getBoxes();
				final Box inBoxRx = boxesR.get(0);
				final List<Message> m1 = (List<Message>) inBoxRx.getMessages();
				m1.add(result);
				inBoxRx.setMessages(m1);
				this.boxService.saveRecipient(inBoxRx, a);
			}
		//Guardar mensaje en BD
		return result;
	}

	public void delete(final Message message) {
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		final List<Box> boxes = new ArrayList<Box>();
		boxes.addAll(this.boxService.findAll());
		boolean msgInAnyBox = false;
		for (int i = 0; i < boxes.size(); i++)
			if (boxes.get(i).getMessages().contains(message)) {
				msgInAnyBox = true;
				break;
			}
		if (msgInAnyBox)
			this.messageRepository.delete(message);
	}

	//Other business methods

	public void moveMessage(final Message message, final Box boxR) {
		//Asserts e inicializaciones
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		final Actor a = message.getSender();
		final List<Box> boxesActor = (List<Box>) a.getBoxes();
		Box originBox = null;
		boolean msgInActor = false;
		boolean boxInActor = false;
		for (int i = 0; i < a.getBoxes().size(); i++) {
			if (boxesActor.get(i).getMessages().contains(message)) {
				originBox = boxesActor.get(i);
				msgInActor = true;
			}
			if (boxesActor.get(i).equals(boxR))
				boxInActor = true;
		}
		//Mover mensage
		final List<Message> oM = (List<Message>) originBox.getMessages();
		oM.remove(message);
		originBox.setMessages(oM);
		this.boxService.save(originBox);
		final List<Message> dM = (List<Message>) boxR.getMessages();
		dM.add(message);
		boxR.setMessages(dM);
		this.boxService.save(boxR);

	}

	public void deleteMessage(final Message message) {
		//Asserts e inicializaciones
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		final Actor a = message.getSender();
		final List<Box> boxesActor = (List<Box>) a.getBoxes();
		Box originBox = null;
		boolean msgInActor = false;
		for (int i = 0; i < a.getBoxes().size(); i++)
			if (boxesActor.get(i).getMessages().contains(message)) {
				originBox = boxesActor.get(i);
				msgInActor = true;
			}
		Assert.isTrue(msgInActor);
		//Si no es trashbox, mover a trashbox
		if (boxesActor.get(2) != originBox) {
			final Box trashBox = boxesActor.get(2);
			final List<Message> oM = (List<Message>) originBox.getMessages();
			oM.remove(message);
			originBox.setMessages(oM);
			this.boxService.save(originBox);
			final List<Message> tM = (List<Message>) trashBox.getMessages();
			tM.add(message);
			trashBox.setMessages(tM);
			this.boxService.save(trashBox);
			//Si trashbox, borrar de actor
		} else
			/*
			 * for (int i = 0; i < a.getBoxes().size(); i++)
			 * if (boxesActor.get(i).getMessages().contains(message)) {
			 * final Box boxWithMsg = boxesActor.get(i);
			 * final List<Message> msgs = (List<Message>) boxWithMsg.getMessages();
			 * msgs.remove(message);
			 * boxWithMsg.setMessages(msgs);
			 * this.boxService.save(boxWithMsg);
			 * /*
			 * final Box boxWithMsg = boxesActor.get(i);
			 * boxesActor.remove(boxWithMsg);
			 * boxWithMsg.getMessages().remove(message);
			 * boxesActor.add(boxWithMsg);
			 * a.setBoxes(boxesActor);
			 * this.boxService.save(boxWithMsg);
			 */
			for (final Box b : a.getBoxes())
				if (b.getMessages().contains(message)) {
					final Collection<Message> messages = b.getMessages();
					messages.remove(message);
					b.setMessages(messages);
					this.boxService.save(b);
					this.delete(message);
				}
	}

	public Message adminMessage(final Message message) {
		//Asserts e inicializaciones
		Assert.notNull(message);
		Assert.isTrue(message.getId() == 0);
		//Mandar mensaje a todos los actores del sistema
		final Actor admin = this.administratorService.findOne(this.actorService.getActorLogged().getId());
		final List<Actor> recipient = new ArrayList<Actor>();
		recipient.addAll(this.actorService.findAll());
		recipient.remove(admin);
		message.setSender(admin);
		message.setRecipient(recipient);
		return this.save(message);
	}
}
