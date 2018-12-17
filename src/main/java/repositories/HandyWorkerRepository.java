
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.FixUpTask;
import domain.HandyWorker;

@Repository
public interface HandyWorkerRepository extends JpaRepository<HandyWorker, Integer> {

	@Query("select h.applications from HandyWorker h where h.id=?1")
	List<Application> getApplicationsById(Integer id);

	@Query("select f.finder.fixUpTask from HandyWorker f where f.id=?1")
	Collection<FixUpTask> fixUpTasksInFinder(int handyWorkerId);

	@Query("select a from HandyWorker h join h.applications a where a.status=1 and h.id=?1")
	List<Application> getAcceptedApplicationsByHW(int id);
}
