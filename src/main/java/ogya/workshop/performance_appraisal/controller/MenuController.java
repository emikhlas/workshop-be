package ogya.workshop.performance_appraisal.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import ogya.workshop.performance_appraisal.dto.menu.MenuCreateDto;
import ogya.workshop.performance_appraisal.dto.menu.MenuDto;
import ogya.workshop.performance_appraisal.dto.menu.MenuInfoDto;
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
@SecurityRequirement(name = "bearerAuth")
public class MenuController {

    @Autowired
    private MenuServ menuServ;

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuCreateDto menuDto) {
        MenuDto newMenu = menuServ.createMenu(menuDto);
        return ResponseEntity.ok(newMenu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDto> updateMenu(@PathVariable UUID id, @RequestBody MenuCreateDto menuDto) {
        try {
            MenuDto updateMenu = menuServ.updateMenu(id, menuDto);
            return ResponseEntity.ok(updateMenu);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getMenuById(@PathVariable UUID id) {
        Optional<MenuDto> menu = menuServ.getMenuById(id);
        return menu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<MenuDto> getAllMenu() {
        return menuServ.getAllMenu();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMenu(@PathVariable UUID id) {
        Boolean response = menuServ.deleteMenu(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{user-id}")
    public ResponseEntity<List<MenuInfoDto>> getMenuByUserId(@PathVariable("user-id") UUID userId) {
        List<MenuInfoDto> menu = menuServ.getMenuByUserId(userId);
        return ResponseEntity.ok(menu);
    }
}
