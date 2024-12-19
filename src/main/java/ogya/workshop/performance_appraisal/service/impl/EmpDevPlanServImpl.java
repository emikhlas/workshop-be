package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.empdevplan.EmpDevPlanCreateDto;
import ogya.workshop.performance_appraisal.dto.empdevplan.EmpDevPlanDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.DevPlan;
import ogya.workshop.performance_appraisal.entity.EmpDevPlan;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.DevPlanRepo;
import ogya.workshop.performance_appraisal.repository.EmpDevPlanRepo;
import ogya.workshop.performance_appraisal.repository.UserRepo;
import ogya.workshop.performance_appraisal.service.EmpDevPlanServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmpDevPlanServImpl implements EmpDevPlanServ {

    @Autowired
    private EmpDevPlanRepo empDevPlanRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DevPlanRepo devPlanRepo;

    @Override
    public List<EmpDevPlanDto> createEmpDevPlan(List<EmpDevPlanCreateDto> empDevPlanDtos) {
        List<EmpDevPlanDto> result = new ArrayList<>();
        for (EmpDevPlanCreateDto empDevPlanDto : empDevPlanDtos) {
            EmpDevPlan empDevPlan = convertToEntity(empDevPlanDto);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AuthUser authUser = (AuthUser) authentication.getPrincipal();
            User creator = authUser.getUser();
            empDevPlan.setCreatedBy(creator);
            empDevPlan.setCreatedAt(new Date());  // Set the creation date

            EmpDevPlan savedEmpDevPlan = empDevPlanRepo.save(empDevPlan);
            result.add(convertToDto(savedEmpDevPlan));
        }
        return result;
    }

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

    @Override
    public Optional<EmpDevPlanDto> getEmpDevPlanById(UUID id) {
        Optional<EmpDevPlan> empDevPlan = empDevPlanRepo.findById(id);
        return empDevPlan.map(this::convertToDto);
    }

    @Override
    public List<EmpDevPlanDto> getAllEmpDevPlan() {
        List<EmpDevPlan> empDevPlan = empDevPlanRepo.findAll();
        return empDevPlan.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<EmpDevPlanDto> getEmpDevPlanByUserId(UUID userId) {
        List<EmpDevPlan> empDevPlans = empDevPlanRepo.findByUserId(userId);
        return empDevPlans.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<EmpDevPlanDto> getEmpDevPlanWithPlan(UUID userId) {
        List<EmpDevPlan> empDevPlans = empDevPlanRepo.findByUserId(userId);

        List<EmpDevPlanDto> empDevPlanDtos = empDevPlans.stream().map(empDevPlan -> {
            EmpDevPlanDto empDevPlanDto = convertToDto(empDevPlan);
            // Fetch the DevPlan and include the plan in the DTO
            if (empDevPlan.getDevPlan() != null) {
                empDevPlanDto.setPlan(empDevPlan.getDevPlan().getPlan());  // Assuming DevPlan has a 'plan' attribute
            }
            return empDevPlanDto;
        }).collect(Collectors.toList());

        return empDevPlanDtos;
    }

    @Override
    public boolean deleteEmpDevPlan(UUID id) {
        empDevPlanRepo.deleteById(id);
        return true;
    }

    @Override
    public List<EmpDevPlanDto> getEmpDevPlanByUserIdAndYear(UUID userId, Integer assessmentYear) {
        List<EmpDevPlan> empDevPlans = empDevPlanRepo.findByUserIdAndAssessmentYear(userId, assessmentYear);

        // Transform empDevPlans to EmpDevPlanDto including the 'plan' attribute
        List<EmpDevPlanDto> empDevPlanDtos = empDevPlans.stream().map(empDevPlan -> {
            EmpDevPlanDto empDevPlanDto = convertToDto(empDevPlan);
            // Fetch the DevPlan and include the 'plan' in the DTO
            if (empDevPlan.getDevPlan() != null) {
                empDevPlanDto.setPlan(empDevPlan.getDevPlan().getPlan());
            }
            return empDevPlanDto;
        }).collect(Collectors.toList());

        return empDevPlanDtos;
    }

    @Override
    public List<Integer> getAllEmpDevPlanYear() {
        return empDevPlanRepo.findDistinctAssessmentYears();
    }

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
            empDevPlanDto.setCreatedBy(UserInfoDto.fromEntity(empDevPlan.getCreatedBy()));
        }
        empDevPlanDto.setUpdatedAt(empDevPlan.getUpdatedAt());
        if(empDevPlan.getUpdatedBy() != null){
            empDevPlanDto.setUpdatedBy(UserInfoDto.fromEntity(empDevPlan.getUpdatedBy()));
        }
        empDevPlanDto.setPlanDetail(empDevPlan.getPlanDetail());
        empDevPlanDto.setStatus(empDevPlan.getStatus());
        return empDevPlanDto;
    }

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
        empDevPlan.setPlanDetail(empDevPlanDto.getPlanDetail());
        empDevPlan.setAssessmentYear(empDevPlanDto.getAssessmentYear());
        empDevPlan.setStatus(empDevPlanDto.getStatus());
        return empDevPlan;
    }
}
