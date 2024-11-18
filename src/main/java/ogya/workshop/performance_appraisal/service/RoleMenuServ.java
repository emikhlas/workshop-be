package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.rolemenu.RoleMenuDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleMenuServ {
    RoleMenuDto createRoleMenu(RoleMenuDto roleMenuDto);
    RoleMenuDto updateRoleMenu(UUID id, RoleMenuDto roleMenuDto);
    Optional<RoleMenuDto> getRoleMenuById(UUID id);
    List<RoleMenuDto> getAllRoleMenu();
    boolean deleteRoleMenu(UUID id);
}
