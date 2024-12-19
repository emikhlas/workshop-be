package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.role.RoleInfoDto;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.dto.user.UserReqDto;
import ogya.workshop.performance_appraisal.dto.userrole.UserRoleUpdateDto;
import ogya.workshop.performance_appraisal.entity.Division;
import ogya.workshop.performance_appraisal.entity.Role;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.entity.UserRole;
import ogya.workshop.performance_appraisal.repository.DivisionRepo;
import ogya.workshop.performance_appraisal.repository.RoleRepo;
import ogya.workshop.performance_appraisal.repository.UserRepo;
import ogya.workshop.performance_appraisal.repository.UserRoleRepo;
import ogya.workshop.performance_appraisal.service.UserRoleServ;
import ogya.workshop.performance_appraisal.service.UserServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServImpl implements UserServ {

    private final Logger Log = LoggerFactory.getLogger(UserServImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private DivisionRepo divisionRepo;

    @Autowired
    private UserRoleServ userRoleServ;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAllUsers() {
        Log.info("Start getAllUsers in UserServImpl");
        List<User> response = userRepo.findAll();
        List<UserDto> userList = new ArrayList<>();

        for (User user : response) {
            UserDto userDto = UserDto.fromEntity(user);
            List<UserRole> userRoles = userRoleRepo.findByUserId(userDto.getId());
            Set<RoleInfoDto> roles = new HashSet<>();
            if(!userRoles.isEmpty()) {
                for (UserRole userRole : userRoles) {
                    Role role= userRole.getRole();
                    roles.add(RoleInfoDto.fromEntity(role));
                }
            }
            userDto.setRole(roles);
            userList.add(userDto);
        }

        Log.info("End getAllUsers in UserServImpl");
        return userList;
    }

    @Override
    public UserDto getUserById(UUID id) {
        Log.info("Start getUserById in UserServImpl");
        Optional<User> user = userRepo.findById(id);
        List<UserRole> userRoles = userRoleRepo.findByUserId(id);
        Set<RoleInfoDto> roles = new HashSet<>();
        if(!userRoles.isEmpty()) {
            for (UserRole userRole : userRoles) {
                Role role= userRole.getRole();
                roles.add(RoleInfoDto.fromEntity(role));
            }
        }
        Log.info("End getUserById in UserServImpl");
        if (user.isPresent()) {
            UserDto userDto = UserDto.fromEntity(user.get());
            userDto.setRole(roles);
            return userDto;
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public UserDto createUser(UserReqDto userDto) {
        Log.info("Start createUser in UserServImpl");
        User user = UserReqDto.toEntity(userDto);
        if (userDto.getDivisionId() != null) {
            Division division = divisionRepo.findById(userDto.getDivisionId()).orElseThrow(() -> new RuntimeException("Division not found"));
            user.setDivision(division);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();
        Log.info("creator: {}", creator);

        user.setCreatedBy(creator);
        user.setCreatedAt(LocalDateTime.now());

        userRepo.save(user);
        UserDto result = UserDto.fromEntity(user);
        Set<RoleInfoDto> roles = new HashSet<>();
        for(UUID roleId : userDto.getRole()) {
            Role role = roleRepo.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRoleRepo.save(userRole);
            roles.add(RoleInfoDto.fromEntity(role));
        }
        result.setRole(roles);

        Log.info("End createUser in UserServImpl");
        return result;
    }

    @Override
    public UserDto updateUser (UUID id, UserReqDto userDto) {
        Log.info("Start updateUser in UserServImpl");
        User findUser  = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User  not found"));
        if (userDto.getDivisionId() != null) {
            Division division = divisionRepo.findById(userDto.getDivisionId()).orElseThrow(() -> new RuntimeException("Division not found"));
            findUser.setDivision(division);
        }

        if(userDto.getRole() != null) {
            UserRoleUpdateDto userRoleUpdateDto = new UserRoleUpdateDto();
            userRoleUpdateDto.setRole(userDto.getRole());
            userRoleServ.updateUserRole(id,userRoleUpdateDto);
        }

        updateUserFields(findUser , userDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        findUser.setUpdatedBy(creator);
        findUser.setUpdatedAt(LocalDateTime.now());

        userRepo.save(findUser);
        Log.info("End updateUser in UserServImpl");
        return UserDto.fromEntity(findUser);
    }

    @Override
    public Boolean deleteUser(UUID id) {
        Log.info("Start deleteUser in UserServImpl");
        User findUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User  not found"));
        userRepo.delete(findUser);
        Log.info("End deleteUser in UserServImpl");
        return true;
    }

    @Override
    public String resetPassword(UUID id) {
        Log.info("Start resetPassword in UserServImpl");
        User findUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User  not found"));
        String newPassword = generatePassword();
        findUser.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(findUser);
        Log.info("End resetPassword in UserServImpl");
        return newPassword;
    }

    public static String generatePassword() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }

    private void updateUserFields(User existingUser, UserReqDto userDto) {
        if (userDto.getUsername() != null) {
            existingUser.setUsername(userDto.getUsername());
        }
        if (userDto.getFullName() != null) {
            existingUser.setFullName(userDto.getFullName());
        }
        if (userDto.getPosition() != null) {
            existingUser.setPosition(userDto.getPosition());
        }
        if (userDto.getEmailAddress() != null) {
            existingUser.setEmailAddress(userDto.getEmailAddress());
        }
        if (userDto.getEmployeeStatus() != null) {
            existingUser.setEmployeeStatus(userDto.getEmployeeStatus());
        }
        if (userDto.getJoinDate() != null) {
            existingUser.setJoinDate(userDto.getJoinDate().toLocalDate());
        }
        if (userDto.getEnabled() != null) {
            existingUser.setEnabled(userDto.getEnabled());
        }
        if (userDto.getPassword() != null) {
            existingUser.setPassword(userDto.getPassword());
        }
    }
}
