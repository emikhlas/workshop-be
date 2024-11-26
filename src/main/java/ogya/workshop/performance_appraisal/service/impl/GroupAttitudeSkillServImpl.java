package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.GroupAttitudeSkill;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.GroupAttitudeSkillRepo;
import ogya.workshop.performance_appraisal.service.GroupAttitudeSkillServ;
import ogya.workshop.performance_appraisal.service.UserServ;
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
public class GroupAttitudeSkillServImpl implements GroupAttitudeSkillServ {

    @Autowired
    private GroupAttitudeSkillRepo groupAttitudeSkillRepo;

//     Create a new Group Achieve
    @Override
    public GroupAttitudeSkillDto createGroupAttitudeSkill(GroupAttitudeSkillCreateDto groupAttitudeSkillDto) {
        GroupAttitudeSkill groupAttitudeSkill = convertToEntity(groupAttitudeSkillDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();
        groupAttitudeSkill.setCreatedBy(creator);
        groupAttitudeSkill.setCreatedAt(new Date());  // Set the creation date

        GroupAttitudeSkill savedGroupAttitudeSkill = groupAttitudeSkillRepo.save(groupAttitudeSkill);
        return convertToDto(savedGroupAttitudeSkill);
    }

    // Update an existing Achieve
    @Override
    public GroupAttitudeSkillDto updateGroupAttitudeSkill(UUID id, GroupAttitudeSkillCreateDto groupAttitudeSkillDto) {
        GroupAttitudeSkill currentGroupAttitudeSkill = groupAttitudeSkillRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Group Attitude Skill with this ID does not exist."));

        if(groupAttitudeSkillDto.getGroupName() != null){
            currentGroupAttitudeSkill.setGroupName(groupAttitudeSkillDto.getGroupName());
        }
        if(groupAttitudeSkillDto.getPercentage() != null){
            currentGroupAttitudeSkill.setPercentage(groupAttitudeSkillDto.getPercentage());
        }
        if(groupAttitudeSkillDto.getEnabled() != null){
            currentGroupAttitudeSkill.setEnabled(groupAttitudeSkillDto.getEnabled());
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User updater = authUser.getUser();
        currentGroupAttitudeSkill.setUpdatedBy(updater);
        currentGroupAttitudeSkill.setUpdatedAt(new Date());


        GroupAttitudeSkill updatedGroupAttitudeSkill = groupAttitudeSkillRepo.save(currentGroupAttitudeSkill);
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
        groupAttitudeSkillDto.setCreatedAt(groupAttitudeSkill.getCreatedAt());
        groupAttitudeSkillDto.setUpdatedAt(groupAttitudeSkill.getUpdatedAt());
        if(groupAttitudeSkill.getCreatedBy() != null){
            groupAttitudeSkillDto.setCreatedBy(UserInfoDto.fromEntity(groupAttitudeSkill.getCreatedBy()));
        }
        if(groupAttitudeSkill.getUpdatedBy() != null){
            groupAttitudeSkillDto.setUpdatedBy(UserInfoDto.fromEntity(groupAttitudeSkill.getUpdatedBy()));
        }
        return groupAttitudeSkillDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private GroupAttitudeSkill convertToEntity(GroupAttitudeSkillCreateDto groupAttitudeSkillDto) {
        GroupAttitudeSkill groupAttitudeSkill = new GroupAttitudeSkill();
        groupAttitudeSkill.setGroupName(groupAttitudeSkillDto.getGroupName());
        groupAttitudeSkill.setPercentage(groupAttitudeSkillDto.getPercentage());
        groupAttitudeSkill.setEnabled(groupAttitudeSkillDto.getEnabled());
        return groupAttitudeSkill;
    }
}
