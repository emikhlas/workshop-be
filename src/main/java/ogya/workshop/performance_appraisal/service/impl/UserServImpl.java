package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.dto.user.UserReqDto;
import ogya.workshop.performance_appraisal.entity.Role;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.entity.UserRole;
import ogya.workshop.performance_appraisal.repository.RoleRepo;
import ogya.workshop.performance_appraisal.repository.UserRepo;
import ogya.workshop.performance_appraisal.repository.UserRoleRepo;
import ogya.workshop.performance_appraisal.service.UserServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<UserDto> getAllUsers() {
        Log.info("Start getAllUsers in UserServImpl");
        List<User> response = userRepo.findAll();
        List<UserDto> userList = new ArrayList<>();

        for (User user : response) {
            UserDto userDto = UserDto.fromEntity(user);
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
        Set<String> roles = new HashSet<>();
        if(!userRoles.isEmpty()) {
            for (UserRole userRole : userRoles) {
                String rolename= userRole.getRole().getRolename();
                roles.add(rolename);
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


        userRepo.save(user);
        for(UUID roleId : userDto.getRole()) {
            Role role = roleRepo.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRoleRepo.save(userRole);
        }

        Log.info("End createUser in UserServImpl");
        return UserDto.fromEntity(user);
    }

    @Override
    public UserDto updateUser (UUID id, UserReqDto userDto) {
        Log.info("Start updateUser in UserServImpl");
        User findUser  = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User  not found"));

        // Update fields based on userDto, falling back to findUser  if userDto field is null
        updateUserFields(findUser , userDto);

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
        if (userDto.getDivisionId() != null) {
            existingUser.setDivisionId(userDto.getDivisionId());
        }
    }
}
