package org.example.dto.booking;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.model.Room;
import org.example.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponseDto {

    long id;

    LocalDateTime arrivalDate;

    LocalDateTime departureDate;

    long roomId;
    long userId;

}
