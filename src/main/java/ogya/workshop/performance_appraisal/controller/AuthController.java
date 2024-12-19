package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthService;
import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.config.security.jwt.JwtService;
import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.auth.AuthRequestDto;
import ogya.workshop.performance_appraisal.dto.auth.AuthResponseDto;
import ogya.workshop.performance_appraisal.dto.auth.ChangePasswordDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.util.ServerResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ManagerDto<AuthResponseDto>> authenticate(@RequestBody AuthRequestDto authRequestDto) {
        Log.info("Start authenticate in AuthController");
        long startTime = System.currentTimeMillis();

        AuthUser authenticatedUser = authService.authenticate(authRequestDto);

        ManagerDto<AuthResponseDto> response = new ManagerDto<>();

        String jwtToken = jwtService.generateToken(authenticatedUser);

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setUser(UserInfoDto.fromAuthenticatedUser(authenticatedUser));
        authResponseDto.setToken(jwtToken);

        response.setContent(authResponseDto);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success login", executionTime));
        Log.info("End authenticate in AuthController");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/change-password/{id}")
    public ResponseEntity<ManagerDto<AuthResponseDto>> changePassword(@PathVariable("id") UUID id, @RequestBody ChangePasswordDto changePasswordDto) {
        Log.info("Start changePassword in AuthController");
        long startTime = System.currentTimeMillis();
        AuthUser updatedUser = authService.changePassword(id, changePasswordDto);
        String jwtToken = jwtService.generateToken(updatedUser);
        ManagerDto<AuthResponseDto> response = new ManagerDto<>();
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setUser(UserInfoDto.fromAuthenticatedUser(updatedUser));
        authResponseDto.setToken(jwtToken);
        response.setContent(authResponseDto);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success change password", executionTime));
        Log.info("End changePassword in AuthController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
