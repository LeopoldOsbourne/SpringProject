package org.example.repository;

import org.example.model.Hotel;
import org.example.model.HotelMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelMarkRepository extends JpaRepository<HotelMark, Long> {
    List<HotelMark> findByHotel(Hotel hotel);
}
