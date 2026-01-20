package org.example.dto.room;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomResponseDto {

    long id;

    String name;

    String description;

    int number;

    Long price;

    int maxNumberOfGuests;

    List<LocalDate> unavailableDates;

}
