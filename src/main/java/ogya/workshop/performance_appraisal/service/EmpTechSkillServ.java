package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.emptechskill.EmpTechSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.emptechskill.EmpTechSkillDto;

import java.util.List;
import java.util.UUID;

public interface EmpTechSkillServ {
    List<EmpTechSkillDto> findAll();
    List<EmpTechSkillDto> findAllByEmpId(UUID empId);
    List<EmpTechSkillDto> findAllByTechSkillId(UUID techSkillId);
    EmpTechSkillDto save(EmpTechSkillCreateDto empTechSkillDto);
    Boolean deleteById(UUID id);
    EmpTechSkillDto update(UUID id, EmpTechSkillCreateDto empTechSkillDto);
}