package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttWithAttDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillInfoWithCountDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.AttitudeSkill;
import ogya.workshop.performance_appraisal.entity.GroupAttitudeSkill;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.AttitudeSkillRepo;
import ogya.workshop.performance_appraisal.repository.GroupAttitudeSkillRepo;
import ogya.workshop.performance_appraisal.service.GroupAttitudeSkillServ;
import ogya.workshop.performance_appraisal.service.SharedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupAttitudeSkillServImpl implements GroupAttitudeSkillServ {

    @Autowired
    private GroupAttitudeSkillRepo groupAttitudeSkillRepo;

    @Autowired
    private AttitudeSkillRepo attitudeSkillRepo;

    @Autowired
    private SharedService sharedService;

    @Override
    public GroupAttitudeSkillDto createGroupAttitudeSkill(GroupAttitudeSkillCreateDto groupAttitudeSkillDto) {
        GroupAttitudeSkill groupAttitudeSkill = convertToEntity(groupAttitudeSkillDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();
        groupAttitudeSkill.setCreatedBy(creator);
        groupAttitudeSkill.setCreatedAt(new Date());

        GroupAttitudeSkill savedGroupAttitudeSkill = groupAttitudeSkillRepo.save(groupAttitudeSkill);
        sharedService.updateAllAssessSums();
        return convertToDto(savedGroupAttitudeSkill);
    }

    @Override
    public GroupAttitudeSkillDto updateGroupAttitudeSkill(UUID id, GroupAttitudeSkillCreateDto groupAttitudeSkillDto) {
        GroupAttitudeSkill currentGroupAttitudeSkill = groupAttitudeSkillRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Group Attitude Skill with this ID does not exist."));

        if(groupAttitudeSkillDto.getGroupName() != null){
            currentGroupAttitudeSkill.setGroupName(groupAttitudeSkillDto.getGroupName());
        }
        if(groupAttitudeSkillDto.getPercentage() != null){
            currentGroupAttitudeSkill.setPercentage(groupAttitudeSkillDto.getPercentage());
        }

        List<AttitudeSkill> attitudeSkills = attitudeSkillRepo.findByGroupAttitudeSkill_Id(id);
        if(groupAttitudeSkillDto.getEnabled() != null){
            currentGroupAttitudeSkill.setEnabled(groupAttitudeSkillDto.getEnabled());
            for (AttitudeSkill attitudeSkill : attitudeSkills) {
                attitudeSkill.setEnabled(groupAttitudeSkillDto.getEnabled());
                attitudeSkillRepo.save(attitudeSkill);
            }
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User updater = authUser.getUser();
        currentGroupAttitudeSkill.setUpdatedBy(updater);
        currentGroupAttitudeSkill.setUpdatedAt(new Date());


        GroupAttitudeSkill updatedGroupAttitudeSkill = groupAttitudeSkillRepo.save(currentGroupAttitudeSkill);

        sharedService.updateAllAssessSums();
        return convertToDto(updatedGroupAttitudeSkill);
    }

    @Override
    public Optional<GroupAttitudeSkillDto> getGroupAttitudeSkillById(UUID id) {
        Optional<GroupAttitudeSkill> groupAttitudeSkill = groupAttitudeSkillRepo.findById(id);
        return groupAttitudeSkill.map(this::convertToDto);
    }

    @Override
    public List<GroupAttitudeSkillDto> getAllGroupAttitudeSkills(boolean enabledOnly) {
        if(enabledOnly){
            return groupAttitudeSkillRepo.findAllByEnabled(1).stream().map(this::convertToDto).collect(Collectors.toList());
        }else{
            return groupAttitudeSkillRepo.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        }
    }

    @Override
    public boolean deleteGroupAttitudeSkill(UUID id) {
        groupAttitudeSkillRepo.deleteById(id);
        sharedService.updateAllAssessSums();
        return true;
    }

    @Override
    public GroupAttWithAttDto getGroupWithAttitudeSkills(UUID id) {
        GroupAttitudeSkill groupAttitudeSkill = groupAttitudeSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return GroupAttWithAttDto.fromEntity(groupAttitudeSkill);
    }

    @Override
    public List<GroupAttWithAttDto> getAllGroupWithAttitudeSkills() {
        List<GroupAttitudeSkill> groupAttitudeSkills = groupAttitudeSkillRepo.findAll();
        return groupAttitudeSkills.stream()
                .map(GroupAttWithAttDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupAttitudeSkillInfoWithCountDto> getGroupAttitudeSkillWithCount() {

        List<Map<String, Object>> result = groupAttitudeSkillRepo.getGroupAttitudeSkillWithCount();

        return result.stream()
                .map(map -> new GroupAttitudeSkillInfoWithCountDto(
                        UUID.nameUUIDFromBytes((byte[]) map.get("id")),
                        (String) map.get("group_name"),
                        (Integer) map.get("percentage"),
                        (Integer) map.get("enabled"),
                        Math.toIntExact((Long) map.get("count"))
                ))
                .collect(Collectors.toList());
    }

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

    private GroupAttitudeSkill convertToEntity(GroupAttitudeSkillCreateDto groupAttitudeSkillDto) {
        GroupAttitudeSkill groupAttitudeSkill = new GroupAttitudeSkill();
        groupAttitudeSkill.setGroupName(groupAttitudeSkillDto.getGroupName());
        groupAttitudeSkill.setPercentage(groupAttitudeSkillDto.getPercentage());
        groupAttitudeSkill.setEnabled(groupAttitudeSkillDto.getEnabled());
        return groupAttitudeSkill;
    }
}
