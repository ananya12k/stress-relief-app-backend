package com.example.diarywrite;

import java.util.List;
import java.util.Optional;

public interface EntryService {

    List<Entry> getAllEntries();

    Optional<Entry> getEntryById(Long id);

    Entry createEntry(Entry entry);

    void deleteEntry(Long id);

    Entry updateEntry(Long id, Entry updatedEntry);
}