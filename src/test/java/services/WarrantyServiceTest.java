
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
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class WarrantyServiceTest extends AbstractTest {

	@Autowired
	private WarrantyService	warrantyService;


	@Test
	public void testCreateWarranty() {

		final Warranty warranty = this.warrantyService.create();
		Assert.notNull(warranty);
	}
	@Test
	public void testFindOneWarranty() {
		super.authenticate("admin1");
		final int id = this.getEntityId("warranty1");
		final Warranty resultado = this.warrantyService.findOne(id);
		Assert.isTrue(resultado.getTitle().equals("Garantia Monitor LG"));
		super.authenticate(null);

	}
	@Test
	public void testFindAllWarranty() {
		super.authenticate("admin1");
		final Collection<Warranty> res = this.warrantyService.findAll();
		Assert.notNull(res);
		super.authenticate(null);

	}
	@Test
	public void testSaveWarranty() {
		super.authenticate("admin1");
		final Warranty a = this.warrantyService.create();
		final Warranty b = this.warrantyService.save(a);
		Assert.notNull(b);
		super.authenticate(null);

	}
	@Test
	public void testDeleteWarranty() {

		super.authenticate("admin1");
		final int id = this.getEntityId("warranty1");
		final Warranty a = this.warrantyService.findOne(id);
		this.warrantyService.delete(a);
		Assert.isNull(this.warrantyService.findOne(id));
		super.authenticate(null);

	}

}
