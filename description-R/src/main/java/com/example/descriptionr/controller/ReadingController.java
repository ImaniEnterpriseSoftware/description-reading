package com.example.descriptionr.controller;

import com.example.descriptionr.model.Description;
import com.example.descriptionr.service.ReadingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/descriptions")
public class ReadingController {

    private final ReadingService readingService;

    public ReadingController (ReadingService readingService){
        this.readingService = readingService;
    }

    @GetMapping
    public List<Description> getAll() {
        return readingService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Description> get(@PathVariable String id) {
        Optional<Description> model = readingService.get(id);
        return model.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Create operation
    @PostMapping
    public ResponseEntity<Description> create(@RequestBody Description description) {
        Description createdDescription = readingService.create(description);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDescription);
    }

    @PutMapping("/title/{title}")
    public Description test (@PathVariable("title") String title,
                             @RequestBody Description newDescription){
        Description description = readingService.findByTitle(title);

        description.setCollection_id(newDescription.getCollection_id());
        description.setDetails(newDescription.getDetails());
        description.setUser_id(newDescription.getUser_id());
        description.setTitle(newDescription.getTitle());

        readingService.update(title, description);
        return description;
    }

    @DeleteMapping
    public String delete(String title) {
        Description description = readingService.findByTitle(title);
        if (description == null) {
            return "That did not work";
        }

        readingService.delete(String.valueOf(description));
        return "yay";
    }
}
