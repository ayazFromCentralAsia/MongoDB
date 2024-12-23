package com.example.mongoDBCardMachine.dto.authentication;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterResponse {
    String name;
    String text;
}
