package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttWithAttDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillInfoWithCountDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupAttitudeSkillServ {
    GroupAttitudeSkillDto createGroupAttitudeSkill(GroupAttitudeSkillCreateDto groupAttitudeSkillDto);
    GroupAttitudeSkillDto updateGroupAttitudeSkill(UUID id, GroupAttitudeSkillCreateDto groupAttitudeSkillDto);
    Optional<GroupAttitudeSkillDto> getGroupAttitudeSkillById(UUID id);
    List<GroupAttitudeSkillDto> getAllGroupAttitudeSkills(boolean enabledOnly);
    boolean deleteGroupAttitudeSkill(UUID id);
    GroupAttWithAttDto getGroupWithAttitudeSkills(UUID id);
    List<GroupAttWithAttDto> getAllGroupWithAttitudeSkills();
    List<GroupAttitudeSkillInfoWithCountDto> getGroupAttitudeSkillWithCount();

}
