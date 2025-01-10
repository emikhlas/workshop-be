package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.empdevplan.EmpDevPlanCreateDto;
import ogya.workshop.performance_appraisal.dto.empdevplan.EmpDevPlanDto;
import ogya.workshop.performance_appraisal.service.EmpDevPlanServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/emp-dev-plan")
public class EmpDevPlanController {

    @Autowired
    private EmpDevPlanServ empDevPlanServ;

    @PostMapping
    public ResponseEntity<List<EmpDevPlanDto>> createEmpDevPlan(@RequestBody List<EmpDevPlanCreateDto> empDevPlanDtos) {
        List<EmpDevPlanDto> newEmpDevPlan = empDevPlanServ.createEmpDevPlan(empDevPlanDtos);
        return ResponseEntity.ok(newEmpDevPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpDevPlanDto> updateEmpDevPlan(@PathVariable UUID id, @RequestBody EmpDevPlanCreateDto empDevPlanDto) {
        try {
            EmpDevPlanDto updateEmpDevPlan = empDevPlanServ.updateEmpDevPlan(id, empDevPlanDto);
            return ResponseEntity.ok(updateEmpDevPlan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpDevPlanDto> getEmpDevPlanById(@PathVariable UUID id) {
        Optional<EmpDevPlanDto> empDevPlan = empDevPlanServ.getEmpDevPlanById(id);
        return empDevPlan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<EmpDevPlanDto> getAllEmpDevPlan() {
        return empDevPlanServ.getAllEmpDevPlan();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EmpDevPlanDto>> getEmpDevPlanByUserId(@PathVariable UUID userId) {
        List<EmpDevPlanDto> empDevPlans = empDevPlanServ.getEmpDevPlanByUserId(userId);
        return ResponseEntity.ok(empDevPlans);
    }

    @GetMapping("/user/{userId}/with-plan")
    public ResponseEntity<List<EmpDevPlanDto>> getEmpDevPlanWithPlanByUserId(@PathVariable UUID userId) {
        List<EmpDevPlanDto> empDevPlansWithPlan = empDevPlanServ.getEmpDevPlanWithPlan(userId);
        return ResponseEntity.ok(empDevPlansWithPlan);
    }

    @GetMapping("/getByUserIdAndYear")
    public ResponseEntity<List<EmpDevPlanDto>> getEmpDevPlanByUserIdAndYear(
            @RequestParam UUID userId,
            @RequestParam Integer assessmentYear) {
        List<EmpDevPlanDto> empDevPlans = empDevPlanServ.getEmpDevPlanByUserIdAndYear(userId, assessmentYear);
        return ResponseEntity.ok(empDevPlans);
    }

    @GetMapping("/assessment-years")
    public ResponseEntity<List<Integer>> getAllEmpDevPlanYear() {
        List<Integer> years = empDevPlanServ.getAllEmpDevPlanYear();
        return ResponseEntity.ok(years);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmpDevPlan(@PathVariable UUID id) {
        Boolean response = empDevPlanServ.deleteEmpDevPlan(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
