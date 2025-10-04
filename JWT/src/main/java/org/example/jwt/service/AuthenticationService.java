package org.example.jwt.service;

import org.example.jwt.model.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.jwt.entity.User;
import org.example.jwt.model.LoginRequest;
import org.example.jwt.model.RegisterRequest;
import org.example.jwt.repository.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public User register(RegisterRequest input) {
        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(user);
    }

    public LoginResponse authenticate(LoginRequest input) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
        User user = userRepository.findByEmail(input.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user);
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setExpiresIn(String.valueOf(jwtService.getExpirationTime()));
        return response;
    }
}