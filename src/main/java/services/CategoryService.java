
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Category;
import domain.FixUpTask;

@Service
@Transactional
public class CategoryService {

	//Managed Repository
	@Autowired
	private CategoryRepository	categoryRepository;

	//Supporting services
	@Autowired
	private ActorService		actorService;

	@Autowired
	private FixUpTaskService	fixUpTaskService;


	//Simple CRUD methods
	public Category create() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();

		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));

		Category result;
		result = new Category();

		return result;
	}

	public Collection<Category> findAll() {
		Collection<Category> result;

		result = this.categoryRepository.findAll();

		return result;
	}

	public Category findOne(final Integer categoryId) {
		Assert.isTrue(categoryId != 0);
		Category category;
		category = this.categoryRepository.findOne(categoryId);
		return category;
	}

	public Category save(final Category c) {
		Actor a;
		a = this.actorService.getActorLogged();

		Assert.isTrue(a.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
		Assert.isTrue(c.getNameEN() != "CATEGORY");
		Assert.notNull(c);
		Category result;
		if (c.getId() == 0) {
			result = this.categoryRepository.save(c);
			c.getParents().getChilds().add(result);
		} else {
			final Category oldParent = this.categoryRepository.findOne(c.getId()).getParents();
			if (oldParent.equals(c.getParents())) {
				oldParent.getChilds().remove(c);
				result = this.categoryRepository.save(c);
				c.getParents().getChilds().add(result);
			} else
				result = this.categoryRepository.save(c);

		}
		return result;
	}

	public void delete(final Category c) {
		Actor a;
		a = this.actorService.getActorLogged();

		Assert.isTrue(a.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
		Assert.notNull(c);

		for (final Category child : c.getChilds()) {
			final Category parent = c.getParents();
			child.setParents(parent);
		}
		for (final FixUpTask f : this.fixUpTaskService.findAll())
			if (f.getCategory().equals(c))
				f.setCategory(c.getParents());

		this.categoryRepository.delete(c);
	}
}
