package org.example.service;

import org.example.dto.hotel.HotelPageResponseDto;
import org.example.dto.hotel.HotelRequestDto;
import org.example.dto.hotel.HotelResponseDto;
import org.example.dto.hotel.HotelResponseWithRatingDto;
import org.springframework.data.domain.Page;


import java.awt.print.Pageable;
import java.util.List;

public interface HotelService {

    List<HotelResponseDto> getAll();

    HotelResponseDto findById(Long id);

    HotelResponseDto create(HotelRequestDto hotelDto);

    HotelResponseDto edit(HotelRequestDto hotelDto, Long id);

    HotelResponseWithRatingDto updateHotelRating(Long id, int newRating);

    Page<HotelPageResponseDto<?>> findAllWithFilters(
            Long id, String name, String adTitle, String city, String address,
            Double distanceToCenter, Double rating, Integer numberOfRating,
            Pageable pageable);

    void deleteHotel(Long id);
}
