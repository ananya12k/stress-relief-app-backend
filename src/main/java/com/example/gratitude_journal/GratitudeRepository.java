package com.example.gratitude_journal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GratitudeRepository extends JpaRepository<Gratitude, Long> {
}
