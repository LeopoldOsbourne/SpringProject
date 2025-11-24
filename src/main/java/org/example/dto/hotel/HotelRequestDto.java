package org.example.dto.hotel;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelRequestDto {

    String name;

    String adTitle;

    String city;

    String address;

    Long distanceFromCityCenter;

}
