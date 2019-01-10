
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Configuration;
import domain.Finder;
import domain.FixUpTask;

@Service
@Transactional
public class FinderService {

	//Managed Repository
	@Autowired
	private FinderRepository		finderRepository;

	//Services
	@Autowired
	private ConfigurationService	configurationService;


	//Simple CRUD Methods
	public Finder create() {
		Finder result;
		result = new Finder();
		final Collection<FixUpTask> fixUps = new ArrayList<FixUpTask>();
		result.setFixUpTask(fixUps);
		return result;
	}
	public List<Finder> findAll() {
		return this.finderRepository.findAll();
	}

	public Finder findOne(final Integer id) {
		return this.finderRepository.findOne(id);
	}

	public Finder save(Finder finder) {
		Assert.notNull(finder);
		final Set<FixUpTask> fixUps = new HashSet<FixUpTask>();
		if (finder.getSingleKeyWord() != null && finder.getSingleKeyWord() != "") {
			final Collection<FixUpTask> fixUpTasks = this.finderRepository.getFixUpTasksByKeyWord(finder.getSingleKeyWord());
			fixUps.addAll(fixUpTasks);
		}
		if (finder.getDateFinishRange() != null && finder.getDateStartRange() != null) {
			final Collection<FixUpTask> fixUpTasks = this.finderRepository.getFixUpTasksByDateRange(finder.getDateStartRange(), finder.getDateFinishRange());
			fixUps.addAll(fixUpTasks);
		}
		if (finder.getRangeStart() != null && finder.getRangeFinish() != null) {
			final Integer minPrice = finder.getRangeStart();
			final Integer maxPrice = finder.getRangeFinish();
			final Collection<FixUpTask> fixUpTasks = this.finderRepository.getFixUpTasksByPriceRange(minPrice * 1.0, maxPrice * 1.0);
			fixUps.addAll(fixUpTasks);
		}
		if (finder.getCategoryName() != null && finder.getCategoryName() != "") {
			final Collection<FixUpTask> fixUpTasks = this.finderRepository.getFixUpTasksByCategory(finder.getCategoryName());
			fixUps.addAll(fixUpTasks);
		}
		if (finder.getWarrantyTitle() != null && finder.getWarrantyTitle() != "") {
			final Collection<FixUpTask> fixUpTasks = this.finderRepository.getFixUpTasksByWarranty(finder.getWarrantyTitle());
			fixUps.addAll(fixUpTasks);
		}

		Configuration conf;
		conf = this.configurationService.findAll().get(0);
		List<FixUpTask> fixUpsL = new ArrayList<FixUpTask>(fixUps);
		//conf.getMaxResults()
		if (fixUps.size() > conf.getMaxResults()) {
			//final int size = fixUps.size();
			final List<FixUpTask> fixUpsLim = new ArrayList<FixUpTask>();
			for (int i = 0; i < conf.getMaxResults(); i++)
				fixUpsLim.add(fixUpsL.get(i));
			fixUpsL = fixUpsLim;
		}

		finder.setFixUpTask(fixUpsL);
		final Date moment = new Date();
		finder.setLastUpdate(moment);
		finder = this.finderRepository.save(finder);
		return finder;
	}
}
