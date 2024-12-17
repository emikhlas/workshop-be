package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.empachieveskill.EmpAchieveSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.empachieveskill.EmpAchieveSkillDto;
import ogya.workshop.performance_appraisal.dto.empachieveskill.EmpAchieveSkillWithUserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmpAchieveSkillServ {
    EmpAchieveSkillDto createEmpAchieveSkill(EmpAchieveSkillCreateDto empAchieveSkillDto);
    EmpAchieveSkillDto updateEmpAchieveSkill(UUID id, EmpAchieveSkillCreateDto empAchieveSkillDto);
    Optional<EmpAchieveSkillDto> getEmpAchieveSkillById(UUID id);
    List<EmpAchieveSkillDto> getAllEmpAchieveSkill();
    boolean deleteEmpAchieveSkill(UUID id);
    List<EmpAchieveSkillWithUserDto> getAllEmpUserAchieve();
    List<EmpAchieveSkillDto> getAllEmpUserAchieveByUserId(UUID id, Integer year, boolean enabledOnly);
}
