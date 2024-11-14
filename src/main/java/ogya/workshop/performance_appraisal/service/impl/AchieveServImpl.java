package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.AchieveDto;
import ogya.workshop.performance_appraisal.entity.Achieve;
import ogya.workshop.performance_appraisal.repository.AchieveRepo;
import ogya.workshop.performance_appraisal.service.AchieveServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AchieveServImpl implements AchieveServ {

    @Autowired
    private AchieveRepo achieveRepo;

    // Create a new Achieve
    @Override
    public AchieveDto createAchievement(AchieveDto achieveDto) {
        Achieve achieve = convertToEntity(achieveDto);
        achieve.setCreatedAt(new Date());  // Set the creation date
        Achieve savedAchieve = achieveRepo.save(achieve);
        return convertToDto(savedAchieve);
    }

    // Update an existing Achieve
    public AchieveDto updateAchievement(String id, AchieveDto achieveDto) {
        if (!achieveRepo.existsById(id)) {
            throw new IllegalArgumentException("Achievement with this ID does not exist.");
        }

        Achieve achieve = convertToEntity(achieveDto);
        achieve.setId(id);  // Use the ID from the URL path
        achieve.setUpdatedAt(new Date());  // Set the updated date

        // Ensure 'createdAt' is set if it's null during the update
        if (achieve.getCreatedAt() == null) {
            achieve.setCreatedAt(new Date());  // Set current date if null
        }

        Achieve updatedAchieve = achieveRepo.save(achieve);
        return convertToDto(updatedAchieve);
    }

    // Retrieve by ID
    @Override
    public Optional<AchieveDto> getAchievementById(String id) {
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
    public void deleteAchievement(String id) {
        achieveRepo.deleteById(id);
    }

    // Helper method to convert Achieve entity to AchieveDto
    private AchieveDto convertToDto(Achieve achieve) {
        AchieveDto achieveDto = new AchieveDto();
        achieveDto.setId(achieve.getId());
        achieveDto.setAchievementName(achieve.getAchievementName());
        achieveDto.setGroupAchievementId(achieve.getGroupAchievementId());
        achieveDto.setEnabled(achieve.getEnabled() != null ? achieve.getEnabled().toString() : null);
        return achieveDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private Achieve convertToEntity(AchieveDto achieveDto) {
        Achieve achieve = new Achieve();
        achieve.setId(achieveDto.getId());
        achieve.setAchievementName(achieveDto.getAchievementName());
        achieve.setGroupAchievementId(achieveDto.getGroupAchievementId());
        achieve.setEnabled(achieveDto.getEnabled() != null ? Integer.parseInt(achieveDto.getEnabled()) : 1);
        return achieve;
    }
}
