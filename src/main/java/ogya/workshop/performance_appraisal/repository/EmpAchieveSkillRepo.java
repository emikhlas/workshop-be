package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.dto.achieve.AchieveWithGroupNameDto;
import ogya.workshop.performance_appraisal.dto.empachieveskill.EmpAchieveWithUserAchieveDto;
import ogya.workshop.performance_appraisal.entity.EmpAchieveSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmpAchieveSkillRepo extends JpaRepository<EmpAchieveSkill, UUID> {
    @Query("SELECT new ogya.workshop.performance_appraisal.dto.empachieveskill.EmpAchieveWithUserAchieveDto(" +
            "e.id, u.id, e.notes, a.id, e.score, e.assessmentYear, u.username, " +
            "a.achievementName, a.createdAt, a.updatedAt) " +
            "FROM EmpAchieveSkill e " +
            "JOIN e.user u " +
            "JOIN e.achieve a")
    List<EmpAchieveWithUserAchieveDto> findEmpAchieveUser();

}
