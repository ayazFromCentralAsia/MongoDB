package com.example.mongoDBCardMachine.service;

import com.example.mongoDBCardMachine.dto.memory.Address;
import com.example.mongoDBCardMachine.dto.memory.MediaUrls;
import com.example.mongoDBCardMachine.dto.memory.MemoryRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MemoryValidator {
    public boolean isValid(MemoryRequest memoryRequest) {
        if (memoryRequest.getName().isEmpty()){
            return false;
        }
        if (memoryRequest.getTitle().isBlank()){
            return false;
        }
        if (memoryRequest.getMediaUrls().getImageUrl().isEmpty() &&
                memoryRequest.getMediaUrls().getVideoUrl().isEmpty() ) {
            return false;
        }
        if (memoryRequest.getAddress().getAddress() == null){
            return false;
        }
        return true;
    }
}
