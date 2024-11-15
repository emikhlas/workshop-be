package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.EmpAchieveSkillDto;
import ogya.workshop.performance_appraisal.dto.GroupAchieveDto;
import ogya.workshop.performance_appraisal.entity.EmpAchieveSkill;
import ogya.workshop.performance_appraisal.entity.GroupAchieve;
import ogya.workshop.performance_appraisal.repository.EmpAchieveSkillRepo;
import ogya.workshop.performance_appraisal.service.EmpAchieveSkillServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public EmpAchieveSkillDto createEmpAchieveSkill(EmpAchieveSkillDto empAchieveSkillDto) {
        System.out.println("dto: "+ empAchieveSkillDto);
        EmpAchieveSkill empAchieveSkill = convertToEntity(empAchieveSkillDto);
        System.out.println("entity :" + empAchieveSkill);
        empAchieveSkill.setCreatedAt(new Date());  // Set the creation date
        EmpAchieveSkill savedEmpAchieveSkill = empAchieveSkillRepo.save(empAchieveSkill);
        return convertToDto(savedEmpAchieveSkill);
    }

    // Update an existing Achieve
    @Override
    public EmpAchieveSkillDto updateEmpAchieveSkill(UUID id, EmpAchieveSkillDto empAchieveSkillDto) {
        if (!empAchieveSkillRepo.existsById(id)) {
            throw new IllegalArgumentException("Group Achievement with this ID does not exist.");
        }

        EmpAchieveSkill empAchieveSkill = convertToEntity(empAchieveSkillDto);
        empAchieveSkill.setId(id);  // Use the ID from the URL path
        empAchieveSkill.setUpdatedAt(new Date());  // Set the updated date

        // Ensure 'createdAt' is set if it's null during the update
        if (empAchieveSkill.getCreatedAt() == null) {
            empAchieveSkill.setCreatedAt(new Date());  // Set current date if null
        }

        EmpAchieveSkill updatedEmpAchieveSkill = empAchieveSkillRepo.save(empAchieveSkill);
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
        empAchieveSkillDto.setUserId(empAchieveSkill.getUserId());
        empAchieveSkillDto.setAchievementId(empAchieveSkill.getAchievementId());
        empAchieveSkillDto.setScore(empAchieveSkill.getScore());
        empAchieveSkillDto.setAssessmentYear(empAchieveSkill.getAssessmentYear());
        return empAchieveSkillDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private EmpAchieveSkill convertToEntity(EmpAchieveSkillDto empAchieveSkillDto) {
        EmpAchieveSkill empAchieveSkill = new EmpAchieveSkill();
        empAchieveSkill.setId(empAchieveSkillDto.getId());
        empAchieveSkill.setAchievementId(empAchieveSkillDto.getAchievementId());
        empAchieveSkill.setUserId(empAchieveSkillDto.getUserId());
        empAchieveSkill.setScore(empAchieveSkillDto.getScore());
        empAchieveSkill.setAssessmentYear(empAchieveSkillDto.getAssessmentYear());
        return empAchieveSkill;
    }

}
