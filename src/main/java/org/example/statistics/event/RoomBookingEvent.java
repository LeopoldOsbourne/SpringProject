package org.example.statistics.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomBookingEvent {
    private long userId;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
}
