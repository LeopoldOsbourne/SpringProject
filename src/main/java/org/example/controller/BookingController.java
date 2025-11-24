package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.booking.BookingRequestDto;
import org.example.dto.booking.BookingResponseDto;
import org.example.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> showAllBookings() {
        return ResponseEntity.ok(bookingService.showAllBookings());
    }

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(BookingRequestDto bookingDto){
        return ResponseEntity.ok(bookingService.createBooking(bookingDto));
    }
}
