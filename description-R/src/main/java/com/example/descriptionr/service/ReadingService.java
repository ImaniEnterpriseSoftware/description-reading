package com.example.descriptionr.service;

import com.example.descriptionr.model.Description;
import com.example.descriptionr.repository.ReadingRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadingService {

    private final ReadingRepo readingRepo;

    public ReadingService(ReadingRepo readingRepo) {
        this.readingRepo = readingRepo;
    }

    public Description create (Description description){
        return readingRepo.save(description);
    }

    public Optional<Description> get (String id){
        return readingRepo.findById(id);
    }

    public List<Description> getAll (){
        return readingRepo.findAll();
    }

    public void update (String id, Description description){
        description.setTitle(id);
        readingRepo.save(description);
    }

    public void delete (String id){
        readingRepo.deleteById(id);
    }
}
