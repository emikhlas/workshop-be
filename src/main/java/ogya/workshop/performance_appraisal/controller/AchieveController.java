package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.AchieveDto;
import ogya.workshop.performance_appraisal.service.AchieveServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/achievements")
public class AchieveController {

    @Autowired
    private AchieveServ achieveServ;

    // Create a new Achievement
    @PostMapping
    public ResponseEntity<AchieveDto> createAchievement(@RequestBody AchieveDto achieveDto) {
        AchieveDto newAchievement = achieveServ.createAchievement(achieveDto);
        return ResponseEntity.ok(newAchievement);
    }

    // Update an existing Achievement
    @PutMapping("/{id}")
    public ResponseEntity<AchieveDto> updateAchievement(@PathVariable String id, @RequestBody AchieveDto achieveDto) {
        try {
            AchieveDto updatedAchievement = achieveServ.updateAchievement(id, achieveDto);
            return ResponseEntity.ok(updatedAchievement);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Retrieve by ID
    @GetMapping("/{id}")
    public ResponseEntity<AchieveDto> getAchievementById(@PathVariable String id) {
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
    public ResponseEntity<Void> deleteAchievement(@PathVariable String id) {
        achieveServ.deleteAchievement(id);
        return ResponseEntity.noContent().build();
    }
}
