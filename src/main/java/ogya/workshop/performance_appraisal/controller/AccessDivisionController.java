package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.accessdivision.AccessDivisionDto;
import ogya.workshop.performance_appraisal.service.AccessDivisionServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/access-division")
public class AccessDivisionController {

    @Autowired
    private AccessDivisionServ accessDivisionServ;

    // Create a new Achievement
    @PostMapping
    public ResponseEntity<AccessDivisionDto> createAccessDivision(@RequestBody AccessDivisionDto accessDivisionDto) {
        AccessDivisionDto newAccessDivision = accessDivisionServ.createAccessDivision(accessDivisionDto);
        return ResponseEntity.ok(newAccessDivision);
    }

    // Update an existing Achievement
    @PutMapping("/{id}")
    public ResponseEntity<AccessDivisionDto> updateAccessDivision(@PathVariable UUID id, @RequestBody AccessDivisionDto accessDivisionDto) {
        try {
            AccessDivisionDto updateAccessDivision = accessDivisionServ.updateAccessDivision(id, accessDivisionDto);
            return ResponseEntity.ok(updateAccessDivision);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Retrieve by ID
    @GetMapping("/{id}")
    public ResponseEntity<AccessDivisionDto> getAccessDivisionById(@PathVariable UUID id) {
        Optional<AccessDivisionDto> accessDivision = accessDivisionServ.getAccessDivisionById(id);
        return accessDivision.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve all Achievements
    @GetMapping
    public List<AccessDivisionDto> getAllAccessDivision() {
        return accessDivisionServ.getAllAccessDivision();
    }

    // Delete an Achievement by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAccessDivision(@PathVariable UUID id) {
        Boolean response = accessDivisionServ.deleteAccessDivision(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
