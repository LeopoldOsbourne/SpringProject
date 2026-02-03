package org.example.service;

import org.example.dto.HotelResponse;
import org.example.dto.hotel.*;
import org.example.repository.HotelFilter;

public interface HotelService {

    HotelResponse getAll(HotelFilter hotelFilter);

    HotelResponseDto findById(Long id);

    HotelResponseDto create(HotelRequestDto hotelDto);

    HotelResponseDto edit(HotelRequestDto hotelDto, Long id);

    HotelResponseWithRatingDto updateHotelRating(Long id, int newRating, Long userId);

    void deleteHotel(Long id);
}
