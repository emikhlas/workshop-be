package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthService;
import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.config.security.jwt.JwtService;
import ogya.workshop.performance_appraisal.dto.auth.AuthRequest;
import ogya.workshop.performance_appraisal.dto.auth.AuthResponse;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.util.ServerResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
        AuthUser authenticatedUser = authService.authenticate(authRequest);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        AuthResponse authResponse = new AuthResponse();
//        authResponse.setUser(UserDto.fromAuthenticatedUser(authenticatedUser));
        authResponse.setToken(jwtToken);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
