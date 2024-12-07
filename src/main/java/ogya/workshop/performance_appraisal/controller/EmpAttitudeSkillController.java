package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.empdevplan.EmpDevPlanDto;
import ogya.workshop.performance_appraisal.service.EmpAttitudeSkillServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/emp-attitude-skill")
public class EmpAttitudeSkillController {

    @Autowired
    private EmpAttitudeSkillServ empAttitudeSkillServ;

    // Create a new Achievement
//    @PostMapping
//    public ResponseEntity<EmpAttitudeSkillDto> createEmpAttitudeSkill(@RequestBody EmpAttitudeSkillCreateDto empAttitudeSkillDto) {
//        EmpAttitudeSkillDto newEmpAttitudeSkill = empAttitudeSkillServ.createEmpAttitudeSkill(empAttitudeSkillDto);
//        return ResponseEntity.ok(newEmpAttitudeSkill);
//    }

    // Create new EmpAttitudeSkills
    @PostMapping
    public ResponseEntity<List<EmpAttitudeSkillDto>> createEmpAttitudeSkills(@RequestBody List<EmpAttitudeSkillCreateDto> empAttitudeSkillDtos) {
        List<EmpAttitudeSkillDto> newEmpAttitudeSkills = empAttitudeSkillServ.createEmpAttitudeSkills(empAttitudeSkillDtos);
        return ResponseEntity.ok(newEmpAttitudeSkills);
    }



    // Update an existing Achievement
    @PutMapping("/{id}")
    public ResponseEntity<EmpAttitudeSkillDto> updateEmpAttitudeSkill(@PathVariable UUID id, @RequestBody EmpAttitudeSkillCreateDto empAttitudeSkillDto) {
        try {
            EmpAttitudeSkillDto updateEmpAttitudeSkill = empAttitudeSkillServ.updateEmpAttitudeSkill(id, empAttitudeSkillDto);
            return ResponseEntity.ok(updateEmpAttitudeSkill);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Retrieve by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmpAttitudeSkillDto> getEmpAttitudeSkillById(@PathVariable UUID id) {
        Optional<EmpAttitudeSkillDto> achievement = empAttitudeSkillServ.getEmpAttitudeSkillById(id);
        return achievement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve all Achievements
    @GetMapping
    public List<EmpAttitudeSkillDto> getAllEmpAttitudeSkills() {
        return empAttitudeSkillServ.getAllEmpAttitudeSkills();
    }



    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EmpAttitudeSkillDto>> getEmpAttSkillByUserId(@PathVariable UUID userId){
        List<EmpAttitudeSkillDto> empAttitudeSkills = empAttitudeSkillServ.getEmpAttSkillByUserId(userId);
        return ResponseEntity.ok(empAttitudeSkills);
    }

    // Delete an Achievement by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmpAttitudeSkill(@PathVariable UUID id) {
        Boolean response = empAttitudeSkillServ.deleteEmpAttitudeSkill(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
