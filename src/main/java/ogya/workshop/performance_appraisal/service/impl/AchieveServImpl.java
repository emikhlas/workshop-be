package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.achieve.AchieveDto;
import ogya.workshop.performance_appraisal.entity.Achieve;
import ogya.workshop.performance_appraisal.entity.GroupAchieve;
import ogya.workshop.performance_appraisal.repository.AchieveRepo;
import ogya.workshop.performance_appraisal.service.AchieveServ;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Create a new Achieve
    @Override
    public AchieveDto createAchievement(AchieveDto achieveDto) {
        Achieve achieve = convertToEntity(achieveDto);
        achieve.setCreatedAt(new Date());  // Set the creation date
        Achieve savedAchieve = achieveRepo.save(achieve);
        return convertToDto(savedAchieve);
    }

    // Update an existing Achieve
    @Override
    public AchieveDto updateAchievement(UUID id, AchieveDto achieveDto) {
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
        achieveDto.setCreatedBy(achieve.getCreatedBy());
        achieveDto.setUpdatedAt(achieve.getUpdatedAt());
        achieveDto.setUpdatedBy(achieve.getUpdatedBy());
        return achieveDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private Achieve convertToEntity(AchieveDto achieveDto) {
        Achieve achieve = new Achieve();
        achieve.setId(achieveDto.getId());
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
