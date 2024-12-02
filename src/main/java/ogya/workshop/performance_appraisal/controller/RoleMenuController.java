package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.rolemenu.RoleMenuByRoleDto;
import ogya.workshop.performance_appraisal.dto.rolemenu.RoleMenuCreateDto;
import ogya.workshop.performance_appraisal.dto.rolemenu.RoleMenuDto;
import ogya.workshop.performance_appraisal.dto.rolemenu.RoleMenuUpdateDto;
import ogya.workshop.performance_appraisal.service.RoleMenuServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/role-menu")
public class RoleMenuController {

    @Autowired
    private RoleMenuServ roleMenuServ;

    // Create a new Achievement
    @PostMapping
    public ResponseEntity<RoleMenuDto> createRoleMenu(@RequestBody RoleMenuCreateDto roleMenuDto) {
        RoleMenuDto newRoleMenu = roleMenuServ.createRoleMenu(roleMenuDto);
        return ResponseEntity.ok(newRoleMenu);
    }

    // Update an existing Achievement
    @PutMapping("/{id}")
    public ResponseEntity<RoleMenuDto> updateRoleMenu(@PathVariable UUID id, @RequestBody RoleMenuCreateDto roleMenuDto) {
        try {
            RoleMenuDto updateRoleMenu = roleMenuServ.updateRoleMenu(id, roleMenuDto);
            return ResponseEntity.ok(updateRoleMenu);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Retrieve by ID
    @GetMapping("/{id}")
    public ResponseEntity<RoleMenuDto> getRoleMenuById(@PathVariable UUID id) {
        Optional<RoleMenuDto> roleMenu = roleMenuServ.getRoleMenuById(id);
        return roleMenu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve all Achievements
    @GetMapping
    public List<RoleMenuDto> getAllRoleMenu() {
        return roleMenuServ.getAllRoleMenu();
    }

    // Delete an Achievement by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRoleMenu(@PathVariable UUID id) {
        Boolean response = roleMenuServ.deleteRoleMenu(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/role")
    public ResponseEntity<List<RoleMenuByRoleDto>> getAllRoleMenuByRole() {
        List<RoleMenuByRoleDto> response = roleMenuServ.getAllRoleMenuByRole();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<List<RoleMenuDto>> updateRoleMenuByRoleId(@PathVariable UUID id, @RequestBody RoleMenuUpdateDto roleMenuUpdateDto) {
        List<RoleMenuDto> response = roleMenuServ.updateRoleMenuByRoleId(id, roleMenuUpdateDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
