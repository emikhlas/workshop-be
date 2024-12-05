package ogya.workshop.performance_appraisal.config.security.Auth;

import ogya.workshop.performance_appraisal.dto.auth.AuthRequestDto;
import ogya.workshop.performance_appraisal.dto.auth.ChangePasswordDto;
import ogya.workshop.performance_appraisal.entity.Role;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.entity.UserRole;
import ogya.workshop.performance_appraisal.repository.UserRepo;
import ogya.workshop.performance_appraisal.repository.UserRoleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Start loadUserByUsername in AuthService");

        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("User not found: {}", username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        List<UserRole> userRoles = userRoleRepo.findByUserId(user.getId());
        if (userRoles == null || userRoles.isEmpty()) {
            log.warn("User roles not found for user: {}", username);
            throw new UsernameNotFoundException("No roles found for user: " + username);
        }

        log.info("User roles found for {}: {}", username, userRoles);

        List<Role> roles = new ArrayList<>();
        for (UserRole role : userRoles) {
            roles.add(role.getRole());
        }

        log.info("End loadUserByUsername in AuthService");

        return new AuthUser(user, roles);
    }

    public AuthUser loadUserById(UUID userId) throws UsernameNotFoundException {
        log.info("Start loadUserById in AuthService");

        User user = userRepo.findById(userId)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", userId);
                    return new UsernameNotFoundException("User not found");
                });

        List<UserRole> userRoles = userRoleRepo.findByUserId(userId);
        if (userRoles == null || userRoles.isEmpty()) {
            log.warn("User roles not found for user ID: {}", userId);
            throw new UsernameNotFoundException("No roles found for user ID: " + userId);
        }

        log.info("User roles found for user ID {}: {}", userId, userRoles);

        List<Role> roles = new ArrayList<>();
        for (UserRole role : userRoles) {
            roles.add(role.getRole());
        }

        log.info("End loadUserById in AuthService");

        return new AuthUser(user, roles);
    }


    public AuthUser authenticate(AuthRequestDto authRequestDto) {
        String username = authRequestDto.getUsername();
        String password = authRequestDto.getPassword();

        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("User not found: {}", username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.error("Invalid password for user: {}", username);
            throw new UsernameNotFoundException("Invalid password for user: " + username);
        }

        List<UserRole> userRoles = userRoleRepo.findByUserId(user.getId());
        if (userRoles == null || userRoles.isEmpty()) {
            log.warn("User roles not found for user: {}", username);
            throw new UsernameNotFoundException("No roles found for user: " + username);
        }

        List<Role> roles = new ArrayList<>();
        for (UserRole role : userRoles) {
            roles.add(role.getRole());
        }

        return  new AuthUser(user, roles);
    }

    public AuthUser changePassword(UUID id, ChangePasswordDto changePasswordDto) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
            userRepo.save(user);
            return loadUserById(id);
        } else {
            throw new RuntimeException("Incorrect password");
        }
    }
}

