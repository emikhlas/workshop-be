package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthService;
import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.config.security.jwt.JwtService;
import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.auth.AuthRequest;
import ogya.workshop.performance_appraisal.dto.auth.AuthResponse;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.util.ServerResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ManagerDto<AuthResponse>> authenticate(@RequestBody AuthRequest authRequest) {
        Log.info("Start authenticate in AuthController");
        long startTime = System.currentTimeMillis();


        AuthUser authenticatedUser = authService.authenticate(authRequest);

        ManagerDto<AuthResponse> response = new ManagerDto<>();

        String jwtToken = jwtService.generateToken(authenticatedUser);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUser(UserInfoDto.fromAuthenticatedUser(authenticatedUser));
        authResponse.setToken(jwtToken);

        response.setContent(authResponse);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success login", executionTime));
        Log.info("End authenticate in AuthController");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
