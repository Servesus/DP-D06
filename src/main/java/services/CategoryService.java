
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
import domain.Administrator;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	//Managed Repository
	@Autowired
	private CategoryRepository		categoryRepository;

	//Supporting services
	@Autowired
	private ActorService			actorService;
	@Autowired
	private AdministratorService	administratorService;


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
		Assert.notNull(result);

		return result;
	}

	public Category findOne(final Integer categoryId) {
		Assert.isTrue(categoryId != 0);
		return this.categoryRepository.findOne(categoryId);
	}

	public Category save(final Category c) {
		Actor a;
		a = this.actorService.getActorLogged();

		Assert.isTrue(a.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
		Assert.notNull(c);

		Administrator admin;
		admin = this.administratorService.findOne(a.getId());

		Category result;
		result = this.categoryRepository.save(c);
		Collection<Category> categories;
		categories = admin.getCategories();
		categories.add(result);
		admin.setCategories(categories);
		this.administratorService.save(admin);

		return result;
	}

	public void delete(final Category c) {
		Actor a;
		a = this.actorService.getActorLogged();
		Collection<Category> categories;

		Assert.isTrue(a.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
		Assert.notNull(c);

		Collection<Administrator> admins;
		admins = this.administratorService.findAll();

		for (final Administrator admin : admins)
			if (admin.getCategories().contains(c)) {

				categories = admin.getCategories();
				categories.remove(c);
				admin.setCategories(categories);
				this.administratorService.save(admin);
			}
		Assert.notNull(c);
		Assert.isTrue(c.getName() != "CATEGORY");
		if (!(c.getChilds().isEmpty())) {
			for (final Category c1 : c.getChilds())
				this.categoryRepository.delete(c1);
			this.categoryRepository.delete(c);
		} else
			this.categoryRepository.delete(c);
	}
}
