package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumDetailDto;
import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumReqDto;
import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumWithUserDto;
import ogya.workshop.performance_appraisal.entity.AssessSum;
import ogya.workshop.performance_appraisal.service.AssessSumServ;
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
@RequestMapping("/assess-sum")
public class AssessSumController extends ServerResponseList {

    private final Logger Log = LoggerFactory.getLogger(AssessSumController.class);
    @Autowired
    private AssessSumServ assessSumServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<AssessSumWithUserDto>>> getAllAssessSum(@RequestParam(value = "year", required = false) Integer year) {

        Log.info("Start getAllAssessSum in AssessSumController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<AssessSumWithUserDto>> response = new ManagerDto<>();
        List<AssessSumWithUserDto> content;
        if (year != null) {
            content = assessSumServ.getAllAssessSumByYear(year);
        } else {
            content = assessSumServ.getAllAssessSum();
        }

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllAssessSum in AssessSumController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ManagerDto<AssessSumWithUserDto>> saveAssessSum(@RequestBody AssessSumReqDto assessSumReqDto) {
        Log.info("Start saveAssessSum in AssessSumController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AssessSumWithUserDto> response = new ManagerDto<>();
        AssessSumWithUserDto content = assessSumServ.createAssessSum(assessSumReqDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveAssessSum in AssessSumController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<AssessSumWithUserDto>> updateAssessSum(
            @PathVariable("id") UUID id,
            @RequestBody AssessSumReqDto assessSumReqDto) {
        Log.info("Start updateAssessSum in AssessSumController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AssessSumWithUserDto> response = new ManagerDto<>();
        AssessSumWithUserDto content = assessSumServ.updateAssessSum(id,assessSumReqDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateAssessSum in AssessSumController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/detail-by-user/{id}")
    public ResponseEntity<ManagerDto<List<AssessSumWithUserDto>>> getAssessSumDetailByUser(@PathVariable("id") UUID id) {
        Log.info("Start getAssessSumDetailByUser in AssessSumController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<AssessSumWithUserDto>> response = new ManagerDto<>();
        List<AssessSumWithUserDto> content = assessSumServ.getAssessSumByUserId(id);

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAssessSumDetailByUser in AssessSumController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteAssessSum(@PathVariable("id") UUID id) {
        Log.info("Start deleteAssessSum in AssessSumController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        boolean content = assessSumServ.deleteAssessSum(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteAssessSum in AssessSumController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/generate/{userId}/{year}")
    public ResponseEntity<ManagerDto<AssessSumWithUserDto>> generateAssessSum(@PathVariable("userId") UUID userId, @PathVariable("year") int year) {
        Log.info("Start generateAssessSum in AssessSumController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AssessSumWithUserDto> response = new ManagerDto<>();
        AssessSumWithUserDto content = assessSumServ.generateAssessSum(userId, year);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success generate data", executionTime));
        Log.info("End generateAssessSum in AssessSumController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/detail/{userId}/{year}")
    public ResponseEntity<ManagerDto<AssessSumDetailDto>> getAssessSumDetail(@PathVariable("userId") UUID userId, @PathVariable("year") int year){
        Log.info("Start getAssessSumDetail in AssessSumController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AssessSumDetailDto> response = new ManagerDto<>();
        AssessSumDetailDto content = assessSumServ.getAssessSumDetail(userId, year);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAssessSumDetail in AssessSumController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/summary")
    public ResponseEntity<AssessSumWithUserDto> getAssessmentSummary(
            @RequestParam UUID userId,
            @RequestParam Integer year) {
        AssessSumWithUserDto summary = assessSumServ.getAssessmentSummary(userId, year);
        return ResponseEntity.ok(summary);
    }

    @PatchMapping("/update-status-to-approve/{id}")
    public ResponseEntity<ManagerDto<AssessSumWithUserDto>> updateAssessSumStatusToActive(
            @PathVariable("id") UUID id) {
        Log.info("Start updateAssessSumStatusToActive in AssessSumController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AssessSumWithUserDto> response = new ManagerDto<>();
        AssessSumWithUserDto content = assessSumServ.updateAssessSumStatusToActive(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update status to active", executionTime));
        Log.info("End updateAssessSumStatusToActive in AssessSumController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
