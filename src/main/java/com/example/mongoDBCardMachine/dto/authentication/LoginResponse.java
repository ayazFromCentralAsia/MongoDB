package com.example.mongoDBCardMachine.dto.authentication;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginResponse {
    String username;
    String text;
    String token;
}
