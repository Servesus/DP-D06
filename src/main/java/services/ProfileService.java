
package services;

import java.util.ArrayList;
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
		result = this.profileRepository.save(profile);
		return result;
	}

	public void delete(final Profile profile) {

		Collection<Actor> actors = new ArrayList<Actor>();
		Assert.notNull(profile);
		assert profile.getId() != 0;
		Assert.isTrue(this.profileRepository.exists(profile.getId()));
		actors = this.actorService.findAll();
		for (final Actor a : actors)
			if (a.getProfiles().contains(profile)) {
				final Collection<Profile> profiles = a.getProfiles();
				profiles.remove(profile);
				a.setProfiles(profiles);
				this.actorService.save(a);
			}
		this.profileRepository.delete(profile);
	}
}
