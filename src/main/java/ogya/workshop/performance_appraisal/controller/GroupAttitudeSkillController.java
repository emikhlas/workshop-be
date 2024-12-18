package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttWithAttDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillInfoWithCountDto;
import ogya.workshop.performance_appraisal.service.GroupAttitudeSkillServ;
import ogya.workshop.performance_appraisal.util.ServerResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/group-attitude-skill")
public class GroupAttitudeSkillController extends ServerResponseList {

    @Autowired
    private GroupAttitudeSkillServ groupAttitudeSkillServ;

    @PostMapping
    public ResponseEntity<ManagerDto<GroupAttitudeSkillDto>> createGroupAttitudeSkill(@RequestBody GroupAttitudeSkillCreateDto groupAttitudeSkillDto) {
        long startTime = System.currentTimeMillis();
        ManagerDto<GroupAttitudeSkillDto> response = new ManagerDto<>();

        GroupAttitudeSkillDto newGroupAttitudeSkill = groupAttitudeSkillServ.createGroupAttitudeSkill(groupAttitudeSkillDto);
        response.setContent(newGroupAttitudeSkill);

        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success create data", executionTime));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupAttitudeSkillDto> updateGroupAttitudeSkill(@PathVariable UUID id, @RequestBody GroupAttitudeSkillCreateDto groupAttitudeSkillDto) {
        try {
            GroupAttitudeSkillDto updateGroupAttitudeSkill = groupAttitudeSkillServ.updateGroupAttitudeSkill(id, groupAttitudeSkillDto);
            return ResponseEntity.ok(updateGroupAttitudeSkill);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerDto<GroupAttitudeSkillDto>> getGroupAttitudeSkillById(@PathVariable UUID id) {
        ManagerDto<GroupAttitudeSkillDto> response = new ManagerDto<>();
        Optional<GroupAttitudeSkillDto> achievement = groupAttitudeSkillServ.getGroupAttitudeSkillById(id);

        if (achievement.isPresent()) {
            response.setContent(achievement.get());
            response.setTotalRows(1);
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<GroupAttitudeSkillDto>>> getAllGroupAttitudeSkills(
            @RequestParam(value = "enabledOnly", required = false, defaultValue = "false") boolean enabledOnly) {
        long startTime = System.currentTimeMillis();
        ManagerDto<List<GroupAttitudeSkillDto>> response = new ManagerDto<>();

        List<GroupAttitudeSkillDto> content = groupAttitudeSkillServ.getAllGroupAttitudeSkills(enabledOnly);
        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all-with-att")
    public ResponseEntity<ManagerDto<List<GroupAttWithAttDto>>> getAllGroupAttitudeSkillsWithAtt(
            @RequestParam(value = "enabledOnly", required = false, defaultValue = "false") boolean enabledOnly) {
        long startTime = System.currentTimeMillis();
        ManagerDto<List<GroupAttWithAttDto>> response = new ManagerDto<>();
        List<GroupAttWithAttDto> content = groupAttitudeSkillServ.getAllGroupWithAttitudeSkills();
        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteGroupAttitudeSkill(@PathVariable UUID id) {
        long startTime = System.currentTimeMillis();
        ManagerDto<Boolean> response = new ManagerDto<>();

        Boolean content = groupAttitudeSkillServ.deleteGroupAttitudeSkill(id);
        response.setContent(content);

        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/group-attitude-skill/{id}")
    public ResponseEntity<ManagerDto<GroupAttWithAttDto>> getGroupWithAttitudeSkills(@PathVariable UUID id) {
        long startTime = System.currentTimeMillis();
        ManagerDto<GroupAttWithAttDto> response = new ManagerDto<>();

        GroupAttWithAttDto content = groupAttitudeSkillServ.getGroupWithAttitudeSkills(id);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all-with-att-skills")
    public ResponseEntity<ManagerDto<List<GroupAttWithAttDto>>> getAllGroupWithAttitudeSkills() {
        long startTime = System.currentTimeMillis();
        ManagerDto<List<GroupAttWithAttDto>> response = new ManagerDto<>();

        List<GroupAttWithAttDto> content = groupAttitudeSkillServ.getAllGroupWithAttitudeSkills();
        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<ManagerDto<List<GroupAttitudeSkillInfoWithCountDto>>> getGroupAttitudeSkillWithCount() {
        long startTime = System.currentTimeMillis();
        ManagerDto<List<GroupAttitudeSkillInfoWithCountDto>> response = new ManagerDto<>();

        List<GroupAttitudeSkillInfoWithCountDto> content = groupAttitudeSkillServ.getGroupAttitudeSkillWithCount();
        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
