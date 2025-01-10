package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.achieve.AchieveCreateDto;
import ogya.workshop.performance_appraisal.dto.achieve.AchieveDto;
import ogya.workshop.performance_appraisal.dto.achieve.AchieveWithGroupNameDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AchieveServ {
    AchieveDto createAchievement(AchieveCreateDto achieveDto);

    AchieveDto updateAchievement(UUID id, AchieveCreateDto achieveDto);

    Optional<AchieveDto> getAchievementById(UUID id);

    List<AchieveDto> getAllAchievements();

    boolean deleteAchievement(UUID id);

    List<AchieveWithGroupNameDto> getAllAchievementsWithGroupNames();
}
