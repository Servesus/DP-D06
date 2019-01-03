
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WarrantyRepository;
import security.UserAccount;
import domain.Warranty;

@Service
@Transactional
public class WarrantyService {

	@Autowired
	private WarrantyRepository	warrantyRepository;
	@Autowired
	private ActorService		actorService;


	public Warranty create() {
		Warranty result;
		result = new Warranty();
		return result;
	}

	public Collection<Warranty> findAll() {
		Collection<Warranty> result;
		UserAccount userAccount;

		userAccount = this.actorService.getActorLogged().getUserAccount();

		//Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
		Assert.notNull(this.warrantyRepository);
		result = this.warrantyRepository.findAll();
		return result;
	}

	public Warranty findOne(final int warrantyId) {
		Warranty result;
		Assert.notNull(this.warrantyRepository);
		result = this.warrantyRepository.findOne(warrantyId);
		return result;

	}

	public Warranty save(final Warranty warranty) {

		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
		Assert.notNull(warranty);
		if (warranty.getId() != 0)
			Assert.isTrue(!this.warrantyRepository.findOne(warranty.getId()).getIsFinal());
		Warranty result;
		result = this.warrantyRepository.save(warranty);
		return result;
	}

	public void delete(final Warranty warranty) {

		UserAccount userAccount;
		userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));
		Assert.notNull(warranty);
		Assert.isTrue(!warranty.getIsFinal());
		this.warrantyRepository.delete(warranty);
	}
}
