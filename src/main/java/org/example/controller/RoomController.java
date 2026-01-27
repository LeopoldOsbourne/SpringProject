package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.HotelResponse;
import org.example.dto.RoomResponse;
import org.example.dto.room.RoomRequestDto;
import org.example.dto.room.RoomResponseDto;
import org.example.repository.HotelFilter;
import org.example.repository.RoomFilter;
import org.example.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public RoomResponse getAll(@RequestBody RoomFilter roomFilter) {
        return roomService.getAll(roomFilter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RoomResponseDto> create(@RequestBody RoomRequestDto roomDto) {
        return ResponseEntity.ok(roomService.create(roomDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDto> edit(@RequestBody RoomRequestDto roomDto, @PathVariable Long id) {
        return ResponseEntity.ok(roomService.edit(roomDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
