package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.AchieveDto;

import java.util.List;
import java.util.Optional;

public interface AchieveServ {
    AchieveDto createAchievement(AchieveDto achieveDto);
    AchieveDto updateAchievement(String id, AchieveDto achieveDto);
    Optional<AchieveDto> getAchievementById(String id);
    List<AchieveDto> getAllAchievements();
    void deleteAchievement(String id);
}
