package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.empachieveskill.EmpAchieveSkillDto;
import ogya.workshop.performance_appraisal.service.EmpAchieveSkillServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/emp-achievements-skill")
public class EmpAchieveSkillController {

    @Autowired
    private EmpAchieveSkillServ empAchieveSkillServ;

    // Create a new Achievement
    @PostMapping
    public ResponseEntity<EmpAchieveSkillDto> createEmpAchieveSkill(@RequestBody EmpAchieveSkillDto empAchieveSkillDto) {
        EmpAchieveSkillDto newEmpAchieveSkill = empAchieveSkillServ.createEmpAchieveSkill(empAchieveSkillDto);
        return ResponseEntity.ok(newEmpAchieveSkill);
    }

    // Update an existing Achievement
    @PutMapping("/{id}")
    public ResponseEntity<EmpAchieveSkillDto> updateEmpAchieveSkill(@PathVariable UUID id, @RequestBody EmpAchieveSkillDto empAchieveSkillDto) {
        try {
            EmpAchieveSkillDto updateEmpAchieveSkill = empAchieveSkillServ.updateEmpAchieveSkill(id, empAchieveSkillDto);
            return ResponseEntity.ok(updateEmpAchieveSkill);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Retrieve by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmpAchieveSkillDto> getEmpAchieveSkillById(@PathVariable UUID id) {
        Optional<EmpAchieveSkillDto> achievement = empAchieveSkillServ.getEmpAchieveSkillById(id);
        return achievement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve all Achievements
    @GetMapping
    public List<EmpAchieveSkillDto> getAllEmpAchieveSkill() {
        return empAchieveSkillServ.getAllEmpAchieveSkill();
    }

    // Delete an Achievement by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmpAchieveSkill(@PathVariable UUID id) {
        Boolean response = empAchieveSkillServ.deleteEmpAchieveSkill(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
