package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.GroupAchieve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface GroupAchieveRepo extends JpaRepository<GroupAchieve, UUID> {
    @Query(value = "SELECT g.id, g.group_achievement_name, g.percentage ,count(a.id) as count " +
            "from group_achievement g join achievement a " +
            "on g.id = a.group_achievement_id group by g.id, g.group_achievement_name;",
            nativeQuery = true)
    List<Map<String, Object>> getGroupAchieveWithCount();
}
