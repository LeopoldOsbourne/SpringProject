package org.example.repository;

import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
public class HotelFilter {
    private Long id;
    private String name;
    private String title;
    private String city;
    private String address;
    private Double distanceToCenter;
    private Double rating;
    private Integer numberOfRating;
}
