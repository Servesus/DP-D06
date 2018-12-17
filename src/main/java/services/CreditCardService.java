package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.CreditCard;
import domain.Customer;

import repositories.CreditCardRepository;
import security.UserAccount;


@Service
@Transactional
public class CreditCardService {
	
	//Managed repositories
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private CustomerService customerService;
	
	//CRUD methods
	
	public CreditCard create(){
		CreditCard result;
		UserAccount userAccount;
		
		userAccount=actorService.getActorLogged().getUserAccount();
		
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority()
				.equals("CUSTOMER"));
		
		result= new CreditCard();
		
		return result;
	}
	
	public CreditCard save(CreditCard creditCard){
		Assert.notNull(creditCard);
		Actor actor;
		Customer customer;
		Collection<CreditCard> creditCards;
		
		actor = actorService.getActorLogged();
		customer= customerService.findOne(actor.getId());
		creditCards= customer.getCreditCards();
		
		CreditCard result;
		
		result= creditCardRepository.save(creditCard);
		
		creditCards.add(result);
		customer.setCreditCards(creditCards);
		customerService.save(customer);
		
		return result;
	}
	
	public void delete(CreditCard creditCard){
		Assert.notNull(creditCard);
		Assert.isTrue(creditCard.getId() != 0);
		
		Collection<Customer> customers;
		
		customers= customerService.findAll();
		
		for(Customer c : customers){
			if(c.getCreditCards().contains(creditCard)){
				Collection<CreditCard> cre = c.getCreditCards();
				cre.remove(creditCard);
				c.setCreditCards(cre);
				customerService.save(c);
			}
		}
		
		creditCardRepository.delete(creditCard);
	}

	public CreditCard findOne(Integer id) {
		Assert.notNull(id);
		UserAccount userAccount = actorService.getActorLogged().getUserAccount();
		Assert.isTrue(userAccount.getAuthorities().iterator().next().getAuthority()
				.equals("CUSTOMER"));
		
		return creditCardRepository.findOne(id);
	}

}
