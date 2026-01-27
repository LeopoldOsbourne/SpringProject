package org.example.dto.booking;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.model.Room;
import org.example.model.User;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequestDto {

    Long roomId;

    LocalDate arrivalDate;

    LocalDate departureDate;

}
