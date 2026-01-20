package org.example.repository;

import org.example.model.UnavailableDates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnavailableDatesRepository extends JpaRepository<UnavailableDates, Long> {
}
