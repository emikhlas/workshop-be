package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumDetailDto;
import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumDto;
import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumReqDto;

import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumResDto;
import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumWithUserDto;
import ogya.workshop.performance_appraisal.entity.AssessSum;

import ogya.workshop.performance_appraisal.service.AssessSumServ;
import ogya.workshop.performance_appraisal.service.DivisionServ;
import ogya.workshop.performance_appraisal.util.PageInfo;
import ogya.workshop.performance_appraisal.util.ServerResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private DivisionServ divisionServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<AssessSumResDto>> getAllAssessSum(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) List<UUID> divisionIds,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Log.info("Start getAllAssessSum in AssessSumController");
        long startTime = System.currentTimeMillis();

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        Page<AssessSumDto> result = assessSumServ.getFilteredAssessSum(searchTerm, year, divisionIds, pageable);

        ManagerDto<AssessSumResDto> response = new ManagerDto<>();
        List<AssessSumDto> assessSumList = result.getContent();
        List<Integer> years = assessSumServ.getDistinctAssessmentYears();
        AssessSumResDto content = new AssessSumResDto();
        content.setAssessSumList(assessSumList);
        content.setYears(years);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(result.getNumber());
        pageInfo.setSize(result.getSize());
        pageInfo.setTotalElements(result.getTotalElements());
        pageInfo.setTotalPages(result.getTotalPages());
        pageInfo.setLast(result.isLast());
        pageInfo.setFirst(result.isFirst());
        response.setPageInfo(pageInfo);

        response.setContent(content);
        response.setTotalRows(assessSumList.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllAssessSum in AssessSumController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ManagerDto<AssessSumDto>> saveAssessSum(@RequestBody AssessSumReqDto assessSumReqDto) {
        Log.info("Start saveAssessSum in AssessSumController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AssessSumDto> response = new ManagerDto<>();
        AssessSumDto content = assessSumServ.createAssessSum(assessSumReqDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveAssessSum in AssessSumController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<AssessSumDto>> updateAssessSum(
            @PathVariable("id") UUID id,
            @RequestBody AssessSumReqDto assessSumReqDto) {
        Log.info("Start updateAssessSum in AssessSumController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AssessSumDto> response = new ManagerDto<>();
        AssessSumDto content = assessSumServ.updateAssessSum(id,assessSumReqDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateAssessSum in AssessSumController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/detail-by-user/{id}")
    public ResponseEntity<ManagerDto<List<AssessSumDto>>> getAssessSumDetailByUser(@PathVariable("id") UUID id) {
        Log.info("Start getAssessSumDetailByUser in AssessSumController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<AssessSumDto>> response = new ManagerDto<>();
        List<AssessSumDto> content = assessSumServ.getAssessSumByUserId(id);

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
    public ResponseEntity<ManagerDto<AssessSumDto>> generateAssessSum(@PathVariable("userId") UUID userId, @PathVariable("year") int year) {
        Log.info("Start generateAssessSum in AssessSumController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AssessSumDto> response = new ManagerDto<>();
        AssessSumDto content = assessSumServ.generateAssessSum(userId, year);

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
    public ResponseEntity<ManagerDto<AssessSumWithUserDto>> updateAssessSumStatusToApprove(
            @PathVariable("id") UUID id) {
        Log.info("Start updateAssessSumStatusToApprove in AssessSumController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AssessSumWithUserDto> response = new ManagerDto<>();
        AssessSumWithUserDto content = assessSumServ.updateAssessSumStatusToApprove(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update status to approve", executionTime));
        Log.info("End updateAssessSumStatusToApprove in AssessSumController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update-status-to-unapprove/{id}")
    public ResponseEntity<ManagerDto<AssessSumWithUserDto>> updateAssessSumStatusToUnapprove(
            @PathVariable("id") UUID id) {
        Log.info("Start updateAssessSumStatusToUnapprove in AssessSumController");
        long startTime = System.currentTimeMillis();

        ManagerDto<AssessSumWithUserDto> response = new ManagerDto<>();
        AssessSumWithUserDto content = assessSumServ.updateAssessSumStatusToUnapprove(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update status to unapprove", executionTime));
        Log.info("End updateAssessSumStatusToUnapprove in AssessSumController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
