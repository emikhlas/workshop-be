package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.dto.user.UserReqDto;
import ogya.workshop.performance_appraisal.service.UserServ;
import ogya.workshop.performance_appraisal.util.PageInfo;
import ogya.workshop.performance_appraisal.util.ServerResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServ userServ;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<UserDto>>> getAllUsers(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Log.info("Start getAllUsers in UserController");
        long startTime = System.currentTimeMillis();

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        Page<UserDto> content = userServ.getAllUsers(searchTerm,pageable);

        ManagerDto<List<UserDto>> response = new ManagerDto<>();
        response.setContent(content.getContent());
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(content.getNumber());
        pageInfo.setSize(content.getSize());
        pageInfo.setTotalElements(content.getTotalElements());
        pageInfo.setTotalPages(content.getTotalPages());
        pageInfo.setLast(content.isLast());
        pageInfo.setFirst(content.isFirst());
        response.setPageInfo(pageInfo);
        response.setTotalRows((int) content.getTotalElements());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));

        Log.info("End getAllUsers in UserController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ManagerDto<UserDto>>  saveUser(@RequestBody UserReqDto userDto) {
        Log.info("Start saveUser in UserController");
        long startTime = System.currentTimeMillis();

        ManagerDto<UserDto> response = new ManagerDto<>();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserDto content = userServ.createUser(userDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveUsers in UserController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ManagerDto<UserDto>>  getUserDetail(@PathVariable("id") UUID id) {
        Log.info("Start getUserDetail in UserController");
        long startTime = System.currentTimeMillis();

        ManagerDto<UserDto> response = new ManagerDto<>();
        UserDto content = userServ.getUserById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getUserDetail in UserController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<UserDto>>  updateUser(@PathVariable("id") UUID id, @RequestBody UserReqDto userDto) {
        Log.info("Start updateUser in UserController");
        long startTime = System.currentTimeMillis();

        ManagerDto<UserDto> response = new ManagerDto<>();
        if(userDto.getPassword() != null){
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        UserDto content = userServ.updateUser(id, userDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateUser in UserController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>>  deleteUser(@PathVariable("id") UUID id) {
        Log.info("Start deleteUser in UserController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = userServ.deleteUser(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteUser in UserController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PostMapping("/reset-password/{id}")
    public ResponseEntity<ManagerDto<String>>  resetPassword(@PathVariable("id") UUID id) {
        Log.info("Start resetPassword in UserController");
        long startTime = System.currentTimeMillis();

        ManagerDto<String> response = new ManagerDto<>();
        String content = userServ.resetPassword(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success reset password", executionTime));
        Log.info("End resetPassword in UserController");
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @GetMapping("/check-username")
    public ResponseEntity<Boolean>  checkUsername(
            @RequestParam("username") String username) {
        Log.info("Start checkUsername in UserController");
        Boolean usernameExist = userServ.isUsernameExist(username);
        return new ResponseEntity<>(usernameExist, HttpStatus.OK) ;
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean>  checkEmail(
            @RequestParam("email") String email) {
        Log.info("Start checkEmail in UserController");
        Boolean emailExist = userServ.isEmailExist(email);
        return new ResponseEntity<>(emailExist, HttpStatus.OK) ;
    }
}
