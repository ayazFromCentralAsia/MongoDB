package com.example.mongoDBCardMachine.entity.repository;

import com.example.mongoDBCardMachine.entity.Memory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemoryRepository extends MongoRepository<Memory, String> {
}
