package org.example.repository;

import org.example.model.HotelMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface HotelMarkRepository extends JpaRepository<HotelMark, Long> {
}
