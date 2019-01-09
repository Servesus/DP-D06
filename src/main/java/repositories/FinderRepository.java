
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;
import domain.FixUpTask;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	//"select f from FixUpTask f where f.ticker like '%?1%' or f.description like '%?1%' or f.address like '%?1%'"
	@Query("select f from FixUpTask f where f.ticker like ?1 or f.description like ?1 or f.address like ?1")
	Collection<FixUpTask> getFixUpTasksByKeyWord(String singleKeyWord);

	@Query("select f from FixUpTask f where f.startDate BETWEEN ?1 and ?2")
	Collection<FixUpTask> getFixUpTasksByDateRange(Date dateMin, Date dateMax);

	@Query("select f from FixUpTask f where f.maxPrice BETWEEN ?1 AND ?2")
	Collection<FixUpTask> getFixUpTasksByPriceRange(Double minPrice, Double maxPrice);

	@Query("select f from FixUpTask f where f.category.nameEN like ?1 or f.category.nameES like ?1")
	Collection<FixUpTask> getFixUpTasksByCategory(String categoryName);

	@Query("select f from FixUpTask f where f.warranty.title like ?1")
	Collection<FixUpTask> getFixUpTasksByWarranty(String warrantyTitle);

}
