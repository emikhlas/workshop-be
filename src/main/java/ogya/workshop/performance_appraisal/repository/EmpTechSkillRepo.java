package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.EmpTechSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmpTechSkillRepo extends JpaRepository<EmpTechSkill, UUID> {
    List<EmpTechSkill> findByUserId(UUID userId);
    List<EmpTechSkill> findByTechSkillId(UUID techSkillId);
    List<EmpTechSkill> findByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);
    @Query("SELECT DISTINCT e.assessmentYear FROM EmpTechSkill e ORDER BY e.assessmentYear DESC")
    List<Integer> findDistinctAssessmentYears();

}
