package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.EmpDevPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmpDevPlanRepo extends JpaRepository<EmpDevPlan, UUID> {
    List<EmpDevPlan> findByUserId(UUID userId);

    List<EmpDevPlan> findByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);

    @Query("SELECT DISTINCT e.assessmentYear FROM EmpDevPlan e ORDER BY e.assessmentYear DESC")
    List<Integer> findDistinctAssessmentYears();
}
