package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeSkillDto;
import ogya.workshop.performance_appraisal.entity.AttitudeSkill;
import ogya.workshop.performance_appraisal.entity.GroupAttitudeSkill;
import ogya.workshop.performance_appraisal.repository.AttitudeSkillRepo;
import ogya.workshop.performance_appraisal.service.AttitudeSkillServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AttitudeSkillServImpl implements AttitudeSkillServ {

    @Autowired
    private AttitudeSkillRepo attitudeSkillRepo;

    // Create a new Group Achieve
    @Override
    public AttitudeSkillDto createAttitudeSkill(AttitudeSkillDto attitudeSkillDto) {
        AttitudeSkill attitudeSkill = convertToEntity(attitudeSkillDto);
        attitudeSkill.setCreatedAt(new Date());  // Set the creation date
        AttitudeSkill savedAttitudeSkill = attitudeSkillRepo.save(attitudeSkill);
        return convertToDto(savedAttitudeSkill);
    }

    // Update an existing Achieve
    @Override
    public AttitudeSkillDto updateAttitudeSkill(UUID id, AttitudeSkillDto attitudeSkillDto) {
        if (!attitudeSkillRepo.existsById(id)) {
            throw new IllegalArgumentException("Group Achievement with this ID does not exist.");
        }

        AttitudeSkill attitudeSkill = convertToEntity(attitudeSkillDto);
        attitudeSkill.setId(id);  // Use the ID from the URL path
        attitudeSkill.setUpdatedAt(new Date());  // Set the updated date

        // Ensure 'createdAt' is set if it's null during the update
        if (attitudeSkill.getCreatedAt() == null) {
            attitudeSkill.setCreatedAt(new Date());  // Set current date if null
        }

        AttitudeSkill updatedAttitudeSkill = attitudeSkillRepo.save(attitudeSkill);
        return convertToDto(updatedAttitudeSkill);
    }

    // Retrieve by ID
    @Override
    public Optional<AttitudeSkillDto> getAttitudeSkillById(UUID id) {
        Optional<AttitudeSkill> attitudeSkill = attitudeSkillRepo.findById(id);
        return attitudeSkill.map(this::convertToDto);
    }

    // Retrieve all Achievements
    @Override
    public List<AttitudeSkillDto> getAllAttitudeSkills() {
        List<AttitudeSkill> attitudeSkill = attitudeSkillRepo.findAll();
        return attitudeSkill.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Delete an Achieve by ID
    @Override
    public boolean deleteAttitudeSkill(UUID id) {
        attitudeSkillRepo.deleteById(id);
        return true;
    }

    // Helper method to convert Achieve entity to AchieveDto
    private AttitudeSkillDto convertToDto(AttitudeSkill attitudeSkill) {
        AttitudeSkillDto attitudeSkillDto = new AttitudeSkillDto();
        attitudeSkillDto.setId(attitudeSkill.getId());
        attitudeSkillDto.setAttitudeSkillName(attitudeSkill.getAttitudeSkillName());
        if (attitudeSkill.getGroupAttitudeSkill() != null) {
            attitudeSkillDto.setGroupAttitudeSkillId(attitudeSkill.getGroupAttitudeSkill().getId());
        }
        attitudeSkillDto.setEnabled(attitudeSkill.getEnabled());
        attitudeSkillDto.setCreatedAt(attitudeSkill.getCreatedAt());
        attitudeSkillDto.setCreatedBy(attitudeSkill.getCreatedBy());
        attitudeSkillDto.setUpdatedAt(attitudeSkill.getUpdatedAt());
        attitudeSkillDto.setUpdatedBy(attitudeSkill.getUpdatedBy());
        return attitudeSkillDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private AttitudeSkill convertToEntity(AttitudeSkillDto attitudeSkillDto) {
        AttitudeSkill attitudeSkill = new AttitudeSkill();
        attitudeSkill.setId(attitudeSkillDto.getId());
        attitudeSkill.setAttitudeSkillName(attitudeSkillDto.getAttitudeSkillName());
        if (attitudeSkillDto.getGroupAttitudeSkillId() != null) {
            GroupAttitudeSkill groupAttitudeSkill = new GroupAttitudeSkill();
            groupAttitudeSkill.setId(attitudeSkillDto.getGroupAttitudeSkillId());
            attitudeSkill.setGroupAttitudeSkill(groupAttitudeSkill);
        }
        attitudeSkill.setEnabled(attitudeSkillDto.getEnabled());
        return attitudeSkill;
    }

}
