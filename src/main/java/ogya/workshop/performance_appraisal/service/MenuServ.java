package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.DivisionDto;
import ogya.workshop.performance_appraisal.dto.MenuDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuServ {
    MenuDto createMenu(MenuDto menuDto);
    MenuDto updateMenu(UUID id, MenuDto menuDto);
    Optional<MenuDto> getMenuById(UUID id);
    List<MenuDto> getAllMenu();
    boolean deleteMenu(UUID id);
}
