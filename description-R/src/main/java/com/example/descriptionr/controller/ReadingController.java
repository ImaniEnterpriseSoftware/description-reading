package com.example.descriptionr.controller;

import com.example.descriptionr.model.Description;
import com.example.descriptionr.repository.ReadingRepo;
import com.example.descriptionr.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String update (@PathVariable("title") String title,
                             @RequestBody Description newDescription){
        readingService.update(title, newDescription);
        return String.format("Description with title: %s was updated", title);
    }

    @DeleteMapping("/title/{title}")
    public String delete(@PathVariable("title") String title) {

        readingService.deleteByTitle(title);
        return String.format("Description with title: %s was deleted", title);
    }
}
