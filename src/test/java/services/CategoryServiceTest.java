
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
import domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CategoryServiceTest extends AbstractTest {

	//Service testing
	@Autowired
	private CategoryService	categoryService;


	@Test
	public void create() {
		super.authenticate("admin1");
		Category c;
		c = this.categoryService.create();

		Assert.notNull(c);
		super.authenticate(null);
	}

	@Test
	public void save() {
		super.authenticate("admin1");
		Category c;
		Category saved;
		Collection<Category> categories;

		c = this.categoryService.create();
		c.setNameEN("test");
		c.setNameES("prueba");
		saved = this.categoryService.save(c);
		categories = this.categoryService.findAll();

		Assert.notNull(saved);
		Assert.isTrue(categories.contains(saved));
		Assert.notNull(categories);

		super.authenticate(null);

	}
	@Test
	public void findOne() {
		Integer id;
		id = this.getEntityId("category1");
		Category c;
		c = this.categoryService.findOne(id);
		Assert.notNull(c);
	}

	@Test
	public void findAll() {
		Collection<Category> categories;
		categories = this.categoryService.findAll();
		Assert.notNull(categories);
	}

	@Test
	public void delete() {
		super.authenticate("admin1");

		Category c;
		Integer id;
		Integer childId;
		id = this.getEntityId("category1");
		childId = this.getEntityId("category2");
		c = this.categoryService.findOne(id);
		this.categoryService.delete(c);
		Assert.isNull(this.categoryService.findOne(id));
		Assert.isNull(this.categoryService.findOne(childId));

		super.authenticate(null);
	}

}
