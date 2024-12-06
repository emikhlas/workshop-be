package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeWithGroupNameDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttWithAttDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillInfoWithCountDto;
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
    public ResponseEntity<GroupAttitudeSkillDto> createGroupAttitudeSkill(@RequestBody GroupAttitudeSkillCreateDto groupAttitudeSkillDto) {
        GroupAttitudeSkillDto newGroupAttitudeSkill = groupAttitudeSkillServ.createGroupAttitudeSkill(groupAttitudeSkillDto);
        return ResponseEntity.ok(newGroupAttitudeSkill);
    }

    // Update an existing Achievement
    @PutMapping("/{id}")
    public ResponseEntity<GroupAttitudeSkillDto> updateGroupAttitudeSkill(@PathVariable UUID id, @RequestBody GroupAttitudeSkillCreateDto groupAttitudeSkillDto) {
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

    @GetMapping("/group-attitude-skill/{id}")
    public GroupAttWithAttDto getGroupWithAttitudeSkills(@PathVariable UUID id) {
        return groupAttitudeSkillServ.getGroupWithAttitudeSkills(id);
    }

    @GetMapping("/all")
    public List<GroupAttWithAttDto> getAllGroupWithAttitudeSkills() {
        return groupAttitudeSkillServ.getAllGroupWithAttitudeSkills();
    }

    @GetMapping("/count")
    public List<GroupAttitudeSkillInfoWithCountDto> getGroupAttitudeSkillWithCount() {
        return groupAttitudeSkillServ.getGroupAttitudeSkillWithCount();
    }
}
