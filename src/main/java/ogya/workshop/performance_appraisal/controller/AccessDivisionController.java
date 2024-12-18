package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.accessdivision.AccessDivisionCreateDto;
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

    @PostMapping
    public ResponseEntity<AccessDivisionDto> createAccessDivision(@RequestBody AccessDivisionCreateDto accessDivisionDto) {
        AccessDivisionDto newAccessDivision = accessDivisionServ.createAccessDivision(accessDivisionDto);
        return ResponseEntity.ok(newAccessDivision);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccessDivisionDto> updateAccessDivision(@PathVariable UUID id, @RequestBody AccessDivisionCreateDto accessDivisionDto) {
        try {
            AccessDivisionDto updateAccessDivision = accessDivisionServ.updateAccessDivision(id, accessDivisionDto);
            return ResponseEntity.ok(updateAccessDivision);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessDivisionDto> getAccessDivisionById(@PathVariable UUID id) {
        Optional<AccessDivisionDto> accessDivision = accessDivisionServ.getAccessDivisionById(id);
        return accessDivision.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<AccessDivisionDto> getAllAccessDivision() {
        return accessDivisionServ.getAllAccessDivision();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAccessDivision(@PathVariable UUID id) {
        Boolean response = accessDivisionServ.deleteAccessDivision(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
