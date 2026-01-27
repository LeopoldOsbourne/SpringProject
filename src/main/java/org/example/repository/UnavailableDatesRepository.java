package org.example.repository;

import org.example.model.UnavailableDates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UnavailableDatesRepository extends JpaRepository<UnavailableDates, Long> {
    List<UnavailableDates> findByRoomIdAndUnavailableDateBetween(Long roomId, LocalDate unavailableDateAfter, LocalDate unavailableDateBefore); //TODO выяснить включительно эти даты будет поиск или нет
}
