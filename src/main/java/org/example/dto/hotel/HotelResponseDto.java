package org.example.dto.hotel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelResponseDto {

    long id;

    String name;

    String title;

    String city;

    String address;

    Long distanceFromCityCenter;

}
