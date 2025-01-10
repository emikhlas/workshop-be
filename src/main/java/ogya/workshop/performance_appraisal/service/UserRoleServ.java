package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.userrole.UserRoleDto;
import ogya.workshop.performance_appraisal.dto.userrole.UserRoleUpdateDto;

import java.util.List;
import java.util.UUID;

public interface UserRoleServ {
    List<UserRoleDto> updateUserRole(UUID userId, UserRoleUpdateDto updateDto);

    List<UserRoleDto> getUserRoleByUserId(UUID userId);
}
