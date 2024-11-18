package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.EmpTechSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmpTechSkillRepo extends JpaRepository<EmpTechSkill, UUID> {

    List<EmpTechSkill> findByUserId(UUID empId);

    List<EmpTechSkill> findByTechSkillId(UUID techSkillId);
}
