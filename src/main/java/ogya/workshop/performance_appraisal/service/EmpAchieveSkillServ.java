package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.empachieveskill.EmpAchieveSkillDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmpAchieveSkillServ {
    EmpAchieveSkillDto createEmpAchieveSkill(EmpAchieveSkillDto empAchieveSkillDto);
    EmpAchieveSkillDto updateEmpAchieveSkill(UUID id, EmpAchieveSkillDto empAchieveSkillDto);
    Optional<EmpAchieveSkillDto> getEmpAchieveSkillById(UUID id);
    List<EmpAchieveSkillDto> getAllEmpAchieveSkill();
    boolean deleteEmpAchieveSkill(UUID id);
}
