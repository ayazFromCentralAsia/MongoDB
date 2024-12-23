package com.example.mongoDBCardMachine.api;


import com.example.mongoDBCardMachine.dto.authentication.LoginRequest;
import com.example.mongoDBCardMachine.dto.authentication.LoginResponse;
import com.example.mongoDBCardMachine.dto.authentication.RegisterRequest;
import com.example.mongoDBCardMachine.dto.authentication.RegisterResponse;
import com.example.mongoDBCardMachine.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
    
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
