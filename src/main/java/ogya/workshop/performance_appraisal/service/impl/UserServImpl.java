package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.UserDto;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.UserRepo;
import ogya.workshop.performance_appraisal.service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServImpl implements UserServ {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> response = userRepo.findAll();
        List<UserDto> userList = new ArrayList<>();

        for (User user : response) {
            UserDto userDto = UserDto.fromEntity(user);
            userList.add(userDto);
        }

        return userList;
    }

    @Override
    public UserDto getUserById(String id) {
        return null;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        System.out.println(userDto);
        User user = UserDto.toEntity(userDto);
        System.out.println(user);
        userRepo.save(user);
        return UserDto.fromEntity(user);
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        return null;
    }

    @Override
    public Boolean deleteUser(String id) {
        return null;
    }
}
