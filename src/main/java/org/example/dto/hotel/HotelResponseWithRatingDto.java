package org.example.dto.hotel;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelResponseWithRatingDto {

    long id;

    String name;

    String adTitle;

    String city;

    String address;

    Long distanceFromCityCenter;

    Long rating;

    Long numberOfRatings;
}
