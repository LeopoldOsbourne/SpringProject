package org.example.dto.room;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.model.Hotel;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomResponseDto {

    long id;

    String name;

    String description;

    int number;

    Long price;

    int maxNumberOfGuests;

    LocalDate unavailableDates;

}
