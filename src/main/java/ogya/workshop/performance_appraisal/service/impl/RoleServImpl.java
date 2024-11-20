package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.role.RoleDto;
import ogya.workshop.performance_appraisal.dto.role.RoleReqDto;
import ogya.workshop.performance_appraisal.dto.userrole.UserRoleDto;
import ogya.workshop.performance_appraisal.entity.Role;
import ogya.workshop.performance_appraisal.entity.UserRole;
import ogya.workshop.performance_appraisal.repository.RoleRepo;
import ogya.workshop.performance_appraisal.repository.UserRoleRepo;
import ogya.workshop.performance_appraisal.service.RoleServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServImpl implements RoleServ {

    private final Logger Log = LoggerFactory.getLogger(RoleServImpl.class);

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Override
    public List<RoleDto> getAllRoles() {
        Log.info("Start getAllRoles in RoleServImpl");
        List<Role> response = roleRepo.findAll();
        List<RoleDto> roleList = new ArrayList<>();
        for (Role role : response) {
            roleList.add(RoleDto.fromEntity(role));
        }
        Log.info("End getAllRoles in RoleServImpl");
        return roleList;
    }

    @Override
    public RoleDto getRoleById(UUID id) {
        Log.info("Start getRoleById in RoleServImpl");
        Optional<Role> response = roleRepo.findById(id);
        Log.info("End getRoleById in RoleServImpl");
        return response.map(RoleDto::fromEntity).orElse(null);
    }

    @Override
    public RoleDto createRole(RoleReqDto roleDto) {
        Log.info("Start createRole in RoleServImpl");
        Role role = RoleReqDto.toEntity(roleDto);
        Log.info("role: {}", role);
        role.setCreatedAt(LocalDateTime.now());
        roleRepo.save(role);
        Log.info("End createRole in RoleServImpl");
        return RoleDto.fromEntity(role);
    }

    @Override
    public RoleDto updateRole(UUID id,RoleReqDto roleDto) {
        Log.info("Start updateRole in RoleServImpl");
        Role currentRole = roleRepo.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        if(roleDto.getRolename() != null) currentRole.setRolename(roleDto.getRolename().toUpperCase());
        currentRole.setUpdatedAt(LocalDateTime.now());
        roleRepo.save(currentRole);
        Log.info("End updateRole in RoleServImpl");
        return RoleDto.fromEntity(currentRole);
    }

    @Override
    public Boolean deleteRole(UUID id) {
        Log.info("Start deleteRole in RoleServImpl");
        Optional<Role> role = roleRepo.findById(id);
        if(role.isEmpty()){
            throw new RuntimeException("Role not found");
        }
        roleRepo.deleteById(id);
        Log.info("End deleteRole in RoleServImpl");
        return true;
    }

    @Override
    public List<UserRoleDto> getUsersWithRole() {
        Log.info("Start getUsersWithRole in RoleServImpl");
        List<UserRole> response = userRoleRepo.findAll();
        List<UserRoleDto> userRoleList = new ArrayList<>();
        for (UserRole userRole : response) {
            userRoleList.add(UserRoleDto.fromEntity(userRole));
        }
        Log.info("End getUsersWithRole in RoleServImpl");
        return userRoleList;
    }
}
