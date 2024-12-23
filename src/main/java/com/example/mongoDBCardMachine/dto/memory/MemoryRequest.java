package com.example.mongoDBCardMachine.dto.memory;

import com.example.mongoDBCardMachine.entity.Location;
import com.example.mongoDBCardMachine.entity.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
public class MemoryRequest {
    private String title;
    private String description;
    private MediaUrls mediaUrls;
    private String name;
    private Address address;
}
