package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillDto;
import ogya.workshop.performance_appraisal.entity.*;
import ogya.workshop.performance_appraisal.repository.EmpAttitudeSkillRepo;
import ogya.workshop.performance_appraisal.service.EmpAttitudeSkillServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmpAttitudeSkillServImpl implements EmpAttitudeSkillServ {

    @Autowired
    private EmpAttitudeSkillRepo empAttitudeSkillRepo;

    // Create a new Group Achieve
    @Override
    public EmpAttitudeSkillDto createEmpAttitudeSkill(EmpAttitudeSkillCreateDto empAttitudeSkillDto) {
        EmpAttitudeSkill empAttitudeSkill = convertToEntity(empAttitudeSkillDto);
        empAttitudeSkill.setCreatedAt(new Date());  // Set the creation date
        EmpAttitudeSkill savedEmpAttitudeSkill = empAttitudeSkillRepo.save(empAttitudeSkill);
        return convertToDto(savedEmpAttitudeSkill);
    }

    // Update an existing Achieve
    @Override
    public EmpAttitudeSkillDto updateEmpAttitudeSkill(UUID id, EmpAttitudeSkillCreateDto empAttitudeSkillDto) {
        if (!empAttitudeSkillRepo.existsById(id)) {
            throw new IllegalArgumentException("Group Achievement with this ID does not exist.");
        }

        EmpAttitudeSkill empAttitudeSkill = convertToEntity(empAttitudeSkillDto);
        empAttitudeSkill.setId(id);  // Use the ID from the URL path
        empAttitudeSkill.setUpdatedAt(new Date());  // Set the updated date

        // Ensure 'createdAt' is set if it's null during the update
        if (empAttitudeSkill.getCreatedAt() == null) {
            empAttitudeSkill.setCreatedAt(new Date());  // Set current date if null
        }

        EmpAttitudeSkill updatedEmpAttitudeSkill = empAttitudeSkillRepo.save(empAttitudeSkill);
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
        empAttitudeSkillDto.setCreatedBy(empAttitudeSkill.getCreatedBy());
        empAttitudeSkillDto.setUpdatedAt(empAttitudeSkill.getUpdatedAt());
        empAttitudeSkillDto.setUpdatedBy(empAttitudeSkill.getUpdatedBy());
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
