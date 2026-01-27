package org.example.dto;

import lombok.Data;
import org.example.dto.hotel.HotelResponseDto;
import org.example.dto.room.RoomResponseDto;

import java.util.List;

@Data
public class RoomResponse {
    private List<RoomResponseDto> rooms;

    private int pageNumber;
    private int pageSize;
    private int pageElements;
}
