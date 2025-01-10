package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.role.RoleDto;
import ogya.workshop.performance_appraisal.dto.role.RoleReqDto;
import ogya.workshop.performance_appraisal.service.RoleServ;
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
@RequestMapping("/role")
public class RoleController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleServ roleServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<RoleDto>>> getAllRoles() {
        Log.info("Start getAllRoles in RoleController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<RoleDto>> response = new ManagerDto<>();
        List<RoleDto> content = roleServ.getAllRoles();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllRoles in RoleController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ManagerDto<RoleDto>> saveRole(@RequestBody RoleReqDto roleDto) {
        Log.info("Start saveRole in RoleController");
        long startTime = System.currentTimeMillis();

        ManagerDto<RoleDto> response = new ManagerDto<>();
        RoleDto content = roleServ.createRole(roleDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveRole in RoleController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ManagerDto<RoleDto>> getRoleDetail(@PathVariable("id") UUID id) {
        Log.info("Start getRoleDetail in RoleController");
        long startTime = System.currentTimeMillis();

        ManagerDto<RoleDto> response = new ManagerDto<>();
        RoleDto content = roleServ.getRoleById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getRoleDetail in RoleController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<RoleDto>> updateRole(@PathVariable("id") UUID id, @RequestBody RoleReqDto roleDto) {
        Log.info("Start updateRole in RoleController");
        long startTime = System.currentTimeMillis();

        ManagerDto<RoleDto> response = new ManagerDto<>();
        RoleDto content = roleServ.updateRole(id, roleDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateRole in RoleController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteRole(@PathVariable("id") UUID id) {
        Log.info("Start deleteRole in RoleController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        response.setContent(roleServ.deleteRole(id));
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteRole in RoleController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
