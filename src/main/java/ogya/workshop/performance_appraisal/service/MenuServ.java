package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.menu.MenuCreateDto;
import ogya.workshop.performance_appraisal.dto.menu.MenuDto;
import ogya.workshop.performance_appraisal.dto.menu.MenuInfoDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuServ {
    MenuDto createMenu(MenuCreateDto menuDto);
    MenuDto updateMenu(UUID id, MenuCreateDto menuDto);
    Optional<MenuDto> getMenuById(UUID id);
    List<MenuDto> getAllMenu();
    boolean deleteMenu(UUID id);
    List<MenuInfoDto> getMenuByUserId(UUID id);
}
