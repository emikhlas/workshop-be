package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeWithGroupNameDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttitudeSkillServ {
    AttitudeSkillDto createAttitudeSkill(AttitudeSkillCreateDto attitudeSkillDto);

    AttitudeSkillDto updateAttitudeSkill(UUID id, AttitudeSkillCreateDto attitudeSkillDto);

    Optional<AttitudeSkillDto> getAttitudeSkillById(UUID id);

    List<AttitudeSkillDto> getAllAttitudeSkills(boolean enabledOnly);

    boolean deleteAttitudeSkill(UUID id);

    List<AttitudeWithGroupNameDto> getAllAttitudeWithGroupName();
}
