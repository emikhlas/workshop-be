package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.Achieve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AchieveRepo extends JpaRepository<Achieve, UUID> {

}
