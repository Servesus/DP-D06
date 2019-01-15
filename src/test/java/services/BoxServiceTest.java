
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
import domain.HandyWorker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class BoxServiceTest extends AbstractTest {

	@Autowired
	private BoxService			boxService;
	@Autowired
	private HandyWorkerService	handyWorkerService;


	@Test
	public void testCreateBox() {
		super.authenticate("handyWorker1");
		final Box box = this.boxService.create();
		Assert.isTrue(box.getIsSystem() == null && box.getName() == null && box.getMessages().isEmpty());
	}

	@Test
	public void testSaveBox() {
		super.authenticate("handyWorker1");
		final Box box = this.boxService.create();
		box.setName("box34");
		final Box saved = this.boxService.save(box);
		final HandyWorker hw = this.handyWorkerService.findOne(this.getEntityId("handyWorker1"));
		Assert.isTrue(hw.getBoxes().contains(saved));
	}

	@Test
	public void testDeleteBox() {
		super.authenticate("handyWorker1");
		final HandyWorker hw = this.handyWorkerService.findOne(this.getEntityId("handyWorker1"));
		final List<Box> boxes = (List<Box>) hw.getBoxes();
		final Box box = boxes.get(0);
		this.boxService.delete(box);
		Assert.isTrue(hw.getBoxes().isEmpty());

	}

	//@Test
	public void testCreateSystem() {
		//Este metodo funciona cuando se crea cualquier actor del sistema
	}

}
