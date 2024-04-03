package com.example.diarywrite;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/diary")
public class TextController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private ModelMapper modelMapper; // Assuming you are using ModelMapper for conversion

    @PostMapping("/newentry")
    public ResponseEntity<?> insertText(@RequestBody EntryDTO entryDTO) {
        try {
            Entry entry = modelMapper.map(entryDTO, Entry.class); // Convert DTO to entity
            entryService.createEntry(entry); // Create entry using the service
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert text");
        }
    }

    @GetMapping("/allentries")
    public ResponseEntity<?> viewAllEntries() {
        try {
            // Logic to fetch all entries
            List<Entry> entries = entryService.getAllEntries(); // Assuming you have a method in EntryService to get all entries
            return ResponseEntity.ok(entries); // Return the list of entries
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch all entries");
        }
    }

    @GetMapping("/entry/{id}")
    public ResponseEntity<?> getEntryById(@PathVariable Long id) {
        try {
            Entry entry = entryService.getEntryById(id).orElse(null);
            if (entry != null) {
                EntryDTO entryDTO = modelMapper.map(entry, EntryDTO.class);
                return ResponseEntity.ok(entryDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch entry by ID");
        }
    }

    @PutMapping("/entry/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable Long id, @RequestBody EntryDTO entryDTO) {
        try {
            Entry existingEntry = entryService.getEntryById(id)
                    .orElseThrow(() -> new RuntimeException("Entry not found with id: " + id));

            // Update existing entry with new data
            existingEntry.setTitle(entryDTO.getTitle());
            existingEntry.setContent(entryDTO.getContent());
            existingEntry.setLastModifiedAt(entryDTO.getLastModifiedAt());

            // Save updated entry
            entryService.updateEntry(id, existingEntry);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update entry");
        }
    }

    @DeleteMapping("/entry/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable Long id) {
        try {
            entryService.deleteEntry(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete entry");
        }
    }
}