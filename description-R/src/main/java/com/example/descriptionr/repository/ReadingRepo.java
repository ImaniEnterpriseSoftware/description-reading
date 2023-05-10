package com.example.descriptionr.repository;

import com.example.descriptionr.model.Description;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepo extends MongoRepository<Description, String> {
}
