package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.empsuggest.EmpSuggestCreateDto;
import ogya.workshop.performance_appraisal.dto.empsuggest.EmpSuggestDto;
import ogya.workshop.performance_appraisal.service.EmpSuggestServ;
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
@RequestMapping("/emp-suggest")
public class EmpSuggestController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(EmpSuggestController.class);

    @Autowired
    private EmpSuggestServ empSuggestServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<EmpSuggestDto>>> getAll() {
        Log.info("Start getAll in EmpSuggestController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpSuggestDto>> response = new ManagerDto<>();
        List<EmpSuggestDto> content = empSuggestServ.findAll();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAll in EmpSuggestController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ManagerDto<EmpSuggestDto>> getDetail(@PathVariable("id") UUID id) {
        Log.info("Start getDetail in EmpSuggestController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpSuggestDto> response = new ManagerDto<>();
        EmpSuggestDto content = empSuggestServ.findById(id);

        response.setContent(content);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getDetail in EmpSuggestController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ManagerDto<List<EmpSuggestDto>>> getDetailByUser(@PathVariable("id") UUID id) {
        Log.info("Start getDetailByUser in EmpSuggestController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpSuggestDto>> response = new ManagerDto<>();
        List<EmpSuggestDto> content = empSuggestServ.findByUserId(id);

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getDetailByUser in EmpSuggestController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ManagerDto<List<EmpSuggestDto>>> create(@RequestBody List<EmpSuggestCreateDto> empSuggestCreateDtos) {
        Log.info("Start create in EmpSuggestController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<EmpSuggestDto>> response = new ManagerDto<>();
        List<EmpSuggestDto> content = empSuggestServ.saveAll(empSuggestCreateDtos);

        response.setContent(content);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success create data", executionTime));
        Log.info("End create in EmpSuggestController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> delete(@PathVariable("id") UUID id) {
        Log.info("Start delete in EmpSuggestController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        response.setContent(empSuggestServ.deleteById(id));
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End delete in EmpSuggestController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<EmpSuggestDto>> update(@PathVariable("id") UUID id, @RequestBody EmpSuggestCreateDto empSuggestDto) {
        Log.info("Start update in EmpSuggestController");
        long startTime = System.currentTimeMillis();

        ManagerDto<EmpSuggestDto> response = new ManagerDto<>();
        response.setContent(empSuggestServ.update(id,empSuggestDto));
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End update in EmpSuggestController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/userId&Year")
    public List<EmpSuggestDto> getEmpSuggestByUserIdAndYear(
            @RequestParam UUID userId,
            @RequestParam Integer assessmentYear) {
        return empSuggestServ.findByUserIdAndAssessmentYear(userId, assessmentYear);
    }

    @GetMapping("/assessment-years")
    public ResponseEntity<List<Integer>> getEmpTechSkillByYear() {
        Log.info("Start getEmpTechSkillByYear in EmpSuggestController");
        List<Integer> years = empSuggestServ.getDistinctAssessmentYears();
        Log.info("End getEmpTechSkillByYear in EmpSuggestController");
        return ResponseEntity.ok(years);
    }
}
