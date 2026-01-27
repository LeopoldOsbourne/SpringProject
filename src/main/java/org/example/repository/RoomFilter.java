package org.example.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data

public class RoomFilter {
    private Long id;
    private String name;
    private String description;
    private Integer number;
    private Long minPrice;
    private Long maxPrice;
    private Integer maxNumberOfGuests;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkIn;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkOut;

    private int pageSize;
    private int pageNumber;
    private Long hotelId;
}
