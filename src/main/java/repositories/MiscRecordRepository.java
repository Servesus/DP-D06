package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.MiscRecord;

@Repository
public interface MiscRecordRepository extends JpaRepository<MiscRecord,Integer>{

}
