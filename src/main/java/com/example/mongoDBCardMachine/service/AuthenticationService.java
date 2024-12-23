package com.example.mongoDBCardMachine.service;


import com.example.mongoDBCardMachine.config.JwtService;
import com.example.mongoDBCardMachine.dto.authentication.LoginRequest;
import com.example.mongoDBCardMachine.dto.authentication.LoginResponse;
import com.example.mongoDBCardMachine.dto.authentication.RegisterRequest;
import com.example.mongoDBCardMachine.dto.authentication.RegisterResponse;

import com.example.mongoDBCardMachine.entity.User;
import com.example.mongoDBCardMachine.entity.enums.Role;
import com.example.mongoDBCardMachine.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    public final JwtService jwtService;
    public final PasswordEncoder passwordEncoder;
    public final UserRepository userRepository;

    public RegisterResponse register(RegisterRequest registerRequest){
        if (registerRequest.getUsername() == null || registerRequest.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        try {
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            user.setPhoto("");
            user.setRole(Role.USER);
            user.setCreatedDate(LocalDateTime.now());
            userRepository.save(user);
            return RegisterResponse.builder()
                    .name(user.getUsername())
                    .text("User registered successfully")
                    .build();
        } catch (Exception e) {
            throw new IllegalArgumentException("Authentication.register(), Error while registering user");
        }
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new RuntimeException("Authentication.login(), User not found"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Authentication.login(), Incorrect password");
        }
        String loggedInUser = userRepository.findByUsername(loginRequest.getUsername()).get().getUsername();
        String token = jwtService.generateToken(loggedInUser);
        return LoginResponse.builder()
                .text("User logged in successfully")
               .username(user.getUsername())
               .token(token)
               .build();
    }
}
