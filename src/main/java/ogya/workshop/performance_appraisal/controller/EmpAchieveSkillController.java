package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.empachieveskill.EmpAchieveSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.empachieveskill.EmpAchieveSkillDto;
import ogya.workshop.performance_appraisal.dto.empachieveskill.EmpAchieveSkillWithUserDto;
import ogya.workshop.performance_appraisal.service.EmpAchieveSkillServ;
import ogya.workshop.performance_appraisal.util.ServerResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/emp-achievements-skill")
public class EmpAchieveSkillController extends ServerResponseList {

    @Autowired
    private EmpAchieveSkillServ empAchieveSkillServ;

    @PostMapping
    public ResponseEntity<EmpAchieveSkillDto> createEmpAchieveSkill(@RequestBody EmpAchieveSkillCreateDto empAchieveSkillDto) {
        EmpAchieveSkillDto newEmpAchieveSkill = empAchieveSkillServ.createEmpAchieveSkill(empAchieveSkillDto);
        return ResponseEntity.ok(newEmpAchieveSkill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpAchieveSkillDto> updateEmpAchieveSkill(@PathVariable UUID id, @RequestBody EmpAchieveSkillCreateDto empAchieveSkillDto) {
        try {
            EmpAchieveSkillDto updateEmpAchieveSkill = empAchieveSkillServ.updateEmpAchieveSkill(id, empAchieveSkillDto);
            return ResponseEntity.ok(updateEmpAchieveSkill);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpAchieveSkillDto> getEmpAchieveSkillById(@PathVariable UUID id) {
        Optional<EmpAchieveSkillDto> achievement = empAchieveSkillServ.getEmpAchieveSkillById(id);
        return achievement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<ManagerDto<List<EmpAchieveSkillDto>>> getAllEmpAchieveSkill() {
        long startTime = System.currentTimeMillis();
        ManagerDto<List<EmpAchieveSkillDto>> response = new ManagerDto<>();
        List<EmpAchieveSkillDto> content = empAchieveSkillServ.getAllEmpAchieveSkill();
        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmpAchieveSkill(@PathVariable UUID id) {
        Boolean response = empAchieveSkillServ.deleteEmpAchieveSkill(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/with-group-names")
    public ResponseEntity<List<EmpAchieveSkillWithUserDto>> getAllEmpUserAchieve() {
        List<EmpAchieveSkillWithUserDto> empAchieves = empAchieveSkillServ.getAllEmpUserAchieve();
        return ResponseEntity.ok(empAchieves);
    }

    @GetMapping("/user/{userId}/{year}")
    public ResponseEntity<ManagerDto<List<EmpAchieveSkillDto>>> getAllEmpUserAchieveByUserId(
            @PathVariable("userId") UUID userId,
            @PathVariable("year") Integer year,
            @RequestParam(value = "enabledOnly", required = false, defaultValue = "false") boolean enabledOnly) {
        long startTime = System.currentTimeMillis();
        ManagerDto<List<EmpAchieveSkillDto>> response = new ManagerDto<>();
        List<EmpAchieveSkillDto> empAchieves = empAchieveSkillServ.getAllEmpUserAchieveByUserId(userId, year, enabledOnly);
        response.setContent(empAchieves);
        response.setTotalRows(empAchieves.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
