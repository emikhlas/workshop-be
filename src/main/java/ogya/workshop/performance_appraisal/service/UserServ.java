package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.UserDto;

import java.util.List;

public interface UserServ {
    List<UserDto> getAllUsers();
    UserDto getUserById(String id);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(String id, UserDto userDto);
    Boolean deleteUser(String id);
}
