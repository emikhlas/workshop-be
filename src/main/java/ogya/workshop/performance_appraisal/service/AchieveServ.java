package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.AchieveDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AchieveServ {
    AchieveDto createAchievement(AchieveDto achieveDto);
    AchieveDto updateAchievement(UUID id, AchieveDto achieveDto);
    Optional<AchieveDto> getAchievementById(UUID id);
    List<AchieveDto> getAllAchievements();
    boolean deleteAchievement(UUID id);
}
