package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.empdevplan.EmpDevPlanDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.*;
import ogya.workshop.performance_appraisal.repository.EmpAttitudeSkillRepo;
import ogya.workshop.performance_appraisal.service.EmpAttitudeSkillServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmpAttitudeSkillServImpl implements EmpAttitudeSkillServ {

    @Autowired
    private EmpAttitudeSkillRepo empAttitudeSkillRepo;

    // Create a new Group Achieve
//    @Override
//    public EmpAttitudeSkillDto createEmpAttitudeSkill(EmpAttitudeSkillCreateDto empAttitudeSkillDto) {
//        EmpAttitudeSkill empAttitudeSkill = convertToEntity(empAttitudeSkillDto);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        AuthUser authUser = (AuthUser) authentication.getPrincipal();
//        User creator = authUser.getUser();
//        empAttitudeSkill.setCreatedBy(creator);
//        empAttitudeSkill.setCreatedAt(new Date());  // Set the creation date
//        EmpAttitudeSkill savedEmpAttitudeSkill = empAttitudeSkillRepo.save(empAttitudeSkill);
//        return convertToDto(savedEmpAttitudeSkill);
//    }

    @Override
    public List<EmpAttitudeSkillDto> createEmpAttitudeSkills(List<EmpAttitudeSkillCreateDto> empAttitudeSkillDtos) {
        List<EmpAttitudeSkillDto> result = new ArrayList<>();
        for (EmpAttitudeSkillCreateDto empAttitudeSkillDto : empAttitudeSkillDtos) {
            EmpAttitudeSkill empAttitudeSkill = convertToEntity(empAttitudeSkillDto);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AuthUser authUser = (AuthUser) authentication.getPrincipal();
            User creator = authUser.getUser();
            empAttitudeSkill.setCreatedBy(creator);
            empAttitudeSkill.setCreatedAt(new Date());  // Set the creation date

            EmpAttitudeSkill savedEmpAttitudeSkill = empAttitudeSkillRepo.save(empAttitudeSkill);
            result.add(convertToDto(savedEmpAttitudeSkill));
        }
        return result;
    }

    // Update an existing Achieve
    @Override
    public EmpAttitudeSkillDto updateEmpAttitudeSkill(UUID id, EmpAttitudeSkillCreateDto empAttitudeSkillDto) {
        EmpAttitudeSkill currentEmpAttitudeSkill = empAttitudeSkillRepo.findById(id).orElseThrow( () -> new RuntimeException("EmpAttitudeSkill not found"));

        if (empAttitudeSkillDto.getUserId() != null) {
            User user = new User();
            user.setId(empAttitudeSkillDto.getUserId());
            currentEmpAttitudeSkill.setUser(user);
        }
        if (empAttitudeSkillDto.getAttitudeSkillId() != null) {
            AttitudeSkill attitudeSkill = new AttitudeSkill();
            attitudeSkill.setId(empAttitudeSkillDto.getAttitudeSkillId());
            currentEmpAttitudeSkill.setAttitudeSkill(attitudeSkill);
        }
        currentEmpAttitudeSkill.setScore(empAttitudeSkillDto.getScore());
        currentEmpAttitudeSkill.setAssessmentYear(empAttitudeSkillDto.getAssessmentYear());


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        currentEmpAttitudeSkill.setUpdatedBy(creator);
        currentEmpAttitudeSkill.setUpdatedAt(new Date());


        EmpAttitudeSkill updatedEmpAttitudeSkill = empAttitudeSkillRepo.save(currentEmpAttitudeSkill);
        return convertToDto(updatedEmpAttitudeSkill);
    }

    // Retrieve by ID
    @Override
    public Optional<EmpAttitudeSkillDto> getEmpAttitudeSkillById(UUID id) {
        Optional<EmpAttitudeSkill> empAttitudeSkill = empAttitudeSkillRepo.findById(id);
        return empAttitudeSkill.map(this::convertToDto);
    }

    // Retrieve all Achievements
    @Override
    public List<EmpAttitudeSkillDto> getAllEmpAttitudeSkills() {
        List<EmpAttitudeSkill> empAttitudeSkills = empAttitudeSkillRepo.findAll();
        return empAttitudeSkills.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<EmpAttitudeSkillDto> getEmpAttSkillByUserId(UUID userId){
        List<EmpAttitudeSkill> empAttitudeSkills = empAttitudeSkillRepo.findByUserId(userId);

        List<EmpAttitudeSkillDto> empAttitudeSkillDtos = empAttitudeSkills.stream().map(empAttitudeSkill -> {
            EmpAttitudeSkillDto empAttitudeSkillDto = convertToDto(empAttitudeSkill);
            if (empAttitudeSkill.getAttitudeSkill() != null) {
                empAttitudeSkillDto.setAttitudeSkillName(empAttitudeSkill.getAttitudeSkill().getAttitudeSkillName());  // Assuming DevPlan has a 'plan' attribute
            }
            return empAttitudeSkillDto;
        }).collect(Collectors.toList());

        return empAttitudeSkillDtos;

    }

    // Delete an Achieve by ID
    @Override
    public boolean deleteEmpAttitudeSkill(UUID id) {
        empAttitudeSkillRepo.deleteById(id);
        return true;
    }

    // Helper method to convert Achieve entity to AchieveDto
    private EmpAttitudeSkillDto convertToDto(EmpAttitudeSkill empAttitudeSkill) {
        EmpAttitudeSkillDto empAttitudeSkillDto = new EmpAttitudeSkillDto();
        empAttitudeSkillDto.setId(empAttitudeSkill.getId());
        if (empAttitudeSkill.getUser() != null) {
            empAttitudeSkillDto.setUserId(empAttitudeSkill.getUser().getId());
        }
        if (empAttitudeSkill.getAttitudeSkill() != null) {
            empAttitudeSkillDto.setAttitudeSkillId(empAttitudeSkill.getAttitudeSkill().getId());
        }
        empAttitudeSkillDto.setScore(empAttitudeSkill.getScore());
        empAttitudeSkillDto.setAssessmentYear(empAttitudeSkill.getAssessmentYear());

        empAttitudeSkillDto.setCreatedAt(empAttitudeSkill.getCreatedAt());
        empAttitudeSkillDto.setUpdatedAt(empAttitudeSkill.getUpdatedAt());

        if (empAttitudeSkill.getCreatedBy() != null) {
            empAttitudeSkillDto.setCreatedBy(UserInfoDto.fromEntity(empAttitudeSkill.getCreatedBy()));
        }
        if (empAttitudeSkill.getUpdatedBy() != null) {
            empAttitudeSkillDto.setUpdatedBy(UserInfoDto.fromEntity(empAttitudeSkill.getUpdatedBy()));
        }

        return empAttitudeSkillDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private EmpAttitudeSkill convertToEntity(EmpAttitudeSkillCreateDto empAttitudeSkillDto) {
        EmpAttitudeSkill empAttitudeSkill = new EmpAttitudeSkill();
        if (empAttitudeSkillDto.getUserId() != null) {
            User user = new User();
            user.setId(empAttitudeSkillDto.getUserId());
            empAttitudeSkill.setUser(user);
        }
        if (empAttitudeSkillDto.getAttitudeSkillId() != null) {
            AttitudeSkill attitudeSkill = new AttitudeSkill();
            attitudeSkill.setId(empAttitudeSkillDto.getAttitudeSkillId());
            empAttitudeSkill.setAttitudeSkill(attitudeSkill);
        }
        empAttitudeSkill.setScore(empAttitudeSkillDto.getScore());
        empAttitudeSkill.setAssessmentYear(empAttitudeSkillDto.getAssessmentYear());
        return empAttitudeSkill;
    }

}
