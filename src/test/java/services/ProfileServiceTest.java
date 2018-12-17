
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
import domain.Profile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ProfileServiceTest extends AbstractTest {

	@Autowired
	private ProfileService	profileService;


	@Test
	public void testCreateProfile() {
		final Profile create = this.profileService.create();
		Assert.notNull(create);
	}
	@Test
	public void testFindOneProfile() {

		final int id = this.getEntityId("profile1");
		Profile a;
		a = this.profileService.findOne(id);
		Assert.isTrue(a.getName().equals("valenciano"));
	}

	@Test
	public void testFindAllProfile() {
		final Collection<Profile> res = this.profileService.findAll();
		Assert.notNull(res);
	}
	@Test
	public void testSaveProfile() {
		final Profile profile = this.profileService.create();
		final Profile profileG = this.profileService.save(profile);
		Assert.isTrue(!profileG.equals(null));
	}
	@Test
	public void testDeleteProfile() {

		final int id = this.getEntityId("profile1");
		final Profile a = this.profileService.findOne(id);
		this.profileService.delete(a);
		Assert.isTrue(this.profileService.findAll().size() == 4);
	}
}
