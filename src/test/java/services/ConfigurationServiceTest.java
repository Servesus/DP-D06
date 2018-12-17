
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Configuration;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ConfigurationServiceTest extends AbstractTest {

	@Autowired
	private ConfigurationService	configurationService;


	@Test
	public void testCreateConfiguration() {
		Configuration c;
		c = this.configurationService.create();
		Assert.notNull(c);
	}
	@Test
	public void testSaveConfiguration() {
		super.authenticate("admin1");
		final Configuration c = this.configurationService.create();
		final Configuration saved = this.configurationService.save(c);
		Assert.notNull(saved);
		super.authenticate(null);

	}
	@Test
	public void testDeleteConfiguration() {
		super.authenticate("admin1");
		final int configId = this.getEntityId("configuration1");
		final Configuration c = this.configurationService.findOne(configId);
		this.configurationService.delete(c);
		Assert.isNull(this.configurationService.findOne(c.getId()));
		super.authenticate(null);
	}
}
