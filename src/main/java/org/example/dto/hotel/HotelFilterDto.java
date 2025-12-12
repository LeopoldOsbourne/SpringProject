package org.example.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class HotelFilterDto {

    private Long id;
    private String name;
    private String adTitle;
    private String city;
    private String address;
    private Double distanceToCenter;
    private Double rating;
    private Integer numberOfRating;
    private Pageable pageable;

}
