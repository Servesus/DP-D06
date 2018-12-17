
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;
import domain.FixUpTask;

@Service
@Transactional
public class FinderService {

	//Managed Repository
	@Autowired
	private FinderRepository	finderRepository;


	//Services

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
		final Collection<FixUpTask> fixUps = finder.getFixUpTask();
		if (finder.getSingleKeyWord() != null && finder.getSingleKeyWord() != "") {
			final Collection<FixUpTask> fixUpTasks = this.finderRepository.getFixUpTasksByKeyWord(finder.getSingleKeyWord());
			fixUps.addAll(fixUpTasks);
		}
		if (finder.getDateFinishRange() != null && finder.getDateStartRange() != null) {
			final Collection<FixUpTask> fixUpTasks = this.finderRepository.getFixUpTasksByDateRange(finder.getDateStartRange(), finder.getDateFinishRange());
			fixUps.addAll(fixUpTasks);
		}
		if (finder.getRangeStart() != null && finder.getRangeFinish() != null) {
			final Collection<FixUpTask> fixUpTasks = this.finderRepository.getFixUpTasksByPriceRange(finder.getRangeStart(), finder.getRangeFinish());
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
		finder.setFixUpTask(fixUps);
		final Date moment = new Date();
		finder.setLastUpdate(moment);
		finder = this.finderRepository.save(finder);
		return finder;
	}

}
