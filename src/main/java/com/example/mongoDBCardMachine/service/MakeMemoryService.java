package com.example.mongoDBCardMachine.service;



import com.example.mongoDBCardMachine.dto.SimpleResponse;
import com.example.mongoDBCardMachine.dto.memory.MemoryRequest;
import com.example.mongoDBCardMachine.entity.Location;
import com.example.mongoDBCardMachine.entity.Memory;
import com.example.mongoDBCardMachine.entity.repository.LocationRepository;
import com.example.mongoDBCardMachine.entity.repository.MemoryRepository;
import com.example.mongoDBCardMachine.util.MemoryConverter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MakeMemoryService {
    private final LocationRepository locationRepository;
    private final MemoryRepository memoryRepository;
    private final MemoryValidator memoryValidator;
    private final MemoryConverter memoryConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(MakeMemoryService.class);

    public SimpleResponse makeMemory(MemoryRequest memoryRequest) {
        if (!memoryValidator.isValid(memoryRequest)){
            return SimpleResponse.builder().message("Invalid memory request").status(HttpStatus.BAD_REQUEST).build();
        }
        LOGGER.info("Creating memory for user: " + SecurityContextHolder.getContext().getAuthentication().getName());
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Memory memory = memoryConverter.convertMemory(memoryRequest, username);
            Location location = memoryConverter.convertLocation(memoryRequest, username);
            memory.setLocation(location);
            memoryRepository.save(memory);
            locationRepository.save(location);
        } catch (Exception e) {
            LOGGER.error("Error creating memory for user: " + SecurityContextHolder.getContext().getAuthentication().getName());
            return SimpleResponse.builder().message("Error creating memory").status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        LOGGER.info("Memory created successfully for user: " + SecurityContextHolder.getContext().getAuthentication().getName());
        return SimpleResponse.builder().message("Memory created successfully")
                .status(HttpStatus.OK).build();
    }
}
