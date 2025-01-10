package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.role.RoleDto;
import ogya.workshop.performance_appraisal.dto.role.RoleReqDto;
import ogya.workshop.performance_appraisal.dto.userrole.UserRoleDto;

import java.util.List;
import java.util.UUID;

public interface RoleServ {
    List<RoleDto> getAllRoles();

    RoleDto getRoleById(UUID id);

    RoleDto createRole(RoleReqDto roleDto);

    RoleDto updateRole(UUID id, RoleReqDto roleDto);

    Boolean deleteRole(UUID id);

    List<UserRoleDto> getUsersWithRole();
}
