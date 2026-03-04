package org.example.statistics.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import org.example.statistics.event.RoomBookingEvent;
import org.example.statistics.event.UserRegistrationEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers:localhost:9092}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, UserRegistrationEvent> userRegistrationProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig(), new StringSerializer(), new JsonSerializer<>());
    }

    @Bean
    public KafkaTemplate<String, UserRegistrationEvent> userRegistrationKafkaTemplate() {
        return new KafkaTemplate<>(userRegistrationProducerFactory());
    }

    @Bean
    public ProducerFactory<String, RoomBookingEvent> roomBookingProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig(), new StringSerializer(), new JsonSerializer<>());
    }

    @Bean
    public KafkaTemplate<String, RoomBookingEvent> roomBookingKafkaTemplate() {
        return new KafkaTemplate<>(roomBookingProducerFactory());
    }

    private Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }
}
