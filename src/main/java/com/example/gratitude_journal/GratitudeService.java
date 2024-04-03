package com.example.gratitude_journal;


import java.util.List;
import java.util.Optional;

public interface GratitudeService {

    List<Gratitude> getAllGratitudeEntries();

    Optional<Gratitude> getGratitudeEntryById(Long id);

    Gratitude createGratitude(Gratitude gratitude);

    void deleteGratitudeEntry(Long id);

    Gratitude updateGratitudeEntry(Long id, Gratitude updatedGratitude);
}
