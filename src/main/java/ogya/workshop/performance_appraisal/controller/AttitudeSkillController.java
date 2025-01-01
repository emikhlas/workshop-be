package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.achieve.AchieveWithGroupNameDto;
import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeWithGroupNameDto;
import ogya.workshop.performance_appraisal.service.AttitudeSkillServ;
import ogya.workshop.performance_appraisal.util.ServerResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/attitude-skill")
public class AttitudeSkillController extends ServerResponseList {

    @Autowired
    private AttitudeSkillServ attitudeSkillServ;

    @PostMapping
    public ResponseEntity<AttitudeSkillDto> createAttitudeSkill(@RequestBody AttitudeSkillCreateDto attitudeSkillDto) {
        AttitudeSkillDto newAttitudeSkill = attitudeSkillServ.createAttitudeSkill(attitudeSkillDto);
        return ResponseEntity.ok(newAttitudeSkill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttitudeSkillDto> updateAttitudeSkill(@PathVariable UUID id, @RequestBody AttitudeSkillCreateDto attitudeSkillDto) {
        try {
            AttitudeSkillDto updateAttitudeSkill = attitudeSkillServ.updateAttitudeSkill(id, attitudeSkillDto);
            return ResponseEntity.ok(updateAttitudeSkill);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttitudeSkillDto> getAttitudeSkillById(@PathVariable UUID id) {
        Optional<AttitudeSkillDto> achievement = attitudeSkillServ.getAttitudeSkillById(id);
        return achievement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<ManagerDto<List<AttitudeSkillDto>>> getAllAttitudeSkills(
            @RequestParam(value = "enabledOnly", required = false, defaultValue = "false") boolean enabledOnly) {
        long startTime = System.currentTimeMillis();
        ManagerDto<List<AttitudeSkillDto>> response = new ManagerDto<>();

        List<AttitudeSkillDto> content = attitudeSkillServ.getAllAttitudeSkills(enabledOnly);
        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAttitudeSkill(@PathVariable UUID id) {
        Boolean response = attitudeSkillServ.deleteAttitudeSkill(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/with-group-names")
    public ResponseEntity<List<AttitudeWithGroupNameDto>> getAllAttitudeWithGroupName() {
        List<AttitudeWithGroupNameDto> achievements = attitudeSkillServ.getAllAttitudeWithGroupName();
        return ResponseEntity.ok(achievements);
    }
}
