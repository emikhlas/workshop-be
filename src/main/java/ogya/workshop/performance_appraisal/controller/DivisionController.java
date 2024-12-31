package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.division.DivisionCreateDto;
import ogya.workshop.performance_appraisal.dto.division.DivisionDto;
import ogya.workshop.performance_appraisal.dto.division.DivisionInfoDto;
import ogya.workshop.performance_appraisal.service.DivisionServ;
import ogya.workshop.performance_appraisal.util.ServerResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/division")
public class DivisionController extends ServerResponseList {

    private final Logger Log = LoggerFactory.getLogger(DivisionController.class);

    @Autowired
    private DivisionServ divisionServ;

    @PostMapping
    public ResponseEntity<DivisionDto> createDivision(@RequestBody DivisionCreateDto divisionDto) {
        DivisionDto newDivision = divisionServ.createDivision(divisionDto);
        return ResponseEntity.ok(newDivision);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DivisionDto> updateDivision(@PathVariable UUID id, @RequestBody DivisionCreateDto divisionDto) {
        try {
            DivisionDto updateDivision = divisionServ.updateDivision(id, divisionDto);
            return ResponseEntity.ok(updateDivision);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DivisionDto> getDivisionById(@PathVariable UUID id) {
        Optional<DivisionDto> division = divisionServ.getDivisionById(id);
        return division.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<DivisionDto> getAllDivision() {
        return divisionServ.getAllDivision();
    }

    @GetMapping("list-name")
    public ResponseEntity<ManagerDto<List<DivisionInfoDto>>> getListDivisionName() {
        Log.info("Start getListDivisionName in DivisionController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<DivisionInfoDto>> response = new ManagerDto<>();
        List<DivisionInfoDto> content = divisionServ.getListDivisionName();
        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getListDivisionName in DivisionController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDivision(@PathVariable UUID id) {
        Boolean response = divisionServ.deleteDivision(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
