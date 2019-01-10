
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import security.UserAccount;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	//Managed repositories
	@Autowired
	private CreditCardRepository	creditCardRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private CustomerService			customerService;


	//CRUD methods

	public CreditCard create() {
		CreditCard result;
		UserAccount userAccount;

		userAccount = this.actorService.getActorLogged().getUserAccount();

		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("CUSTOMER"));

		result = new CreditCard();

		return result;
	}

	public CreditCard save(final CreditCard creditCard) {
		Assert.notNull(creditCard);

		CreditCard result;

		result = this.creditCardRepository.save(creditCard);

		return result;
	}

	public CreditCard findOne(final Integer id) {
		Assert.notNull(id);
		final UserAccount userAccount = this.actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority().equals("CUSTOMER"));

		return this.creditCardRepository.findOne(id);
	}

}
