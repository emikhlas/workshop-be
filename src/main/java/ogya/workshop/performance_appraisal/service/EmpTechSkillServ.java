package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.emptechskill.EmpTechSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.emptechskill.EmpTechSkillDto;
import ogya.workshop.performance_appraisal.dto.emptechskill.EmpTechSkillUserDto;

import java.util.List;
import java.util.UUID;

public interface EmpTechSkillServ {
    List<EmpTechSkillDto> findAll();
    List<EmpTechSkillDto> findAllByTechSkillId(UUID techSkillId);
    List<EmpTechSkillDto> save(List<EmpTechSkillCreateDto> empTechSkillDtos);
    Boolean deleteById(UUID id);
    EmpTechSkillDto update(UUID id, EmpTechSkillCreateDto empTechSkillDto);
    List<EmpTechSkillUserDto> findByUserId(UUID userId);
    List<EmpTechSkillDto> findByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);
    List<Integer> getAllEmpTechSkillYears();
}
