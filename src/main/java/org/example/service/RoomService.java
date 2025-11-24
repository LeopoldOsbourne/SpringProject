package org.example.service;

import org.example.dto.room.RoomRequestDto;
import org.example.dto.room.RoomResponseDto;

public interface RoomService {

    RoomResponseDto findById(Long id);

    RoomResponseDto create(RoomRequestDto roomDto);

    RoomResponseDto edit(RoomRequestDto roomDto, Long id);

    void delete(Long id);
}
