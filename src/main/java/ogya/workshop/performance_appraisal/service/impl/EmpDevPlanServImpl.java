package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.empdevplan.EmpDevPlanCreateDto;
import ogya.workshop.performance_appraisal.dto.empdevplan.EmpDevPlanDto;
import ogya.workshop.performance_appraisal.entity.DevPlan;
import ogya.workshop.performance_appraisal.entity.EmpDevPlan;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.EmpDevPlanRepo;
import ogya.workshop.performance_appraisal.service.EmpDevPlanServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmpDevPlanServImpl implements EmpDevPlanServ {

    @Autowired
    private EmpDevPlanRepo empDevPlanRepo;

    // Create a new Group Achieve
    @Override
    public EmpDevPlanDto createEmpDevPlan(EmpDevPlanCreateDto empDevPlanDto) {
        EmpDevPlan empDevPlan = convertToEntity(empDevPlanDto);
        empDevPlan.setCreatedAt(new Date());  // Set the creation date
        EmpDevPlan savedEmpDevPlan = empDevPlanRepo.save(empDevPlan);
        return convertToDto(savedEmpDevPlan);
    }

    // Update an existing Achieve
    @Override
    public EmpDevPlanDto updateEmpDevPlan(UUID id, EmpDevPlanCreateDto empDevPlanDto) {
        if (!empDevPlanRepo.existsById(id)) {
            throw new IllegalArgumentException("Group Achievement with this ID does not exist.");
        }

        EmpDevPlan empDevPlan = convertToEntity(empDevPlanDto);
        empDevPlan.setId(id);  // Use the ID from the URL path
        empDevPlan.setUpdatedAt(new Date());  // Set the updated date

        // Ensure 'createdAt' is set if it's null during the update
        if (empDevPlan.getCreatedAt() == null) {
            empDevPlan.setCreatedAt(new Date());  // Set current date if null
        }

        EmpDevPlan updatedEmpDevPlan = empDevPlanRepo.save(empDevPlan);
        return convertToDto(updatedEmpDevPlan);
    }

    // Retrieve by ID
    @Override
    public Optional<EmpDevPlanDto> getEmpDevPlanById(UUID id) {
        Optional<EmpDevPlan> empDevPlan = empDevPlanRepo.findById(id);
        return empDevPlan.map(this::convertToDto);
    }

    // Retrieve all Achievements
    @Override
    public List<EmpDevPlanDto> getAllEmpDevPlan() {
        List<EmpDevPlan> empDevPlan = empDevPlanRepo.findAll();
        return empDevPlan.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Delete an Achieve by ID
    @Override
    public boolean deleteEmpDevPlan(UUID id) {
        empDevPlanRepo.deleteById(id);
        return true;
    }

    // Helper method to convert Achieve entity to AchieveDto
    private EmpDevPlanDto convertToDto(EmpDevPlan empDevPlan) {
        EmpDevPlanDto empDevPlanDto = new EmpDevPlanDto();
        empDevPlanDto.setId(empDevPlan.getId());
        if (empDevPlan.getUser() != null) {
            empDevPlanDto.setUserId(empDevPlan.getUser().getId());
        }
        if (empDevPlan.getDevPlan() != null) {
            empDevPlanDto.setDevPlanId(empDevPlan.getDevPlan().getId());
        }
        empDevPlanDto.setAssessmentYear(empDevPlan.getAssessmentYear());
        empDevPlanDto.setCreatedAt(empDevPlan.getCreatedAt());
        empDevPlanDto.setCreatedBy(empDevPlan.getCreatedBy());
        empDevPlanDto.setUpdatedAt(empDevPlan.getUpdatedAt());
        empDevPlanDto.setUpdatedBy(empDevPlan.getUpdatedBy());
        return empDevPlanDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private EmpDevPlan convertToEntity(EmpDevPlanCreateDto empDevPlanDto) {
        EmpDevPlan empDevPlan = new EmpDevPlan();
        if (empDevPlanDto.getUserId() != null) {
            User user = new User();
            user.setId(empDevPlanDto.getUserId());
            empDevPlan.setUser(user);
        }
        if (empDevPlanDto.getDevPlanId() != null) {
            DevPlan devPlan = new DevPlan();
            devPlan.setId(empDevPlanDto.getDevPlanId());
            empDevPlan.setDevPlan(devPlan);
        }
        empDevPlan.setAssessmentYear(empDevPlanDto.getAssessmentYear());
        return empDevPlan;
    }
}
