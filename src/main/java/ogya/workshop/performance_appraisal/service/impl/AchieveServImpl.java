package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.achieve.AchieveCreateDto;
import ogya.workshop.performance_appraisal.dto.achieve.AchieveDto;

import ogya.workshop.performance_appraisal.dto.achieve.AchieveWithGroupNameDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;

import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;

import ogya.workshop.performance_appraisal.entity.*;
import ogya.workshop.performance_appraisal.repository.AchieveRepo;
import ogya.workshop.performance_appraisal.repository.GroupAchieveRepo;
import ogya.workshop.performance_appraisal.service.AchieveServ;
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
public class AchieveServImpl implements AchieveServ {

    @Autowired
    private AchieveRepo achieveRepo;

    @Autowired
    private GroupAchieveRepo groupAchieveRepo;

    // Create a new Achieve
    @Override
    public AchieveDto createAchievement(AchieveCreateDto achieveDto) {
        Achieve achieve = convertToEntity(achieveDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        achieve.setCreatedBy(creator);
        achieve.setCreatedAt(new Date());  // Set the creation date
        Achieve savedAchieve = achieveRepo.save(achieve);
        return convertToDto(savedAchieve);
    }

    // Update an existing Achieve
    @Override
    public AchieveDto updateAchievement(UUID id, AchieveCreateDto achieveDto) {
        Achieve currentAchieve = achieveRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Achieve with this ID does not exist."));
//        if (!achieveRepo.existsById(id)) {
//            throw new IllegalArgumentException("Achievement with this ID does not exist.");
//        }
        if(achieveDto.getAchievementName() != null){
            currentAchieve.setAchievementName(achieveDto.getAchievementName());
        }

        if(achieveDto.getGroupAchievementId() != null){

            GroupAchieve groupAchieve = groupAchieveRepo.findById(achieveDto.getGroupAchievementId()).orElseThrow(() -> new IllegalArgumentException("Group Attitude Skill with this ID does not exist."));
            currentAchieve.setGroupAchieve(groupAchieve);
        }
//        Achieve achieve = convertToEntity(achieveDto);
//        achieve.setId(id);  // Use the ID from the URL path
        currentAchieve.setUpdatedAt(new Date());  // Set the updated date

        // Ensure 'createdAt' is set if it's null during the update
//        if (achieve.getCreatedAt() == null) {
//            achieve.setCreatedAt(new Date());  // Set current date if null
//        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        currentAchieve.setUpdatedBy(creator);

        Achieve updatedAchieve = achieveRepo.save(currentAchieve);
        return convertToDto(updatedAchieve);
    }

    // Retrieve by ID
    @Override
    public Optional<AchieveDto> getAchievementById(UUID id) {
        Optional<Achieve> achieve = achieveRepo.findById(id);
        return achieve.map(this::convertToDto);
    }

    // Retrieve all Achievements
    @Override
    public List<AchieveDto> getAllAchievements() {
        List<Achieve> achieves = achieveRepo.findAll();
        return achieves.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Delete an Achieve by ID
    @Override
    public boolean deleteAchievement(UUID id) {
        achieveRepo.deleteById(id);
        return true;
    }

    @Override
    public List<AchieveWithGroupNameDto> getAllAchievementsWithGroupNames() {
        return achieveRepo.findAllWithGroupNames();
    }

    // Helper method to convert Achieve entity to AchieveDto
    private AchieveDto convertToDto(Achieve achieve) {
        AchieveDto achieveDto = new AchieveDto();
        achieveDto.setId(achieve.getId());
        achieveDto.setAchievementName(achieve.getAchievementName());
        if (achieve.getGroupAchieve() != null) {
            achieveDto.setGroupAchievementId(achieve.getGroupAchieve().getId());
        }
        achieveDto.setEnabled(achieve.getEnabled());
        achieveDto.setCreatedAt(achieve.getCreatedAt());

        if(achieve.getCreatedAt() != null) {
            achieveDto.setCreatedBy(UserInfoDto.fromEntity(achieve.getCreatedBy()));
        }
        if (achieve.getCreatedBy() != null) {
            achieveDto.setCreatedBy(UserInfoDto.fromEntity(achieve.getCreatedBy()));

        }
        achieveDto.setUpdatedAt(achieve.getUpdatedAt());
        if(achieve.getUpdatedBy() != null){
            achieveDto.setUpdatedBy(UserInfoDto.fromEntity(achieve.getUpdatedBy()));
        }
        return achieveDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private Achieve convertToEntity(AchieveCreateDto achieveDto) {
        Achieve achieve = new Achieve();
        achieve.setAchievementName(achieveDto.getAchievementName());
        if (achieveDto.getGroupAchievementId() != null) {
            GroupAchieve groupAchieve = new GroupAchieve();
            groupAchieve.setId(achieveDto.getGroupAchievementId());
            achieve.setGroupAchieve(groupAchieve);
        }
        achieve.setEnabled(achieveDto.getEnabled());
        return achieve;
    }
}
