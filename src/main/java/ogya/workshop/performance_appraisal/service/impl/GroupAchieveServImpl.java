package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.groupachieve.GroupAchieveCreateDto;
import ogya.workshop.performance_appraisal.dto.groupachieve.GroupAchieveDto;
import ogya.workshop.performance_appraisal.dto.groupachieve.GroupAchieveInfoWithCountDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.Achieve;
import ogya.workshop.performance_appraisal.entity.GroupAchieve;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.AchieveRepo;
import ogya.workshop.performance_appraisal.repository.GroupAchieveRepo;
import ogya.workshop.performance_appraisal.service.GroupAchieveServ;
import ogya.workshop.performance_appraisal.service.SharedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupAchieveServImpl implements GroupAchieveServ {

    @Autowired
    private GroupAchieveRepo groupAchieveRepo;

    @Autowired
    private AchieveRepo achieveRepo;

    @Autowired
    private SharedService sharedService;

    @Override
    public GroupAchieveDto createGroupAchieve(GroupAchieveCreateDto groupAchieveDto) {
        GroupAchieve groupAchieve = convertToEntity(groupAchieveDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        groupAchieve.setCreatedBy(creator);

        groupAchieve.setCreatedAt(new Date());
        GroupAchieve savedGroupAchieve = groupAchieveRepo.save(groupAchieve);
        sharedService.updateAllAssessSums();
        return convertToDto(savedGroupAchieve);
    }

    @Override
    public GroupAchieveDto updateGroupAchieve(UUID id, GroupAchieveCreateDto groupAchieveDto) {
        GroupAchieve currentGroupAchieve = groupAchieveRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Group achieve with this ID does not exist."));

        if(groupAchieveDto.getGroupAchievementName() != null){
            currentGroupAchieve.setGroupAchievementName(groupAchieveDto.getGroupAchievementName());
        }

        if(groupAchieveDto.getPercentage() != null){
            currentGroupAchieve.setPercentage(groupAchieveDto.getPercentage());
        }

        if(groupAchieveDto.getEnabled() != null){
            List<Achieve> achieves = achieveRepo.findByGroupAchieve_Id(id);
            for(Achieve achieve : achieves){
                achieve.setEnabled(groupAchieveDto.getEnabled());
                achieveRepo.save(achieve);
            }
            currentGroupAchieve.setEnabled(groupAchieveDto.getEnabled());
        }

        currentGroupAchieve.setUpdatedAt(new Date());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        currentGroupAchieve.setUpdatedBy(creator);

        GroupAchieve updatedGroupAchieve = groupAchieveRepo.save(currentGroupAchieve);
        sharedService.updateAllAssessSums();
        return convertToDto(updatedGroupAchieve);
    }

    @Override
    public Optional<GroupAchieveDto> getGroupAchieveById(UUID id) {
        Optional<GroupAchieve> groupAchieve = groupAchieveRepo.findById(id);
        return groupAchieve.map(this::convertToDto);
    }

    @Override
    public List<GroupAchieveDto> getAllGroupAchieve() {
        List<GroupAchieve> groupAchieves = groupAchieveRepo.findAll();
        return groupAchieves.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public boolean deleteGroupAchieve(UUID id) {
        groupAchieveRepo.deleteById(id);
        sharedService.updateAllAssessSums();
        return true;
    }

    @Override
    public List<GroupAchieveInfoWithCountDto> getGroupAchieveInfoWithCount() {
        List<Map<String,Object>> result = groupAchieveRepo.getGroupAchieveWithCount();

        return result.stream()
                .map(map -> new GroupAchieveInfoWithCountDto(
                        UUID.nameUUIDFromBytes((byte[]) map.get("id")),
                        (String) map.get("group_achievement_name"),
                        (Integer) map.get("percentage"),
                        (Integer) map.get("enabled"),
                        Math.toIntExact((Long) map.get("count"))
                ))
                .collect(Collectors.toList());
    }

    private GroupAchieveDto convertToDto(GroupAchieve groupAchieve) {
        GroupAchieveDto groupAchieveDto = new GroupAchieveDto();
        groupAchieveDto.setId(groupAchieve.getId());
        groupAchieveDto.setGroupAchievementName(groupAchieve.getGroupAchievementName());
        groupAchieveDto.setPercentage(groupAchieve.getPercentage());
        groupAchieveDto.setEnabled(groupAchieve.getEnabled());
        groupAchieveDto.setCreatedAt(groupAchieve.getCreatedAt());
        if(groupAchieve.getCreatedBy() != null){
            groupAchieveDto.setCreatedBy(UserInfoDto.fromEntity(groupAchieve.getCreatedBy()));
        }
        groupAchieveDto.setUpdatedAt(groupAchieve.getUpdatedAt());
        if(groupAchieve.getUpdatedBy() != null){
            groupAchieveDto.setUpdatedBy(UserInfoDto.fromEntity(groupAchieve.getUpdatedBy()));
        }
        return groupAchieveDto;
    }

    private GroupAchieve convertToEntity(GroupAchieveCreateDto groupAchieveDto) {
        GroupAchieve groupAchieve = new GroupAchieve();
        groupAchieve.setGroupAchievementName(groupAchieveDto.getGroupAchievementName());
        groupAchieve.setPercentage(groupAchieveDto.getPercentage());
        groupAchieve.setEnabled(groupAchieveDto.getEnabled());
        return groupAchieve;
    }
}
