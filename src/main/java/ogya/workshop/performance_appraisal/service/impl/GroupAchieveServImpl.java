package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.groupachieve.GroupAchieveCreateDto;
import ogya.workshop.performance_appraisal.entity.GroupAchieve;
import ogya.workshop.performance_appraisal.dto.groupachieve.GroupAchieveDto;
import ogya.workshop.performance_appraisal.repository.GroupAchieveRepo;
import ogya.workshop.performance_appraisal.service.GroupAchieveServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GroupAchieveServImpl implements GroupAchieveServ {

    @Autowired
    private GroupAchieveRepo groupAchieveRepo;

    // Create a new Group Achieve
    @Override
    public GroupAchieveDto createGroupAchieve(GroupAchieveCreateDto groupAchieveDto) {
        GroupAchieve groupAchieve = convertToEntity(groupAchieveDto);
        groupAchieve.setCreatedAt(new Date());  // Set the creation date
        GroupAchieve savedGroupAchieve = groupAchieveRepo.save(groupAchieve);
        return convertToDto(savedGroupAchieve);
    }

    // Update an existing Achieve
    @Override
    public GroupAchieveDto updateGroupAchieve(UUID id, GroupAchieveCreateDto groupAchieveDto) {
        if (!groupAchieveRepo.existsById(id)) {
            throw new IllegalArgumentException("Group Achievement with this ID does not exist.");
        }

        GroupAchieve groupAchieve = convertToEntity(groupAchieveDto);
        groupAchieve.setId(id);  // Use the ID from the URL path
        groupAchieve.setUpdatedAt(new Date());  // Set the updated date

        // Ensure 'createdAt' is set if it's null during the update
        if (groupAchieve.getCreatedAt() == null) {
            groupAchieve.setCreatedAt(new Date());  // Set current date if null
        }

        GroupAchieve updatedGroupAchieve = groupAchieveRepo.save(groupAchieve);
        return convertToDto(updatedGroupAchieve);
    }

    // Retrieve by ID
    @Override
    public Optional<GroupAchieveDto> getGroupAchieveById(UUID id) {
        Optional<GroupAchieve> groupAchieve = groupAchieveRepo.findById(id);
        return groupAchieve.map(this::convertToDto);
    }

    // Retrieve all Achievements
    @Override
    public List<GroupAchieveDto> getAllGroupAchieve() {
        List<GroupAchieve> groupAchieves = groupAchieveRepo.findAll();
        return groupAchieves.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Delete an Achieve by ID
    @Override
    public boolean deleteGroupAchieve(UUID id) {
        groupAchieveRepo.deleteById(id);
        return true;
    }

    // Helper method to convert Achieve entity to AchieveDto
    private GroupAchieveDto convertToDto(GroupAchieve groupAchieve) {
        GroupAchieveDto groupAchieveDto = new GroupAchieveDto();
        groupAchieveDto.setId(groupAchieve.getId());
        groupAchieveDto.setGroupAchievementName(groupAchieve.getGroupAchievementName());
        groupAchieveDto.setPercentage(groupAchieve.getPercentage());
        groupAchieveDto.setEnabled(groupAchieve.getEnabled());
        groupAchieveDto.setCreatedAt(groupAchieve.getCreatedAt());
        groupAchieveDto.setCreatedBy(groupAchieve.getCreatedBy());
        groupAchieveDto.setUpdatedAt(groupAchieve.getUpdatedAt());
        groupAchieveDto.setUpdatedBy(groupAchieve.getUpdatedBy());
        return groupAchieveDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private GroupAchieve convertToEntity(GroupAchieveCreateDto groupAchieveDto) {
        GroupAchieve groupAchieve = new GroupAchieve();
        groupAchieve.setGroupAchievementName(groupAchieveDto.getGroupAchievementName());
        groupAchieve.setPercentage(groupAchieveDto.getPercentage());
        groupAchieve.setEnabled(groupAchieveDto.getEnabled());
        return groupAchieve;
    }
}
