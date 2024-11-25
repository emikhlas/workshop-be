package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.achieve.AchieveCreateDto;
import ogya.workshop.performance_appraisal.dto.achieve.AchieveDto;
import ogya.workshop.performance_appraisal.dto.achieve.AchieveWithGroupNameDto;
import ogya.workshop.performance_appraisal.service.AchieveServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/achievements")
public class AchieveController {

    @Autowired
    private AchieveServ achieveServ;

    // Create a new Achievement
    @PostMapping
    public ResponseEntity<AchieveDto> createAchievement(@RequestBody AchieveCreateDto achieveDto) {
        AchieveDto newAchievement = achieveServ.createAchievement(achieveDto);
        return ResponseEntity.ok(newAchievement);
    }

    // Update an existing Achievement
    @PutMapping("/{id}")
    public ResponseEntity<AchieveDto> updateAchievement(@PathVariable UUID id, @RequestBody AchieveCreateDto achieveDto) {
        try {
            AchieveDto updatedAchievement = achieveServ.updateAchievement(id, achieveDto);
            return ResponseEntity.ok(updatedAchievement);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Retrieve by ID
    @GetMapping("/{id}")
    public ResponseEntity<AchieveDto> getAchievementById(@PathVariable UUID id) {
        Optional<AchieveDto> achievement = achieveServ.getAchievementById(id);
        return achievement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve all Achievements
    @GetMapping
    public List<AchieveDto> getAllAchievements() {
        return achieveServ.getAllAchievements();
    }

    // Delete an Achievement by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAchievement(@PathVariable UUID id) {
        Boolean response = achieveServ.deleteAchievement(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/with-group-names")
    public ResponseEntity<List<AchieveWithGroupNameDto>> getAllAchievementsWithGroupNames() {
        List<AchieveWithGroupNameDto> achievements = achieveServ.getAllAchievementsWithGroupNames();
        return ResponseEntity.ok(achievements);
    }
}
