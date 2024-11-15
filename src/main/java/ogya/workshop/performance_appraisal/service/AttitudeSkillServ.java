package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.AttitudeSkillDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttitudeSkillServ {
    AttitudeSkillDto createAttitudeSkill(AttitudeSkillDto attitudeSkillDto);
    AttitudeSkillDto updateAttitudeSkill(UUID id, AttitudeSkillDto attitudeSkillDto);
    Optional<AttitudeSkillDto> getAttitudeSkillById(UUID id);
    List<AttitudeSkillDto> getAllAttitudeSkills();
    boolean deleteAttitudeSkill(UUID id);
}
