package org.example.dto.room;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.model.Hotel;

import java.time.Instant;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomRequestDto {

    String name;

    String description;

    int number;

    Long price;

    int maxNumberOfGuests;

    LocalDate unavailableDates;

}
