package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupAttitudeSkillServ {
    GroupAttitudeSkillDto createGroupAttitudeSkill(GroupAttitudeSkillDto groupAttitudeSkillDto);
    GroupAttitudeSkillDto updateGroupAttitudeSkill(UUID id, GroupAttitudeSkillDto groupAttitudeSkillDto);
    Optional<GroupAttitudeSkillDto> getGroupAttitudeSkillById(UUID id);
    List<GroupAttitudeSkillDto> getAllGroupAttitudeSkills();
    boolean deleteGroupAttitudeSkill(UUID id);
}
