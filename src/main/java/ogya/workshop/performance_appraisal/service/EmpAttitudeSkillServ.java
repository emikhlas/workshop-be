package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmpAttitudeSkillServ {
    List<EmpAttitudeSkillDto> createEmpAttitudeSkills(List<EmpAttitudeSkillCreateDto> empAttitudeSkillDtos);
    EmpAttitudeSkillDto updateEmpAttitudeSkill(UUID id, EmpAttitudeSkillCreateDto empAttitudeSkillDto);
    Optional<EmpAttitudeSkillDto> getEmpAttitudeSkillById(UUID id);
    List<EmpAttitudeSkillDto> getAllEmpAttitudeSkills();
    boolean deleteEmpAttitudeSkill(UUID id);
    List<EmpAttitudeSkillDto> getEmpAttSkillByUserId(UUID userId, Integer year);
    List<Integer> getAllEmpAttitudeSkillYears();

}
