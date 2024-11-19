package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeSkillDto;
import ogya.workshop.performance_appraisal.service.AttitudeSkillServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/attitude-skill")
public class AttitudeSkillController {

    @Autowired
    private AttitudeSkillServ attitudeSkillServ;

    // Create a new Achievement
    @PostMapping
    public ResponseEntity<AttitudeSkillDto> createAttitudeSkill(@RequestBody AttitudeSkillCreateDto attitudeSkillDto) {
        AttitudeSkillDto newAttitudeSkill = attitudeSkillServ.createAttitudeSkill(attitudeSkillDto);
        return ResponseEntity.ok(newAttitudeSkill);
    }

    // Update an existing Achievement
    @PutMapping("/{id}")
    public ResponseEntity<AttitudeSkillDto> updateAttitudeSkill(@PathVariable UUID id, @RequestBody AttitudeSkillCreateDto attitudeSkillDto) {
        try {
            AttitudeSkillDto updateAttitudeSkill = attitudeSkillServ.updateAttitudeSkill(id, attitudeSkillDto);
            return ResponseEntity.ok(updateAttitudeSkill);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Retrieve by ID
    @GetMapping("/{id}")
    public ResponseEntity<AttitudeSkillDto> getAttitudeSkillById(@PathVariable UUID id) {
        Optional<AttitudeSkillDto> achievement = attitudeSkillServ.getAttitudeSkillById(id);
        return achievement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve all Achievements
    @GetMapping
    public List<AttitudeSkillDto> getAllAttitudeSkills() {
        return attitudeSkillServ.getAllAttitudeSkills();
    }

    // Delete an Achievement by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAttitudeSkill(@PathVariable UUID id) {
        Boolean response = attitudeSkillServ.deleteAttitudeSkill(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
