package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.devplan.DevPlanCreateDto;
import ogya.workshop.performance_appraisal.dto.devplan.DevPlanDto;
import ogya.workshop.performance_appraisal.service.DevPlanServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/dev-plan")
public class DevPlanController {

    @Autowired
    private DevPlanServ devPlanServ;

    @PostMapping
    public ResponseEntity<DevPlanDto> createDevPlan(@RequestBody DevPlanCreateDto devPlanDto) {
        DevPlanDto newDevPlan = devPlanServ.createDevPlan(devPlanDto);
        return ResponseEntity.ok(newDevPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DevPlanDto> updateDevPlan(@PathVariable UUID id, @RequestBody DevPlanCreateDto devPlanDto) {
        try {
            DevPlanDto updateDevPlan = devPlanServ.updateDevPlan(id, devPlanDto);
            return ResponseEntity.ok(updateDevPlan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<DevPlanDto> getDevPlanById(@PathVariable UUID id) {
        Optional<DevPlanDto> achievement = devPlanServ.getDevPlanById(id);
        return achievement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public List<DevPlanDto> getAllDevPlan() {
        return devPlanServ.getAllDevPlan();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDevPlan(@PathVariable UUID id) {
        Boolean response = devPlanServ.deleteDevPlan(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
