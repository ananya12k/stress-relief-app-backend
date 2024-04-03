package com.example.gratitude_journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GratitudeServiceImpl implements GratitudeService {

    @Autowired
    private GratitudeRepository gratitudeRepository;

    @Override
    public List<Gratitude> getAllGratitudeEntries() {
        return gratitudeRepository.findAll();
    }

    @Override
    public Optional<Gratitude> getGratitudeEntryById(Long id) {
        return gratitudeRepository.findById(id);
    }

    @Override
    public Gratitude createGratitude(Gratitude gratitude) {
        return gratitudeRepository.save(gratitude);
    }

    @Override
    public void deleteGratitudeEntry(Long id) {
        gratitudeRepository.deleteById(id);
    }

    @Override
    public Gratitude updateGratitudeEntry(Long id, Gratitude updatedGratitude) {
        Optional<Gratitude> optionalGratitude = gratitudeRepository.findById(id);
        if (optionalGratitude.isPresent()) {
            Gratitude existingGratitude = optionalGratitude.get();
            existingGratitude.setContent(updatedGratitude.getContent());
            existingGratitude.setLastModifiedAt(updatedGratitude.getLastModifiedAt());
            return gratitudeRepository.save(existingGratitude);
        } else {
            throw new RuntimeException("Gratitude entry not found with id: " + id);
        }
    }
}
