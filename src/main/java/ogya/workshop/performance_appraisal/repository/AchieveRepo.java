package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.dto.achieve.AchieveWithGroupNameDto;
import ogya.workshop.performance_appraisal.entity.Achieve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AchieveRepo extends JpaRepository<Achieve, UUID> {
    @Query("SELECT new ogya.workshop.performance_appraisal.dto.achieve.AchieveWithGroupNameDto(a.id, a.achievementName, g.id, g.groupAchievementName, a.enabled, a.createdAt, a.updatedAt) " +
            "FROM Achieve a JOIN a.groupAchieve g")
    List<AchieveWithGroupNameDto> findAllWithGroupNames();

    List<Achieve> findByGroupAchieve_Id(UUID groupAchieveId);
}

