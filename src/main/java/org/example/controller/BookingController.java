package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.booking.BookingRequestDto;
import org.example.dto.booking.BookingResponseDto;
import org.example.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

   private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> showAllBookings() {
        return ResponseEntity.ok(bookingService.showAllBookings());
    }

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto bookingDto){
        return ResponseEntity.ok(bookingService.createBooking(bookingDto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable long id){
        bookingService.deleteBooking(id);
        return ResponseEntity.ok().build();
    }
}
