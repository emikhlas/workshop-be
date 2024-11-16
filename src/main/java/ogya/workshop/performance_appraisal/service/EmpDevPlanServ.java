package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.DevPlanDto;
import ogya.workshop.performance_appraisal.dto.EmpDevPlanDto;
import ogya.workshop.performance_appraisal.repository.EmpDevPlanRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmpDevPlanServ {
    EmpDevPlanDto createEmpDevPlan(EmpDevPlanDto empDevPlanDto);
    EmpDevPlanDto updateEmpDevPlan(UUID id, EmpDevPlanDto empDevPlanDto);
    Optional<EmpDevPlanDto> getEmpDevPlanById(UUID id);
    List<EmpDevPlanDto> getAllEmpDevPlan();
    boolean deleteEmpDevPlan(UUID id);
}
