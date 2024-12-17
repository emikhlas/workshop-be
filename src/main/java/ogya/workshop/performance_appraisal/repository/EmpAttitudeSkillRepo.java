package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.EmpAttitudeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmpAttitudeSkillRepo extends JpaRepository<EmpAttitudeSkill, UUID> {
    List<EmpAttitudeSkill> findByUserId(UUID userId);
    List<EmpAttitudeSkill> findByUserIdAndAssessmentYear(UUID userId, Integer year);
    @Query("SELECT DISTINCT e.assessmentYear FROM EmpAttitudeSkill e ORDER BY e.assessmentYear ASC")
    List<Integer> findDistinctAssessmentYears();
}
