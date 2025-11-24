package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.booking.BookingRequestDto;
import org.example.dto.booking.BookingResponseDto;
import org.example.mapper.BookingMapper;
import org.example.model.Booking;
import org.example.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    public List<BookingResponseDto> showAllBookings() {
        return bookingMapper.toBookingResponseList(bookingRepository.findAll());
    }

    @Override
    public BookingResponseDto createBooking(BookingRequestDto bookingDto) {
        Booking booking = bookingMapper.toBooking(bookingDto);
        return bookingMapper.toBookingResponseDto(bookingRepository.save(booking));
    }
}
