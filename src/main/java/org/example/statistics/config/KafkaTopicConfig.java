package org.example.statistics.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic userRegistrationTopic() {
        return TopicBuilder.name(KafkaTopics.USER_REGISTRATION)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic roomBookingTopic() {
        return TopicBuilder.name(KafkaTopics.ROOM_BOOKING)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
