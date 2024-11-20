package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.empdevplan.EmpDevPlanCreateDto;
import ogya.workshop.performance_appraisal.dto.empdevplan.EmpDevPlanDto;
import ogya.workshop.performance_appraisal.dto.user.UserByDto;
import ogya.workshop.performance_appraisal.entity.*;
import ogya.workshop.performance_appraisal.repository.DevPlanRepo;
import ogya.workshop.performance_appraisal.repository.EmpDevPlanRepo;
import ogya.workshop.performance_appraisal.repository.UserRepo;
import ogya.workshop.performance_appraisal.service.EmpDevPlanServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DevPlanRepo devPlanRepo;

    // Create a new Group Achieve
    @Override
    public EmpDevPlanDto createEmpDevPlan(EmpDevPlanCreateDto empDevPlanDto) {
        EmpDevPlan empDevPlan = convertToEntity(empDevPlanDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        empDevPlan.setCreatedBy(creator);

        empDevPlan.setCreatedAt(new Date());  // Set the creation date
        EmpDevPlan savedEmpDevPlan = empDevPlanRepo.save(empDevPlan);
        return convertToDto(savedEmpDevPlan);
    }

    // Update an existing Achieve
    @Override
    public EmpDevPlanDto updateEmpDevPlan(UUID id, EmpDevPlanCreateDto empDevPlanDto) {
        EmpDevPlan currentEmpDevPlan = empDevPlanRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("EmpDevPlan with this ID does not exist."));

        if(empDevPlanDto.getUserId() != null){
            User user = userRepo.findById(empDevPlanDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("User with this ID does not exist."));
            currentEmpDevPlan.setUser(user);
        }

        if(empDevPlanDto.getDevPlanId() != null){
            DevPlan devPlan = devPlanRepo.findById(empDevPlanDto.getDevPlanId()).orElseThrow(() -> new IllegalArgumentException("DevPlan with this ID does not exist."));
            currentEmpDevPlan.setDevPlan(devPlan);
        }

        if(empDevPlanDto.getAssessmentYear() != null){
            currentEmpDevPlan.setAssessmentYear(empDevPlanDto.getAssessmentYear());
        }

        currentEmpDevPlan.setUpdatedAt(new Date());  // Set the updated date

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        currentEmpDevPlan.setUpdatedBy(creator);

        EmpDevPlan updatedEmpDevPlan = empDevPlanRepo.save(currentEmpDevPlan);
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
        if(empDevPlan.getCreatedBy() != null){
            empDevPlanDto.setCreatedBy(UserByDto.fromEntity(empDevPlan.getCreatedBy()));
        }
        empDevPlanDto.setUpdatedAt(empDevPlan.getUpdatedAt());
        if(empDevPlan.getUpdatedBy() != null){
            empDevPlanDto.setUpdatedBy(UserByDto.fromEntity(empDevPlan.getUpdatedBy()));
        }
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
