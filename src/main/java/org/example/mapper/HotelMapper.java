package org.example.mapper;

import org.example.dto.hotel.HotelRequestDto;
import org.example.dto.hotel.HotelResponseDto;
import org.example.dto.hotel.HotelResponseWithRatingDto;
import org.example.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HotelMapper {

    @Mapping(target = "id", ignore = true)
    Hotel toHotel(HotelRequestDto hotelDto);

    HotelResponseDto toHotelResponseDto(Hotel hotel);

    HotelResponseWithRatingDto toHotelResponseWithRatingDto(Hotel hotel);

    List<HotelResponseWithRatingDto> toHotelResponseWithRatingList(List<Hotel> hotels);

    List<HotelResponseDto> toHotelResponseList(List<Hotel> hotels);
}
