
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BoxRepository;
import domain.Actor;
import domain.Box;
import domain.Message;

@Service
@Transactional
public class BoxService {

	//Managed repository
	@Autowired
	private BoxRepository	boxRepository;

	//Supporting service
	@Autowired
	private ActorService	actorService;


	//Simple CRUD methods
	public Box create() {
		final Box box = new Box();
		box.setMessages(new ArrayList<Message>());
		box.setIsSystem(false);
		return box;
	}

	public Collection<Box> findAll() {
		return this.boxRepository.findAll();
	}

	public Box findOne(final int boxID) {
		return this.boxRepository.findOne(boxID);
	}
	//fallo: cuando va metiendo en spambox o inbox en el if estas buscando en boxes del actor
	//logeado las cajas del reciever y da -1 en index por eso
	public Box save(final Box box) {
		/*
		 * if (box.getIsSystem() == null)
		 * box.setIsSystem(false);
		 * final Box result = this.boxRepository.save(box);
		 * final Actor a = this.actorService.getActorLogged();
		 * final List<Box> boxes = (List<Box>) a.getBoxes();
		 * final int index = boxes.indexOf(this.findOne(result.getId()));
		 * if (result.getIsSystem()) {
		 * final Box systemBox = boxes.get(index);
		 * Assert.isTrue(systemBox.getName().equals(result.getName()) && result.getParentBoxes().isEmpty());
		 * }
		 * boxes.remove(this.findOne(result.getId()));
		 * boxes.add(index, result);
		 * a.setBoxes(boxes);
		 * this.actorService.save(a);
		 * return result;
		 */
		Assert.notNull(box);
		Box result;
		result = this.boxRepository.save(box);
		final Actor a = this.actorService.getActorLogged();
		final Collection<Box> boxes = a.getBoxes();
		if (box.getId() == 0) {
			boxes.add(result);
			a.setBoxes(boxes);
			//	this.actorService.save(a);
		}
		return result;

	}
	public Box saveRecipient(final Box box, final Actor a) {
		if (box.getIsSystem() == null)
			box.setIsSystem(false);
		final Box result = this.boxRepository.save(box);
		final List<Box> boxes = (List<Box>) a.getBoxes();
		final int index = boxes.indexOf(this.findOne(result.getId()));
		if (result.getIsSystem()) {
			final Box systemBox = boxes.get(index);
			Assert.isTrue(systemBox.getName().equals(result.getName()));
		}
		boxes.remove(this.findOne(result.getId()));
		boxes.add(index, result);
		a.setBoxes(boxes);
		this.actorService.save(a);
		return result;
	}
	public void delete(final Box box) {
		Assert.isTrue(box.getId() != 0);
		Assert.isTrue(!box.getIsSystem());
		final Actor a = this.actorService.getActorLogged();
		final Collection<Box> boxes = a.getBoxes();
		boxes.remove(box);
		this.boxRepository.delete(box);

	}

	//Other business methods

	public Collection<Box> createSystemBoxes() {
		final List<Box> res = new ArrayList<Box>();
		//crear cajas memoria vacias
		final Box inBox = this.create();
		final Box outBox = this.create();
		final Box trashBox = this.create();
		final Box spamBox = this.create();
		//poner nombres cajas
		inBox.setName("INBOX");
		outBox.setName("OUTBOX");
		trashBox.setName("TRASHBOX");
		spamBox.setName("SPAMBOX");
		//hacer cajas del sistema
		inBox.setIsSystem(true);
		outBox.setIsSystem(true);
		trashBox.setIsSystem(true);
		spamBox.setIsSystem(true);
		//añadir cajas al result
		res.add(this.boxRepository.save(inBox));
		res.add(this.boxRepository.save(outBox));
		res.add(this.boxRepository.save(trashBox));
		res.add(this.boxRepository.save(spamBox));
		//result
		return res;
	}

}
