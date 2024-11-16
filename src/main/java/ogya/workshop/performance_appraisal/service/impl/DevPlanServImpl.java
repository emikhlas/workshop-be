package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.AttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.DevPlanDto;
import ogya.workshop.performance_appraisal.entity.AttitudeSkill;
import ogya.workshop.performance_appraisal.entity.DevPlan;
import ogya.workshop.performance_appraisal.repository.DevPlanRepo;
import ogya.workshop.performance_appraisal.service.DevPlanServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DevPlanServImpl implements DevPlanServ {

    @Autowired
    private DevPlanRepo devPlanRepo;

    // Create a new Group Achieve
    @Override
    public DevPlanDto createDevPlan(DevPlanDto devPlanDto) {
        DevPlan devPlan = convertToEntity(devPlanDto);
        devPlan.setCreatedAt(new Date());  // Set the creation date
        DevPlan savedDevPlan = devPlanRepo.save(devPlan);
        return convertToDto(savedDevPlan);
    }

    // Update an existing Achieve
    @Override
    public DevPlanDto updateDevPlan(UUID id, DevPlanDto devPlanDto) {
        if (!devPlanRepo.existsById(id)) {
            throw new IllegalArgumentException("Group Achievement with this ID does not exist.");
        }

        DevPlan devPlan = convertToEntity(devPlanDto);
        devPlan.setId(id);  // Use the ID from the URL path
        devPlan.setUpdatedAt(new Date());  // Set the updated date

        // Ensure 'createdAt' is set if it's null during the update
        if (devPlan.getCreatedAt() == null) {
            devPlan.setCreatedAt(new Date());  // Set current date if null
        }

        DevPlan updatedDevPlan = devPlanRepo.save(devPlan);
        return convertToDto(updatedDevPlan);
    }

    // Retrieve by ID
    @Override
    public Optional<DevPlanDto> getDevPlanById(UUID id) {
        Optional<DevPlan> devPlan = devPlanRepo.findById(id);
        return devPlan.map(this::convertToDto);
    }

    // Retrieve all Achievements
    @Override
    public List<DevPlanDto> getAllDevPlan() {
        List<DevPlan> devPlan = devPlanRepo.findAll();
        return devPlan.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Delete an Achieve by ID
    @Override
    public boolean deleteDevPlan(UUID id) {
        devPlanRepo.deleteById(id);
        return true;
    }

    // Helper method to convert Achieve entity to AchieveDto
    private DevPlanDto convertToDto(DevPlan devPlan) {
        DevPlanDto devPlanDto = new DevPlanDto();
        devPlanDto.setId(devPlan.getId());
        devPlanDto.setPlan(devPlan.getPlan());
        devPlanDto.setEnabled(devPlan.getEnabled());
        return devPlanDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private DevPlan convertToEntity(DevPlanDto devPlanDto) {
        DevPlan devPlan = new DevPlan();
        devPlan.setId(devPlanDto.getId());
        devPlan.setPlan(devPlanDto.getPlan());
        devPlan.setEnabled(devPlanDto.getEnabled());
        return devPlan;
    }

}
