package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.AttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.DevPlanDto;
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

    // Create a new Achievement
    @PostMapping
    public ResponseEntity<DevPlanDto> createDevPlan(@RequestBody DevPlanDto devPlanDto) {
        DevPlanDto newDevPlan = devPlanServ.createDevPlan(devPlanDto);
        return ResponseEntity.ok(newDevPlan);
    }

    // Update an existing Achievement
    @PutMapping("/{id}")
    public ResponseEntity<DevPlanDto> updateDevPlan(@PathVariable UUID id, @RequestBody DevPlanDto devPlanDto) {
        try {
            DevPlanDto updateDevPlan = devPlanServ.updateDevPlan(id, devPlanDto);
            return ResponseEntity.ok(updateDevPlan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Retrieve by ID
    @GetMapping("/{id}")
    public ResponseEntity<DevPlanDto> getDevPlanById(@PathVariable UUID id) {
        Optional<DevPlanDto> achievement = devPlanServ.getDevPlanById(id);
        return achievement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve all Achievements
    @GetMapping
    public List<DevPlanDto> getAllDevPlan() {
        return devPlanServ.getAllDevPlan();
    }

    // Delete an Achievement by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDevPlan(@PathVariable UUID id) {
        Boolean response = devPlanServ.deleteDevPlan(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
