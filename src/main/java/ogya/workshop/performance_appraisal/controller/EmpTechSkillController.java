package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.emptechskill.EmpTechSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.emptechskill.EmpTechSkillDto;
import ogya.workshop.performance_appraisal.dto.emptechskill.EmpTechSkillUserDto;
import ogya.workshop.performance_appraisal.service.EmpTechSkillServ;
import ogya.workshop.performance_appraisal.util.ServerResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/emp-tech-skill")
public class EmpTechSkillController extends ServerResponseList {

    private final Logger Log = LoggerFactory.getLogger(EmpTechSkillController.class);

    @Autowired
    private EmpTechSkillServ empTechSkillServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<EmpTechSkillDto>>>  getAllEmpTechSkill() {
        Log.info("Start getAllEmpTechSkill in EmpTechSkillController");

        Long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpTechSkillDto>> result = new ManagerDto<>();
        List<EmpTechSkillDto> content = empTechSkillServ.findAll();
        result.setContent(content);
        result.setTotalRows(content.size());

        Long endTime = System.currentTimeMillis();
        result.setInfo(getInfoOk("Success get data", endTime - startTime));
        Log.info("End getAllEmpTechSkill in EmpTechSkillController");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping("/detail/{user-id}")
//    public ResponseEntity<ManagerDto<List<EmpTechSkillDto>>>  getAllEmpTechSkillByUserId(@PathVariable("user-id") UUID userId) {
//        Log.info("Start getAllEmpTechSkillByUserId in EmpTechSkillController");
//
//        Long startTime = System.currentTimeMillis();
//
//        ManagerDto<List<EmpTechSkillDto>> result = new ManagerDto<>();
//        List<EmpTechSkillDto> content = empTechSkillServ.findAllByEmpId(userId);
//        result.setContent(content);
//        result.setTotalRows(content.size());
//
//        Long endTime = System.currentTimeMillis();
//        result.setInfo(getInfoOk("Success get data", endTime - startTime));
//        Log.info("End getAllEmpTechSkillByUserId in EmpTechSkillController");
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EmpTechSkillUserDto>> getAllUserEmpTech(@PathVariable UUID userId){
        List<EmpTechSkillUserDto> empTechSkills = empTechSkillServ.findByUserId(userId);
        return ResponseEntity.ok(empTechSkills);
    }

//    @PostMapping("/save")
//    public ResponseEntity<ManagerDto<EmpTechSkillDto>> save(@RequestBody EmpTechSkillCreateDto empTechSkillDto) {
//        Log.info("Start save in EmpTechSkillController");
//
//        Long startTime = System.currentTimeMillis();
//
//        ManagerDto<EmpTechSkillDto> result = new ManagerDto<>();
//        EmpTechSkillDto content = empTechSkillServ.save(empTechSkillDto);
//        result.setContent(content);
//        result.setTotalRows(1);
//
//        Long endTime = System.currentTimeMillis();
//        result.setInfo(getInfoOk("Success save data", endTime - startTime));
//        Log.info("End save in EmpTechSkillController");
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @PostMapping("/save")
    public ResponseEntity<ManagerDto<List<EmpTechSkillDto>>> save(@RequestBody List<EmpTechSkillCreateDto> empTechSkillDtos) {
        Log.info("Start bulk save in EmpTechSkillController");

        Long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpTechSkillDto>> result = new ManagerDto<>();
        List<EmpTechSkillDto> content = empTechSkillServ.save(empTechSkillDtos);
        result.setContent(content);
        result.setTotalRows(content.size());

        Long endTime = System.currentTimeMillis();
        result.setInfo(getInfoOk("Success save data", endTime - startTime));
        Log.info("End bulk save in EmpTechSkillController");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }





    @PutMapping("/update/{id}")
    public ResponseEntity<ManagerDto<EmpTechSkillDto>> update(@PathVariable("id") UUID id,@RequestBody EmpTechSkillCreateDto empTechSkillDto) {
        Log.info("Start update in EmpTechSkillController");

        Long startTime = System.currentTimeMillis();

        ManagerDto<EmpTechSkillDto> result = new ManagerDto<>();
        EmpTechSkillDto content = empTechSkillServ.update(id,empTechSkillDto);
        result.setContent(content);
        result.setTotalRows(1);

        Long endTime = System.currentTimeMillis();
        result.setInfo(getInfoOk("Success update data", endTime - startTime));
        Log.info("End update in EmpTechSkillController");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-user-and-year")
    public ResponseEntity<List<EmpTechSkillDto>> getEmpTechSkillByUserIdAndYear(
            @RequestParam UUID userId,
            @RequestParam Integer assessmentYear) {
        List<EmpTechSkillDto> empTechSkills = empTechSkillServ.findByUserIdAndAssessmentYear(userId, assessmentYear);
        return ResponseEntity.ok(empTechSkills);
    }

    @GetMapping("/assessment-years")
    public List<Integer> getAllAssessmentYears() {
        Log.info("Received request to get all distinct assessment years");
        List<Integer> assessmentYears = empTechSkillServ.getAllEmpTechSkillYears();
        Log.info("Returning assessment years: {}", assessmentYears);
        return assessmentYears;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> delete(@PathVariable("id") UUID id) {
        Log.info("Start delete in EmpTechSkillController");

        Long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> result = new ManagerDto<>();
        Boolean content = empTechSkillServ.deleteById(id);
        result.setContent(content);
        result.setTotalRows(1);

        Long endTime = System.currentTimeMillis();
        result.setInfo(getInfoOk("Success delete data", endTime - startTime));
        Log.info("End delete in EmpTechSkillController");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
