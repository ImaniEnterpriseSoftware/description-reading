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
        return readingRepo.findByTitle(title);
    }

    public Description updateDescription(String id, Description updatedDescription) {
        Description descriptionToUpdate = readingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Description not found with id " + id));

        descriptionToUpdate.setTitle(updatedDescription.getTitle());
        descriptionToUpdate.setDetails(updatedDescription.getDetails());
        descriptionToUpdate.setUser_id(updatedDescription.getUser_id());
        descriptionToUpdate.setCollection_id(updatedDescription.getCollection_id());

        return readingRepo.save(descriptionToUpdate);
    }

    public Description update (String title, Description description){
        Description descriptionToUpdate = findByTitle(title);

        descriptionToUpdate.setTitle(description.getTitle());
        descriptionToUpdate.setDetails(description.getDetails());
        descriptionToUpdate.setUser_id(description.getUser_id());
        descriptionToUpdate.setCollection_id(description.getCollection_id());

        readingRepo.save(descriptionToUpdate);
        return descriptionToUpdate;
    }

    public void delete (String id){
        readingRepo.deleteById(id);
    }
}
