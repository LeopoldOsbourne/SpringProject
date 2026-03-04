package org.example.statistics.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.statistics.event.StatisticsEventType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "statistics")
public class StatisticsDocument {
    @Id
    private String id;

    private StatisticsEventType eventType;
    private Instant timestamp;

    private Long userId;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
}
