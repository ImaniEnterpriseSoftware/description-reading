package com.example.descriptionr.controller;

import com.example.descriptionr.model.Description;
import com.example.descriptionr.service.ReadingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
        return ResponseEntity.created(URI.create("/api/Descriptions/" + createdDescription.getId())).body(createdDescription);
    }

    // Update operation
    @PutMapping("/{id}")
    public ResponseEntity<Description> update(@PathVariable String id, @RequestBody Description model) {
        if (!readingService.get(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        model.setId(id);
        readingService.update(id, model);
        return ResponseEntity.noContent().build();
    }

    // Delete operation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!readingService.get(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        readingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
