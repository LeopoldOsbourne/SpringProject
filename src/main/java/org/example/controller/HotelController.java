package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.HotelResponse;
import org.example.dto.hotel.HotelRequestDto;
import org.example.dto.hotel.HotelResponseDto;
import org.example.dto.hotel.HotelResponseWithRatingDto;
import org.example.model.UpdateHotelRatingRequest;
import org.example.repository.HotelFilter;
import org.example.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public HotelResponse getAll(@RequestBody HotelFilter hotelFilter) {
        return hotelService.getAll(hotelFilter);
    }

    @GetMapping("/id")
    public HotelResponseDto findById(@PathVariable Long id) {
        return hotelService.findById(id);
    }

    @PostMapping
    public HotelResponseDto create(@RequestBody HotelRequestDto hotelDto) {
        return hotelService.create(hotelDto);
    }

    @PostMapping("/{hotelId}")
    public HotelResponseWithRatingDto updateHotelRating(
            @PathVariable Long hotelId, @Valid @RequestBody UpdateHotelRatingRequest ratingRequest ) {

        return hotelService.updateHotelRating(hotelId, ratingRequest.getMark(),  ratingRequest.getUserId());
    }

    @PutMapping("/{id}")
    public HotelResponseDto edit(@RequestBody HotelRequestDto hotelDto,  @PathVariable Long id) {
        return hotelService.edit(hotelDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

}
