package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.AssessSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AssessSumRepo extends JpaRepository<AssessSum, UUID> {

    List<AssessSum> findByUserId(UUID userId);

    AssessSum findByUserIdAndYear(UUID userId, Integer year);

    List<AssessSum> findByYear(Integer year);
}
