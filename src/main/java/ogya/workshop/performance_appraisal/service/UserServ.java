package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.dto.user.UserReqDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserServ {
    Page<UserDto> getAllUsers(String searchTerm, Pageable pageable);
    UserDto getUserById(UUID id);
    UserDto createUser(UserReqDto userDto);
    UserDto updateUser(UUID id, UserReqDto userDto);
    Boolean deleteUser(UUID id);
    String resetPassword(UUID id);
    Boolean isUsernameExist(String username);
    Boolean isEmailExist(String email);
}
