package com.example.mongoDBCardMachine.dto.authentication;

import lombok.Getter;

@Getter
public class RegisterRequest {
    private String username;
    private String password;
}