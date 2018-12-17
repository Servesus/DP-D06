package security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ActorService;

import domain.Actor;

@Service
@Transactional
public class UserAccountService {

		// Managed repository -----------------------------------------------------

		@Autowired
		private UserAccountRepository	userAccountRepository;
		
		@Autowired
		private ActorService actorService;


		// Supporting services ----------------------------------------------------

		// Constructors -----------------------------------------------------------


		// Simple CRUD methods ----------------------------------------------------

		public UserAccount findByActor(final Actor actor) {
			Assert.notNull(actor);

			UserAccount result;

			result = this.userAccountRepository.findByActorId(actor.getId());

			return result;
		}
		
		public Collection<UserAccount> findAll() {

			Collection<UserAccount> result;
			result = this.userAccountRepository.findAll();
			Assert.notNull(result);
			return result;

		}

		public UserAccount findOne(final int userAccountId) {
			final Actor actor = this.actorService.getActorLogged();
			Assert.notNull(actor);

			Assert.notNull(userAccountId);
			UserAccount result;
			result = this.userAccountRepository.findOne(userAccountId);

			return result;
		}

		public UserAccount save(final UserAccount userAccount) {

			Assert.notNull(userAccount);
			UserAccount result;

			if (userAccount.getId() != 0) {

				final Actor actor = this.actorService.getActorLogged();
				Assert.notNull(actor);

				Assert.isTrue(actor.getId() == userAccount.getId());

				result = this.userAccountRepository.save(userAccount);

			} else
				result = this.userAccountRepository.save(userAccount);

			return result;

		}
}
