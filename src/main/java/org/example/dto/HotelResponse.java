package org.example.dto;


import lombok.Data;
import org.example.dto.hotel.HotelResponseDto;

import java.util.List;

@Data
public class HotelResponse {
    private List<HotelResponseDto> hotels;

    private int pageNumber;
    private int pageSize;
    private int pageElements;
}
