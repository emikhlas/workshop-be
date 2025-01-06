package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillInfoWithCountDto;
import ogya.workshop.performance_appraisal.entity.GroupAttitudeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface GroupAttitudeSkillRepo extends JpaRepository<GroupAttitudeSkill, UUID> {
    @Query(value = "SELECT g.id, g.group_name, g.percentage,COUNT(a.id) as count, g.enabled " +
            "from group_attitude_skill g join attitude_skill a " +
            "on g.id = a.group_attitude_skill_id AND a.enabled = 1 " +
            "GROUP by g.id, g.group_name;",
            nativeQuery = true)
    List<Map<String, Object>> getGroupAttitudeSkillWithCount();
    List<GroupAttitudeSkill> findAllByEnabled(int b);
}
