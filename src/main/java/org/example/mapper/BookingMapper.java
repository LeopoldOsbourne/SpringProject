package org.example.mapper;

import org.example.dto.booking.BookingRequestDto;
import org.example.dto.booking.BookingResponseDto;
import org.example.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    Booking toBooking(BookingRequestDto bookingDto);

    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "user.id", target = "userId")
    BookingResponseDto toBookingResponseDto(Booking booking);

    List<BookingResponseDto> toBookingResponseList(List<Booking> bookings);
}
