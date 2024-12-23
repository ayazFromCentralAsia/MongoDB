package com.example.mongoDBCardMachine.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
public record SimpleResponse(
        HttpStatus status,
        String message){
}