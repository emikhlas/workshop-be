package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.empdevplan.EmpDevPlanCreateDto;
import ogya.workshop.performance_appraisal.dto.empdevplan.EmpDevPlanDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmpDevPlanServ {
    List<EmpDevPlanDto> createEmpDevPlan(List<EmpDevPlanCreateDto> empDevPlanDtos);

    EmpDevPlanDto updateEmpDevPlan(UUID id, EmpDevPlanCreateDto empDevPlanDto);

    Optional<EmpDevPlanDto> getEmpDevPlanById(UUID id);

    List<EmpDevPlanDto> getAllEmpDevPlan();

    boolean deleteEmpDevPlan(UUID id);

    List<EmpDevPlanDto> getEmpDevPlanByUserId(UUID userId);

    List<EmpDevPlanDto> getEmpDevPlanWithPlan(UUID userId);

    List<EmpDevPlanDto> getEmpDevPlanByUserIdAndYear(UUID userId, Integer assessmentYear);

    List<Integer> getAllEmpDevPlanYear();

}
