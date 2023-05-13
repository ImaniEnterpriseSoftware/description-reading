package com.example.descriptionr.service;

import com.example.descriptionr.config.ResourceNotFoundException;
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

    public Description findByTitle(String title) {
        Description description = readingRepo.findByTitle(title);
        return description;
    }

    public String update (String title, Description description){
        Description testDescription = readingRepo.findByTitle(title);
        String id = testDescription.get_id();

        Description descriptionToUpdate = readingRepo.findById(id).get();

        if (descriptionToUpdate != null) {
            descriptionToUpdate.setTitle(description.getTitle());
            descriptionToUpdate.setDetails(description.getDetails());
            descriptionToUpdate.setUser_id(description.getUser_id());
            descriptionToUpdate.setCollection_id(description.getCollection_id());

            readingRepo.save(descriptionToUpdate);

            return "Saved";
        }
        return "Description does not exist";
    }

    public String deleteByTitle (String title){
        Description description = readingRepo.findByTitle(title);
        String id = description.get_id();

        if(id != null){
            readingRepo.deleteById(id);
            return "Description is deleted";
        }
        return "Description does not exist";
    }
}
