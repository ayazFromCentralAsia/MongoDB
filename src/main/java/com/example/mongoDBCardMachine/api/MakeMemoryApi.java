package com.example.mongoDBCardMachine.api;


import com.example.mongoDBCardMachine.dto.SimpleResponse;
import com.example.mongoDBCardMachine.dto.memory.MemoryRequest;
import com.example.mongoDBCardMachine.service.MakeMemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/makeMemory")
@RequiredArgsConstructor
public class MakeMemoryApi {
    private final MakeMemoryService makeMemoryService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public SimpleResponse create(@RequestBody MemoryRequest memoryRequest) {
        return makeMemoryService.makeMemory(memoryRequest);
    }
}
