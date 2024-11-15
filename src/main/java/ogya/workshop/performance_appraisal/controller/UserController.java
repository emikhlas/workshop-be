package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.dto.user.UserReqDto;
import ogya.workshop.performance_appraisal.service.UserServ;
import ogya.workshop.performance_appraisal.util.ServerResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServ userServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<UserDto>>>  getAllUsers() {
        Log.info("Start getAllUsers in UserController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<UserDto>> response = new ManagerDto<>();
        List<UserDto> content = userServ.getAllUsers();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllUsers in UserController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<UserDto>>  saveUser(@RequestBody UserReqDto userDto) {
        Log.info("Start saveUser in UserController");
        long startTime = System.currentTimeMillis();

        ManagerDto<UserDto> response = new ManagerDto<>();
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
}
