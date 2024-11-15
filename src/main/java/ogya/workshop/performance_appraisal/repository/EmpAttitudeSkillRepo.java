package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.EmpAttitudeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmpAttitudeSkillRepo extends JpaRepository<EmpAttitudeSkill, UUID> {
}
