package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.booking.BookingRequestDto;
import org.example.dto.booking.BookingResponseDto;
import org.example.mapper.BookingMapper;
import org.example.model.Booking;
import org.example.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public void deleteBooking(long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Booking is not found"));
        bookingRepository.delete(booking);
    }
}
