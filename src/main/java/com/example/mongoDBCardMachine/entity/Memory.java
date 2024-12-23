package com.example.mongoDBCardMachine.entity;


import com.example.mongoDBCardMachine.dto.memory.MemoryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "memory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Memory {
    @Id
    private String id;
    private String userId;
    private String title;
    private String description;
    private List<String> imageUrl;
    private List<String> audioUrl;
    private List<String> videoUrl;
    private Location location;
    @CreatedDate
    private LocalDate createdAt;
}
