package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.TechSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TechSkillRepo extends JpaRepository<TechSkill, UUID> {


    List<TechSkill> findAllByEnabled(int i);
}
