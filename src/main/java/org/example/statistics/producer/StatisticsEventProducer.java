package org.example.statistics.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.statistics.config.KafkaTopics;
import org.example.statistics.event.RoomBookingEvent;
import org.example.statistics.event.UserRegistrationEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StatisticsEventProducer {

    private final KafkaTemplate<String, UserRegistrationEvent> userRegistrationKafkaTemplate;
    private final KafkaTemplate<String, RoomBookingEvent> roomBookingKafkaTemplate;

    public void sendUserRegistrationEvent(long userId) {
        UserRegistrationEvent event = new UserRegistrationEvent(userId);
        userRegistrationKafkaTemplate.send(KafkaTopics.USER_REGISTRATION, String.valueOf(userId), event);
        log.info("Sent user registration event for userId={}", userId);
    }

    public void sendRoomBookingEvent(long userId, java.time.LocalDate arrivalDate, java.time.LocalDate departureDate) {
        RoomBookingEvent event = new RoomBookingEvent(userId, arrivalDate, departureDate);
        roomBookingKafkaTemplate.send(KafkaTopics.ROOM_BOOKING, String.valueOf(userId), event);
        log.info("Sent room booking event for userId={}, dates: {} - {}", userId, arrivalDate, departureDate);
    }
}
