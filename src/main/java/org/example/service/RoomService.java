package org.example.service;

import org.example.dto.RoomResponse;
import org.example.dto.room.RoomRequestDto;
import org.example.dto.room.RoomResponseDto;
import org.example.repository.RoomFilter;

public interface RoomService {

    RoomResponse getAll(RoomFilter roomFilter);

    RoomResponseDto findById(Long id);

    RoomResponseDto create(RoomRequestDto roomDto);

    RoomResponseDto edit(RoomRequestDto roomDto, Long id);

    void delete(Long id);
}
