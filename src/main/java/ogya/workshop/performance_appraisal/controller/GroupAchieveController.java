package ogya.workshop.performance_appraisal.controller;


import ogya.workshop.performance_appraisal.dto.groupachieve.GroupAchieveCreateDto;
import ogya.workshop.performance_appraisal.dto.groupachieve.GroupAchieveDto;
import ogya.workshop.performance_appraisal.service.GroupAchieveServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/group-achievements")
public class GroupAchieveController {

    @Autowired
    private GroupAchieveServ groupAchieveServ;

    // Create a new Achievement
    @PostMapping
    public ResponseEntity<GroupAchieveDto> createGroupAchieve(@RequestBody GroupAchieveCreateDto groupAchieveDto) {
        GroupAchieveDto newGroupAchievement = groupAchieveServ.createGroupAchieve(groupAchieveDto);
        return ResponseEntity.ok(newGroupAchievement);
    }

    // Update an existing Achievement
    @PutMapping("/{id}")
    public ResponseEntity<GroupAchieveDto> updateGroupAchieve(@PathVariable UUID id, @RequestBody GroupAchieveCreateDto groupAchieveDto) {
        try {
            GroupAchieveDto updatedGroupAchieve = groupAchieveServ.updateGroupAchieve(id, groupAchieveDto);
            return ResponseEntity.ok(updatedGroupAchieve);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Retrieve by ID
    @GetMapping("/{id}")
    public ResponseEntity<GroupAchieveDto> getAchievementById(@PathVariable UUID id) {
        Optional<GroupAchieveDto> achievement = groupAchieveServ.getGroupAchieveById(id);
        return achievement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve all Achievements
    @GetMapping
    public List<GroupAchieveDto> getAllGroupAchieve() {
        return groupAchieveServ.getAllGroupAchieve();
    }

    // Delete an Achievement by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteGroupAchieve(@PathVariable UUID id) {
        Boolean response = groupAchieveServ.deleteGroupAchieve(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
