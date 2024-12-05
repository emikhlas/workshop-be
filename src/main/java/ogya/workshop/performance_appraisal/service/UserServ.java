package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.dto.user.UserReqDto;

import java.util.List;
import java.util.UUID;

public interface UserServ {
    List<UserDto> getAllUsers();
    UserDto getUserById(UUID id);
    UserDto createUser(UserReqDto userDto);
    UserDto updateUser(UUID id, UserReqDto userDto);
    Boolean deleteUser(UUID id);
    String resetPassword(UUID id);
}
