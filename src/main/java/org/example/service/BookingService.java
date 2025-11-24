package org.example.service;

import org.example.dto.booking.BookingRequestDto;
import org.example.dto.booking.BookingResponseDto;

import java.util.List;

public interface BookingService {

    List<BookingResponseDto> showAllBookings();

    BookingResponseDto createBooking(BookingRequestDto bookingDto);
}
