package com.example.mongoDBCardMachine.util;


import com.example.mongoDBCardMachine.dto.memory.MemoryRequest;
import com.example.mongoDBCardMachine.entity.Location;
import com.example.mongoDBCardMachine.entity.Memory;
import com.example.mongoDBCardMachine.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemoryConverter {

    public final UserRepository userRepository;

    public Memory convertMemory(MemoryRequest memoryRequest, String username) {
        Memory memory = new Memory();
        memory.setUserId(userRepository.findByUsername(username).orElseThrow(()
                -> new RuntimeException("User not found")).getId());
        memory.setImageUrl(memoryRequest.getMediaUrls().getImageUrl());
        memory.setAudioUrl(memoryRequest.getMediaUrls().getAudioUrl());
        memory.setVideoUrl(memoryRequest.getMediaUrls().getVideoUrl());
        memory.setTitle(memoryRequest.getTitle());
        memory.setDescription(memoryRequest.getDescription());
        return memory;
    }
    public Location convertLocation(MemoryRequest memoryRequest, String username) {
        Location location = new Location();
        location.setCity(memoryRequest.getAddress().getCity());
        location.setCity(memoryRequest.getAddress().getCity());
        location.setName(memoryRequest.getAddress().getName());
        location.setAddress(memoryRequest.getAddress().getAddress());
        location.setLatitude(memoryRequest.getAddress().getLatitude());
        location.setLongitude(memoryRequest.getAddress().getLongitude());
        location.setUserId(userRepository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("User not found")).getId());
        return location;
    }
}
