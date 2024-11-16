package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.GroupAttitudeSkillDto;
import ogya.workshop.performance_appraisal.entity.GroupAttitudeSkill;
import ogya.workshop.performance_appraisal.repository.GroupAttitudeSkillRepo;
import ogya.workshop.performance_appraisal.service.GroupAttitudeSkillServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GroupAttitudeSkillServImpl implements GroupAttitudeSkillServ {

    @Autowired
    private GroupAttitudeSkillRepo groupAttitudeSkillRepo;

    // Create a new Group Achieve
    @Override
    public GroupAttitudeSkillDto createGroupAttitudeSkill(GroupAttitudeSkillDto groupAttitudeSkillDto) {
        GroupAttitudeSkill groupAttitudeSkill = convertToEntity(groupAttitudeSkillDto);
        groupAttitudeSkill.setCreatedAt(new Date());  // Set the creation date
        GroupAttitudeSkill savedGroupAttitudeSkill = groupAttitudeSkillRepo.save(groupAttitudeSkill);
        return convertToDto(savedGroupAttitudeSkill);
    }

    // Update an existing Achieve
    @Override
    public GroupAttitudeSkillDto updateGroupAttitudeSkill(UUID id, GroupAttitudeSkillDto groupAttitudeSkillDto) {
        if (!groupAttitudeSkillRepo.existsById(id)) {
            throw new IllegalArgumentException("Group Achievement with this ID does not exist.");
        }

        GroupAttitudeSkill groupAttitudeSkill = convertToEntity(groupAttitudeSkillDto);
        groupAttitudeSkill.setId(id);  // Use the ID from the URL path
        groupAttitudeSkill.setUpdatedAt(new Date());  // Set the updated date

        // Ensure 'createdAt' is set if it's null during the update
        if (groupAttitudeSkill.getCreatedAt() == null) {
            groupAttitudeSkill.setCreatedAt(new Date());  // Set current date if null
        }

        GroupAttitudeSkill updatedGroupAttitudeSkill = groupAttitudeSkillRepo.save(groupAttitudeSkill);
        return convertToDto(updatedGroupAttitudeSkill);
    }

    // Retrieve by ID
    @Override
    public Optional<GroupAttitudeSkillDto> getGroupAttitudeSkillById(UUID id) {
        Optional<GroupAttitudeSkill> groupAttitudeSkill = groupAttitudeSkillRepo.findById(id);
        return groupAttitudeSkill.map(this::convertToDto);
    }

    // Retrieve all Achievements
    @Override
    public List<GroupAttitudeSkillDto> getAllGroupAttitudeSkills() {
        List<GroupAttitudeSkill> groupAttitudeSkill = groupAttitudeSkillRepo.findAll();
        return groupAttitudeSkill.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Delete an Achieve by ID
    @Override
    public boolean deleteGroupAttitudeSkill(UUID id) {
        groupAttitudeSkillRepo.deleteById(id);
        return true;
    }

    // Helper method to convert Achieve entity to AchieveDto
    private GroupAttitudeSkillDto convertToDto(GroupAttitudeSkill groupAttitudeSkill) {
        GroupAttitudeSkillDto groupAttitudeSkillDto = new GroupAttitudeSkillDto();
        groupAttitudeSkillDto.setId(groupAttitudeSkill.getId());
        groupAttitudeSkillDto.setGroupName(groupAttitudeSkill.getGroupName());
        groupAttitudeSkillDto.setPercentage(groupAttitudeSkill.getPercentage());
        groupAttitudeSkillDto.setEnabled(groupAttitudeSkill.getEnabled());
        return groupAttitudeSkillDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private GroupAttitudeSkill convertToEntity(GroupAttitudeSkillDto groupAttitudeSkillDto) {
        GroupAttitudeSkill groupAttitudeSkill = new GroupAttitudeSkill();
        groupAttitudeSkill.setId(groupAttitudeSkillDto.getId());
        groupAttitudeSkill.setGroupName(groupAttitudeSkillDto.getGroupName());
        groupAttitudeSkill.setPercentage(groupAttitudeSkillDto.getPercentage());
        groupAttitudeSkill.setEnabled(groupAttitudeSkillDto.getEnabled());
        return groupAttitudeSkill;
    }
}
