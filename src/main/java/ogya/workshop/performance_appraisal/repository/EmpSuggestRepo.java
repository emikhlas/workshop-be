package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.EmpSuggest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmpSuggestRepo extends JpaRepository<EmpSuggest, UUID> {
    List<EmpSuggest> findByUserId(UUID userId);
    List<EmpSuggest> findByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);

    @Query("SELECT DISTINCT e.assessmentYear FROM EmpSuggest e ORDER BY e.assessmentYear DESC")
    List<Integer> findDistinctAssessmentYears();
}
