package org.example.service;

import org.example.dto.HotelResponse;
import org.example.dto.hotel.*;
import org.example.repository.HotelFilter;
import org.springframework.data.domain.Page;


import java.awt.print.Pageable;
import java.util.List;

public interface HotelService {

    HotelResponse getAll(HotelFilter hotelFilter);

    HotelResponseDto findById(Long id);

    HotelResponseDto create(HotelRequestDto hotelDto);

    HotelResponseDto edit(HotelRequestDto hotelDto, Long id);

   // HotelResponseWithRatingDto updateHotelRating(Long id, int newRating);

    //Page<HotelPageResponseDto<?>> findAllWithFilters(HotelFilterDto hotelFilterDto);

    void deleteHotel(Long id);
}
