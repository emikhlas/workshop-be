package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.EmpAttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.GroupAttitudeSkillDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmpAttitudeSkillServ {
    EmpAttitudeSkillDto createEmpAttitudeSkill(EmpAttitudeSkillDto empAttitudeSkillDto);
    EmpAttitudeSkillDto updateEmpAttitudeSkill(UUID id, EmpAttitudeSkillDto empAttitudeSkillDto);
    Optional<EmpAttitudeSkillDto> getEmpAttitudeSkillById(UUID id);
    List<EmpAttitudeSkillDto> getAllEmpAttitudeSkills();
    boolean deleteEmpAttitudeSkill(UUID id);
}
