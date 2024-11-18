package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.devplan.DevPlanDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DevPlanServ {
    DevPlanDto createDevPlan(DevPlanDto devPlanDto);
    DevPlanDto updateDevPlan(UUID id, DevPlanDto devPlanDto);
    Optional<DevPlanDto> getDevPlanById(UUID id);
    List<DevPlanDto> getAllDevPlan();
    boolean deleteDevPlan(UUID id);
}
