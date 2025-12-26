package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.hotel.HotelPageResponseDto;
import org.example.dto.hotel.HotelRequestDto;
import org.example.dto.hotel.HotelResponseDto;
import org.example.dto.hotel.HotelSpecification;
import org.example.model.Hotel;
import org.example.repository.HotelRepository;
import org.example.service.HotelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public List<HotelResponseDto> getAll() {
        return hotelService.getAll();
    }

    @GetMapping("/id")
    public HotelResponseDto findById(@PathVariable Long id) {
        return hotelService.findById(id);
    }

    @PostMapping
    public HotelResponseDto create(@RequestBody HotelRequestDto hotelDto) {
        return hotelService.create(hotelDto);
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
