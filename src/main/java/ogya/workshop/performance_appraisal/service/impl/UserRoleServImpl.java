package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.userrole.UserRoleDto;
import ogya.workshop.performance_appraisal.dto.userrole.UserRoleUpdateDto;
import ogya.workshop.performance_appraisal.entity.Role;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.entity.UserRole;
import ogya.workshop.performance_appraisal.repository.RoleRepo;
import ogya.workshop.performance_appraisal.repository.UserRepo;
import ogya.workshop.performance_appraisal.repository.UserRoleRepo;
import ogya.workshop.performance_appraisal.service.UserRoleServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserRoleServImpl implements UserRoleServ {
    private final Logger Log = LoggerFactory.getLogger(UserRoleServImpl.class);

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public List<UserRoleDto> updateUserRole(UUID id, UserRoleUpdateDto updateDto) {
        Log.info("Start update in UserRoleServImpl");
        List<UserRole> currentUserRole = userRoleRepo.findByUserId(id);
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        List<UserRoleDto> updatedUserRole = new ArrayList<>();
        for (UserRole userRole : currentUserRole) {
            userRoleRepo.delete(userRole);
        }

        for (UUID roleId : updateDto.getRole()) {
            Role role = roleRepo.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRoleRepo.save(userRole);
            updatedUserRole.add(UserRoleDto.fromEntity(userRole));
        }
        Log.info("End update in UserRoleServImpl");
        return updatedUserRole;
    }

    @Override
    public List<UserRoleDto> getUserRoleByUserId(UUID userId) {
        Log.info("Start getUserRoleByUserId in UserRoleServImpl");
        List<UserRole> response = userRoleRepo.findByUserId(userId);
        List<UserRoleDto> userRoleList = new ArrayList();
        for (UserRole userRole : response) {
            userRoleList.add(UserRoleDto.fromEntity(userRole));
        }
        Log.info("End getUserRoleByUserId in UserRoleServImpl");
        return userRoleList;
    }
}
