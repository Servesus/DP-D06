package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.EducationalRecord;

@Repository
public interface EducationalRecordRepository extends JpaRepository<EducationalRecord,Integer>{

}
