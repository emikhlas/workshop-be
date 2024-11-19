package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.menu.MenuCreateDto;
import ogya.workshop.performance_appraisal.dto.menu.MenuDto;
import ogya.workshop.performance_appraisal.entity.Menu;
import ogya.workshop.performance_appraisal.repository.MenuRepo;
import ogya.workshop.performance_appraisal.service.MenuServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MenuServImpl implements MenuServ {

    @Autowired
    private MenuRepo menuRepo;

    // Create a new Group Achieve
    @Override
    public MenuDto createMenu(MenuCreateDto menuDto) {
        Menu menu = convertToEntity(menuDto);
        menu.setCreatedAt(new Date());  // Set the creation date
        Menu savedMenu = menuRepo.save(menu);
        return convertToDto(savedMenu);
    }

    // Update an existing Achieve
    @Override
    public MenuDto updateMenu(UUID id, MenuCreateDto menuDto) {
        if (!menuRepo.existsById(id)) {
            throw new IllegalArgumentException("Group Achievement with this ID does not exist.");
        }

        Menu menu = convertToEntity(menuDto);
        menu.setId(id);  // Use the ID from the URL path
        menu.setUpdatedAt(new Date());  // Set the updated date

        // Ensure 'createdAt' is set if it's null during the update
        if (menu.getCreatedAt() == null) {
            menu.setCreatedAt(new Date());  // Set current date if null
        }

        Menu updatedMenu = menuRepo.save(menu);
        return convertToDto(updatedMenu);
    }

    // Retrieve by ID
    @Override
    public Optional<MenuDto> getMenuById(UUID id) {
        Optional<Menu> menu = menuRepo.findById(id);
        return menu.map(this::convertToDto);
    }

    // Retrieve all Achievements
    @Override
    public List<MenuDto> getAllMenu() {
        List<Menu> menus = menuRepo.findAll();
        return menus.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Delete an Achieve by ID
    @Override
    public boolean deleteMenu(UUID id) {
        menuRepo.deleteById(id);
        return true;
    }

    // Helper method to convert Achieve entity to AchieveDto
    private MenuDto convertToDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setMenuName(menu.getMenuName());
        menuDto.setCreatedAt(menu.getCreatedAt());
        menuDto.setCreatedBy(menu.getCreatedBy());
        menuDto.setUpdatedAt(menu.getUpdatedAt());
        menuDto.setUpdatedBy(menu.getUpdatedBy());
        return menuDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private Menu convertToEntity(MenuCreateDto menuDto) {
        Menu menu = new Menu();
        menu.setMenuName(menuDto.getMenuName());
        return menu;
    }
}
