package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeWithGroupNameDto;
import ogya.workshop.performance_appraisal.entity.AttitudeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttitudeSkillRepo extends JpaRepository<AttitudeSkill, UUID> {
    @Query("SELECT new ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeWithGroupNameDto(a.id, a.attitudeSkillName, g.id, g.groupName, a.enabled, a.createdAt, a.updatedAt) " +
            "FROM AttitudeSkill a JOIN a.groupAttitudeSkill g")
    List<AttitudeWithGroupNameDto> findAllWithGroupNames();
    List<AttitudeSkill> findAllByEnabled(int i);
    List<AttitudeSkill> findByGroupAttitudeSkill_Id(UUID groupAttitudeSkillId);

}



