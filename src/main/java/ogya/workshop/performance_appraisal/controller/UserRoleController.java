package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.userrole.UserRoleDto;
import ogya.workshop.performance_appraisal.dto.userrole.UserRoleUpdateDto;
import ogya.workshop.performance_appraisal.service.RoleServ;
import ogya.workshop.performance_appraisal.service.UserRoleServ;
import ogya.workshop.performance_appraisal.util.ServerResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user-role")
public class UserRoleController extends ServerResponseList {

    private static final Logger Log = LoggerFactory.getLogger(UserRoleController.class);

    @Autowired
    private RoleServ roleServ;

    @Autowired
    private UserRoleServ userRoleServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<UserRoleDto>>> getUserWithRole() {
        Log.info("Start getUserRole in RoleController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<UserRoleDto>> response = new ManagerDto<>();
        List<UserRoleDto> content = roleServ.getUsersWithRole();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getUserRole in RoleController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{user-id}")
    public ResponseEntity<ManagerDto<List<UserRoleDto>>> getUserRoleByUserId(@PathVariable("user-id") UUID userId) {
        Log.info("Start getUserRoleByUserId in RoleController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<UserRoleDto>> response = new ManagerDto<>();
        List<UserRoleDto> content = userRoleServ.getUserRoleByUserId(userId);

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getUserRoleByUserId in RoleController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{user-id}")
    public ResponseEntity<ManagerDto<List<UserRoleDto>>> updateUserRole(@PathVariable("user-id") UUID userId, @RequestBody UserRoleUpdateDto updateDto) {
        Log.info("Start updateUserRole in UserRoleController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<UserRoleDto>> response = new ManagerDto<>();
        List<UserRoleDto> content = userRoleServ.updateUserRole(userId, updateDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateUserRole in UserRoleController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
