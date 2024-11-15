package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.AttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.GroupAttitudeSkillDto;
import ogya.workshop.performance_appraisal.service.GroupAttitudeSkillServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/group-attitude-skill")
public class GroupAttitudeSkillController {

    @Autowired
    private GroupAttitudeSkillServ groupAttitudeSkillServ;

    // Create a new Achievement
    @PostMapping
    public ResponseEntity<GroupAttitudeSkillDto> createGroupAttitudeSkill(@RequestBody GroupAttitudeSkillDto groupAttitudeSkillDto) {
        GroupAttitudeSkillDto newGroupAttitudeSkill = groupAttitudeSkillServ.createGroupAttitudeSkill(groupAttitudeSkillDto);
        return ResponseEntity.ok(newGroupAttitudeSkill);
    }

    // Update an existing Achievement
    @PutMapping("/{id}")
    public ResponseEntity<GroupAttitudeSkillDto> updateGroupAttitudeSkill(@PathVariable UUID id, @RequestBody GroupAttitudeSkillDto groupAttitudeSkillDto) {
        try {
            GroupAttitudeSkillDto updateGroupAttitudeSkill = groupAttitudeSkillServ.updateGroupAttitudeSkill(id, groupAttitudeSkillDto);
            return ResponseEntity.ok(updateGroupAttitudeSkill);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Retrieve by ID
    @GetMapping("/{id}")
    public ResponseEntity<GroupAttitudeSkillDto> getGroupAttitudeSkillById(@PathVariable UUID id) {
        Optional<GroupAttitudeSkillDto> achievement = groupAttitudeSkillServ.getGroupAttitudeSkillById(id);
        return achievement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve all Achievements
    @GetMapping
    public List<GroupAttitudeSkillDto> getAllGroupAttitudeSkills() {
        return groupAttitudeSkillServ.getAllGroupAttitudeSkills();
    }

    // Delete an Achievement by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteGroupAttitudeSkill(@PathVariable UUID id) {
        Boolean response = groupAttitudeSkillServ.deleteGroupAttitudeSkill(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
