package org.example.dto.hotel;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelResponseWithRatingDto {

    long id;

    String name;

    String title;

    String city;

    String address;

    Long distanceFromCityCenter;

    Long rating;

    Long numberOfRatings;
}
