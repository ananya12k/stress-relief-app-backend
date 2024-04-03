package com.example.gratitude_journal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/gratitude")
public class GratitudeController {

    @Autowired
    private GratitudeService gratitudeService;

    @Autowired
    private ModelMapper modelMapper; // Assuming you are using ModelMapper for conversion

    @PostMapping("/newentry")
    public ResponseEntity<?> insertGratitude(@RequestBody GratitudeDTO gratitudeDTO) {
        try {
            Gratitude gratitude = modelMapper.map(gratitudeDTO, Gratitude.class); // Convert DTO to entity
            gratitudeService.createGratitude(gratitude); // Create gratitude entry using the service
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert gratitude entry");
        }
    }

    @GetMapping("/allentries")
    public ResponseEntity<?> viewAllGratitudeEntries() {
        try {
            // Logic to fetch all gratitude entries
            List<Gratitude> gratitudeEntries = gratitudeService.getAllGratitudeEntries(); // Assuming you have a method in GratitudeService to get all entries
            return ResponseEntity.ok(gratitudeEntries); // Return the list of gratitude entries
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch all gratitude entries");
        }
    }

    @GetMapping("/entry/{id}")
    public ResponseEntity<?> getGratitudeEntryById(@PathVariable Long id) {
        try {
            Gratitude gratitudeEntry = gratitudeService.getGratitudeEntryById(id).orElse(null);
            if (gratitudeEntry != null) {
                GratitudeDTO gratitudeDTO = modelMapper.map(gratitudeEntry, GratitudeDTO.class);
                return ResponseEntity.ok(gratitudeDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch gratitude entry by ID");
        }
    }

    @PutMapping("/entry/{id}")
    public ResponseEntity<?> updateGratitudeEntry(@PathVariable Long id, @RequestBody GratitudeDTO gratitudeDTO) {
        try {
            Gratitude existingGratitudeEntry = gratitudeService.getGratitudeEntryById(id)
                    .orElseThrow(() -> new RuntimeException("Gratitude entry not found with id: " + id));

            // Update existing gratitude entry with new data
            existingGratitudeEntry.setContent(gratitudeDTO.getContent());
            existingGratitudeEntry.setLastModifiedAt(gratitudeDTO.getLastModifiedAt());

            // Save updated gratitude entry
            gratitudeService.updateGratitudeEntry(id, existingGratitudeEntry);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update gratitude entry");
        }
    }

    @DeleteMapping("/entry/{id}")
    public ResponseEntity<?> deleteGratitudeEntry(@PathVariable Long id) {
        try {
            gratitudeService.deleteGratitudeEntry(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete gratitude entry");
        }
    }
}