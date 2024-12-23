package com.example.mongoDBCardMachine.entity.repository;

import com.example.mongoDBCardMachine.entity.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {
}