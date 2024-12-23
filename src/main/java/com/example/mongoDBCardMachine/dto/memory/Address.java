package com.example.mongoDBCardMachine.dto.memory;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String address;
    private String city;
    private String name;
    private String latitude;
    private String longitude;
}
