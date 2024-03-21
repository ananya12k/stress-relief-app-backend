package com.example.diarywrite;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("*")
@RestController
public class TextController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private ModelMapper modelMapper; // Assuming you are using ModelMapper for conversion

    @PostMapping("/api/insertText")
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
}
