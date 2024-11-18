package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.menu.MenuDto;
import ogya.workshop.performance_appraisal.service.MenuServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuServ menuServ;

    // Create a new Achievement
    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto) {
        MenuDto newMenu = menuServ.createMenu(menuDto);
        return ResponseEntity.ok(newMenu);
    }

    // Update an existing Achievement
    @PutMapping("/{id}")
    public ResponseEntity<MenuDto> updateMenu(@PathVariable UUID id, @RequestBody MenuDto menuDto) {
        try {
            MenuDto updateMenu = menuServ.updateMenu(id, menuDto);
            return ResponseEntity.ok(updateMenu);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Retrieve by ID
    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getMenuById(@PathVariable UUID id) {
        Optional<MenuDto> menu = menuServ.getMenuById(id);
        return menu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve all Achievements
    @GetMapping
    public List<MenuDto> getAllMenu() {
        return menuServ.getAllMenu();
    }

    // Delete an Achievement by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMenu(@PathVariable UUID id) {
        Boolean response = menuServ.deleteMenu(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
