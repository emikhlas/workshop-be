package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.rolemenu.RoleMenuByRoleDto;
import ogya.workshop.performance_appraisal.dto.rolemenu.RoleMenuCreateDto;
import ogya.workshop.performance_appraisal.dto.rolemenu.RoleMenuDto;
import ogya.workshop.performance_appraisal.dto.rolemenu.RoleMenuUpdateDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleMenuServ {
    RoleMenuDto createRoleMenu(RoleMenuCreateDto roleMenuDto);

    RoleMenuDto updateRoleMenu(UUID id, RoleMenuCreateDto roleMenuDto);

    Optional<RoleMenuDto> getRoleMenuById(UUID id);

    List<RoleMenuDto> getAllRoleMenu();

    boolean deleteRoleMenu(UUID id);

    List<RoleMenuDto> updateRoleMenuByRoleId(UUID roleId, RoleMenuUpdateDto roleMenuUpdateDto);

    List<RoleMenuByRoleDto> getAllRoleMenuByRole();
}
