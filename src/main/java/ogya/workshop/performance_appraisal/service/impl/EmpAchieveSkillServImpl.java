package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.empachieveskill.EmpAchieveSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.empachieveskill.EmpAchieveSkillDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.Achieve;
import ogya.workshop.performance_appraisal.entity.EmpAchieveSkill;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.EmpAchieveSkillRepo;
import ogya.workshop.performance_appraisal.service.EmpAchieveSkillServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class EmpAchieveSkillServImpl implements EmpAchieveSkillServ {

    private final Logger Log = LoggerFactory.getLogger(EmpAchieveSkillServImpl.class);

    @Autowired
    private EmpAchieveSkillRepo empAchieveSkillRepo;

    // Create a new Group Achieve
    @Override
    public EmpAchieveSkillDto createEmpAchieveSkill(EmpAchieveSkillCreateDto empAchieveSkillDto) {
        System.out.println("dto: "+ empAchieveSkillDto);
        EmpAchieveSkill empAchieveSkill = convertToEntity(empAchieveSkillDto);
        System.out.println("entity :" + empAchieveSkill);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();
        empAchieveSkill.setCreatedBy(creator);
        empAchieveSkill.setCreatedAt(new Date());  // Set the creation date
        EmpAchieveSkill savedEmpAchieveSkill = empAchieveSkillRepo.save(empAchieveSkill);
        return convertToDto(savedEmpAchieveSkill);
    }

    // Update an existing Achieve
    @Override
    public EmpAchieveSkillDto updateEmpAchieveSkill(UUID id, EmpAchieveSkillCreateDto empAchieveSkillDto) {

        EmpAchieveSkill currentEAchieveSkill = empAchieveSkillRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Achieve ID: " + id));
        if (empAchieveSkillDto.getUserId() != null) {
            User user = new User();
            user.setId(empAchieveSkillDto.getUserId());
            currentEAchieveSkill.setUser(user);
        }
        if (empAchieveSkillDto.getAchievementId() != null) {
            Achieve achieve = new Achieve();
            achieve.setId(empAchieveSkillDto.getAchievementId());
            currentEAchieveSkill.setAchieve(achieve);
        }
        currentEAchieveSkill.setNotes(empAchieveSkillDto.getNotes());
        currentEAchieveSkill.setScore(empAchieveSkillDto.getScore());
        currentEAchieveSkill.setAssessmentYear(empAchieveSkillDto.getAssessmentYear());

        EmpAchieveSkill updatedEmpAchieveSkill = empAchieveSkillRepo.save(currentEAchieveSkill);
        return convertToDto(updatedEmpAchieveSkill);
    }

    // Retrieve by ID
    @Override
    public Optional<EmpAchieveSkillDto> getEmpAchieveSkillById(UUID id) {
        Optional<EmpAchieveSkill> empAchieveSkill = empAchieveSkillRepo.findById(id);
        return empAchieveSkill.map(this::convertToDto);
    }

    // Retrieve all Achievements
    @Override
    public List<EmpAchieveSkillDto> getAllEmpAchieveSkill() {
        List<EmpAchieveSkill> empAchieveSkills = empAchieveSkillRepo.findAll();
        return empAchieveSkills.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Delete an Achieve by ID
    @Override
    public boolean deleteEmpAchieveSkill(UUID id) {
        empAchieveSkillRepo.deleteById(id);
        return true;
    }

    // Helper method to convert Achieve entity to AchieveDto
    private EmpAchieveSkillDto convertToDto(EmpAchieveSkill empAchieveSkill) {
        EmpAchieveSkillDto empAchieveSkillDto = new EmpAchieveSkillDto();
        empAchieveSkillDto.setId(empAchieveSkill.getId());
        if (empAchieveSkill.getUser() != null) {
            empAchieveSkillDto.setUserId(empAchieveSkill.getUser().getId());
        }
        if (empAchieveSkill.getAchieve() != null) {
            empAchieveSkillDto.setAchievementId(empAchieveSkill.getAchieve().getId());
        }
        empAchieveSkillDto.setNotes(empAchieveSkill.getNotes());
        empAchieveSkillDto.setScore(empAchieveSkill.getScore());
        empAchieveSkillDto.setAssessmentYear(empAchieveSkill.getAssessmentYear());
        empAchieveSkillDto.setCreatedAt(empAchieveSkill.getCreatedAt());
        empAchieveSkillDto.setUpdatedAt(empAchieveSkill.getUpdatedAt());

        if(empAchieveSkill.getCreatedBy() != null) {
            empAchieveSkillDto.setCreatedBy(UserInfoDto.fromEntity(empAchieveSkill.getCreatedBy()));
        }

        if(empAchieveSkill.getUpdatedBy() != null) {
            empAchieveSkillDto.setUpdatedBy(UserInfoDto.fromEntity(empAchieveSkill.getUpdatedBy()));
        }
        return empAchieveSkillDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private EmpAchieveSkill convertToEntity(EmpAchieveSkillCreateDto empAchieveSkillDto) {
        EmpAchieveSkill empAchieveSkill = new EmpAchieveSkill();
        if (empAchieveSkillDto.getUserId() != null) {
            User user = new User();
            user.setId(empAchieveSkillDto.getUserId());
            empAchieveSkill.setUser(user);
        }
        if (empAchieveSkillDto.getAchievementId() != null) {
            Achieve achieve = new Achieve();
            achieve.setId(empAchieveSkillDto.getAchievementId());
            empAchieveSkill.setAchieve(achieve);
        }
        empAchieveSkill.setNotes(empAchieveSkillDto.getNotes());
        empAchieveSkill.setScore(empAchieveSkillDto.getScore());
        empAchieveSkill.setAssessmentYear(empAchieveSkillDto.getAssessmentYear());
        return empAchieveSkill;
    }

}
