package com.example.mongoDBCardMachine.dto.memory;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MediaUrls {
    private List<String> imageUrl;
    private List<String> audioUrl;
    private List<String> videoUrl;
}
