package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.menu.MenuCreateDto;
import ogya.workshop.performance_appraisal.dto.menu.MenuDto;
import ogya.workshop.performance_appraisal.dto.menu.MenuInfoDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.GroupAchieve;
import ogya.workshop.performance_appraisal.entity.Menu;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.MenuRepo;
import ogya.workshop.performance_appraisal.service.MenuServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuServImpl implements MenuServ {

    @Autowired
    private MenuRepo menuRepo;

    // Create a new Group Achieve
    @Override
    public MenuDto createMenu(MenuCreateDto menuDto) {
        Menu menu = convertToEntity(menuDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        menu.setCreatedBy(creator);

        menu.setCreatedAt(new Date());  // Set the creation date
        Menu savedMenu = menuRepo.save(menu);
        return convertToDto(savedMenu);
    }

    // Update an existing Achieve
    @Override
    public MenuDto updateMenu(UUID id, MenuCreateDto menuDto) {
        Menu currentMenu = menuRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Menu with this ID does not exist."));

        if(menuDto.getMenuName() != null){
            currentMenu.setMenuName(menuDto.getMenuName());
        }

        currentMenu.setUpdatedAt(new Date());  // Set the updated date

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        currentMenu.setUpdatedBy(creator);

        Menu updatedMenu = menuRepo.save(currentMenu);
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

    @Override
    public List<MenuInfoDto> getMenuByUserId(UUID userId) {

        List<Menu> menus = menuRepo.findMenuByUserId(userId);
        Set<Menu> menuSets = new HashSet<>(menuRepo.findMenuByUserId(userId));
        return menuSets.stream()
                .map(menu ->  {
                    return new MenuInfoDto(
                            menu.getId(),        // Cast the value to UUID
                            menu.getMenuName() // Cast the value to String
                    ) ;
                })
                .collect(Collectors.toList());
    }


    // Helper method to convert Achieve entity to AchieveDto
    private MenuDto convertToDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setMenuName(menu.getMenuName());
        menuDto.setCreatedAt(menu.getCreatedAt());
        if(menu.getCreatedBy() != null){
            menuDto.setCreatedBy(UserInfoDto.fromEntity(menu.getCreatedBy()));
        }
        menuDto.setUpdatedAt(menu.getUpdatedAt());
        if(menu.getUpdatedBy() != null){
            menuDto.setUpdatedBy(UserInfoDto.fromEntity(menu.getUpdatedBy()));
        }
        return menuDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private Menu convertToEntity(MenuCreateDto menuDto) {
        Menu menu = new Menu();
        menu.setMenuName(menuDto.getMenuName());
        return menu;
    }
}
