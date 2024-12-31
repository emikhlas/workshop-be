package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillUpdateRequestDto;
import ogya.workshop.performance_appraisal.dto.empdevplan.EmpDevPlanDto;
import ogya.workshop.performance_appraisal.service.EmpAttitudeSkillServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/emp-attitude-skill")
public class EmpAttitudeSkillController {

    @Autowired
    private EmpAttitudeSkillServ empAttitudeSkillServ;

    @PostMapping
    public ResponseEntity<List<EmpAttitudeSkillDto>> createEmpAttitudeSkills(@RequestBody List<EmpAttitudeSkillCreateDto> empAttitudeSkillDtos) {
        List<EmpAttitudeSkillDto> newEmpAttitudeSkills = empAttitudeSkillServ.createEmpAttitudeSkills(empAttitudeSkillDtos);
        return ResponseEntity.ok(newEmpAttitudeSkills);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpAttitudeSkillDto> updateEmpAttitudeSkill(@PathVariable UUID id, @RequestBody EmpAttitudeSkillCreateDto empAttitudeSkillDto) {
        try {
            EmpAttitudeSkillDto updateEmpAttitudeSkill = empAttitudeSkillServ.updateEmpAttitudeSkill(id, empAttitudeSkillDto);
            return ResponseEntity.ok(updateEmpAttitudeSkill);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping
    public ResponseEntity<List<EmpAttitudeSkillDto>> updateEmpAttitudeSkills(@RequestBody List<EmpAttitudeSkillUpdateRequestDto> empAttitudeSkillUpdates) {
        try {
            // Ekstrak ID dan DTO dari request
            List<UUID> ids = empAttitudeSkillUpdates.stream()
                    .map(EmpAttitudeSkillUpdateRequestDto::getId)
                    .collect(Collectors.toList());

            List<EmpAttitudeSkillCreateDto> empAttitudeSkillDtos = empAttitudeSkillUpdates.stream()
                    .map(updateRequest -> new EmpAttitudeSkillCreateDto(
                            updateRequest.getUserId(),
                            updateRequest.getAttitudeSkillId(),
                            updateRequest.getScore(),
                            updateRequest.getAssessmentYear()
                    ))
                    .collect(Collectors.toList());

            List<EmpAttitudeSkillDto> updatedEmpAttitudeSkills = empAttitudeSkillServ.updateEmpAttitudeSkills(ids, empAttitudeSkillDtos);
            return ResponseEntity.ok(updatedEmpAttitudeSkills);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpAttitudeSkillDto> getEmpAttitudeSkillById(@PathVariable UUID id) {
        Optional<EmpAttitudeSkillDto> achievement = empAttitudeSkillServ.getEmpAttitudeSkillById(id);
        return achievement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<EmpAttitudeSkillDto> getAllEmpAttitudeSkills() {
        return empAttitudeSkillServ.getAllEmpAttitudeSkills();
    }

    @GetMapping("/user/{userId}/{year}")
    public ResponseEntity<List<EmpAttitudeSkillDto>> getEmpAttSkillByUserId(
            @PathVariable UUID userId,
            @PathVariable Integer year,
            @RequestParam(value = "enabledOnly", required = false, defaultValue = "false") boolean enabledOnly) {
        List<EmpAttitudeSkillDto> empAttitudeSkills = empAttitudeSkillServ.getEmpAttSkillByUserId(userId, year, enabledOnly);
        return ResponseEntity.ok(empAttitudeSkills);
    }

    @GetMapping("/assessment-years")
    public ResponseEntity<List<Integer>> getAllEmpAttitudeSkillYears() {
        List<Integer> years = empAttitudeSkillServ.getAllEmpAttitudeSkillYears();
        return ResponseEntity.ok(years);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmpAttitudeSkill(@PathVariable UUID id) {
        Boolean response = empAttitudeSkillServ.deleteEmpAttitudeSkill(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
