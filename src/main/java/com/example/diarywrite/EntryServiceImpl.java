package com.example.diarywrite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Override
    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    @Override
    public Optional<Entry> getEntryById(Long id) {
        return entryRepository.findById(id);
    }

    @Override
    public Entry createEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    @Override
    public void deleteEntry(Long id) {
        entryRepository.deleteById(id);
    }

    @Override
    public Entry updateEntry(Long id, Entry updatedEntry) {
        Optional<Entry> optionalEntry = entryRepository.findById(id);
        if (optionalEntry.isPresent()) {
            Entry existingEntry = optionalEntry.get();
            existingEntry.setTitle(updatedEntry.getTitle());
            existingEntry.setContent(updatedEntry.getContent());
            existingEntry.setLastModifiedAt(updatedEntry.getLastModifiedAt());
            return entryRepository.save(existingEntry);
        } else {
            throw new RuntimeException("Entry not found with id: " + id);
        }
    }
}
