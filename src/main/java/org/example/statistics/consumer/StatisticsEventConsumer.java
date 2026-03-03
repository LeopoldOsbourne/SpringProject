package org.example.statistics.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.statistics.config.KafkaTopics;
import org.example.statistics.document.StatisticsDocument;
import org.example.statistics.event.RoomBookingEvent;
import org.example.statistics.event.StatisticsEventType;
import org.example.statistics.event.UserRegistrationEvent;
import org.example.statistics.repository.StatisticsRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class StatisticsEventConsumer {

    private final StatisticsRepository statisticsRepository;

    @KafkaListener(topics = KafkaTopics.USER_REGISTRATION, groupId = "statistics-group", containerFactory = "userRegistrationListenerFactory")
    public void consumeUserRegistration(UserRegistrationEvent event) {
        log.info("Received user registration event: userId={}", event.getUserId());
        StatisticsDocument document = StatisticsDocument.builder()
                .eventType(StatisticsEventType.USER_REGISTRATION)
                .timestamp(Instant.now())
                .userId(event.getUserId())
                .build();
        statisticsRepository.save(document);
    }

    @KafkaListener(topics = KafkaTopics.ROOM_BOOKING, groupId = "statistics-group", containerFactory = "roomBookingListenerFactory")
    public void consumeRoomBooking(RoomBookingEvent event) {
        log.info("Received room booking event: userId={}, {} - {}", event.getUserId(), event.getArrivalDate(), event.getDepartureDate());
        StatisticsDocument document = StatisticsDocument.builder()
                .eventType(StatisticsEventType.ROOM_BOOKING)
                .timestamp(Instant.now())
                .userId(event.getUserId())
                .arrivalDate(event.getArrivalDate())
                .departureDate(event.getDepartureDate())
                .build();
        statisticsRepository.save(document);
    }
}
