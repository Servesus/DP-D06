
package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProfileRepository;
import domain.Actor;
import domain.Profile;

@Service
@Transactional
public class ProfileService {

	@Autowired
	private ProfileRepository	profileRepository;
	@Autowired
	private ActorService		actorService;


	public Profile create() {
		Profile result;
		result = new Profile();
		return result;
	}

	public List<Profile> findAll() {
		return this.profileRepository.findAll();
	}

	public Profile findOne(final Integer arg0) {
		return this.profileRepository.findOne(arg0);
	}

	public Profile save(final Profile profile) {

		Profile result;
		Assert.notNull(profile);
		final Actor user = this.actorService.getActorLogged();
		result = this.profileRepository.save(profile);
		if (profile.getId() == 0)
			user.getProfiles().add(result);
		return result;
	}

	public void delete(final Profile profile) {

		final Actor actor = this.actorService.getActorLogged();
		Assert.notNull(profile);
		Assert.isTrue(this.profileRepository.exists(profile.getId()));
		final Collection<Profile> profiles = actor.getProfiles();
		profiles.remove(profile);
		this.profileRepository.delete(profile);
	}
}
