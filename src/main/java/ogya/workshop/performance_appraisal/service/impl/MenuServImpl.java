package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.menu.MenuCreateDto;
import ogya.workshop.performance_appraisal.dto.menu.MenuDto;
import ogya.workshop.performance_appraisal.dto.menu.MenuInfoDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
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

    @Override
    public MenuDto createMenu(MenuCreateDto menuDto) {
        Menu menu = convertToEntity(menuDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        menu.setCreatedBy(creator);

        menu.setCreatedAt(new Date());
        Menu savedMenu = menuRepo.save(menu);
        return convertToDto(savedMenu);
    }

    @Override
    public MenuDto updateMenu(UUID id, MenuCreateDto menuDto) {
        Menu currentMenu = menuRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Menu with this ID does not exist."));

        if(menuDto.getMenuName() != null){
            currentMenu.setMenuName(menuDto.getMenuName());
        }

        currentMenu.setUpdatedAt(new Date());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        currentMenu.setUpdatedBy(creator);

        Menu updatedMenu = menuRepo.save(currentMenu);
        return convertToDto(updatedMenu);
    }

    @Override
    public Optional<MenuDto> getMenuById(UUID id) {
        Optional<Menu> menu = menuRepo.findById(id);
        return menu.map(this::convertToDto);
    }

    @Override
    public List<MenuDto> getAllMenu() {
        List<Menu> menus = menuRepo.findAll();
        return menus.stream().map(this::convertToDto).collect(Collectors.toList());
    }

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
                            menu.getId(),
                            menu.getMenuName()
                    ) ;
                })
                .collect(Collectors.toList());
    }

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

    private Menu convertToEntity(MenuCreateDto menuDto) {
        Menu menu = new Menu();
        menu.setMenuName(menuDto.getMenuName());
        return menu;
    }
}
