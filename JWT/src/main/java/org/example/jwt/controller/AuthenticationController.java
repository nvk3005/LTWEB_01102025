package org.example.jwt.controller;

import jakarta.transaction.Transactional;
import org.example.jwt.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.jwt.model.LoginRequest;
import org.example.jwt.model.LoginResponse;
import org.example.jwt.model.RegisterRequest;
import org.example.jwt.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest register) {
        User registeredUser = authenticationService.register(register);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping(path = "/login")
    @Transactional
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest login) {
        LoginResponse loginResponse = authenticationService.authenticate(login);
        return ResponseEntity.ok(loginResponse);
    }
}