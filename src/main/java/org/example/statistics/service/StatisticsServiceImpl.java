package org.example.statistics.service;

import lombok.RequiredArgsConstructor;
import org.example.statistics.document.StatisticsDocument;
import org.example.statistics.event.StatisticsEventType;
import org.example.statistics.repository.StatisticsRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private final StatisticsRepository statisticsRepository;

    @Override
    public List<StatisticsDocument> getAllStatistics() {
        return statisticsRepository.findAllByOrderByTimestampAsc();
    }

    @Override
    public byte[] exportToCsv() {
        List<StatisticsDocument> documents = getAllStatistics();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream, true, StandardCharsets.UTF_8);

        writer.println("event_type,timestamp,user_id,arrival_date,departure_date");

        for (StatisticsDocument doc : documents) {
            String eventType = doc.getEventType() != null ? doc.getEventType().name() : "";
            String timestamp = doc.getTimestamp() != null ? doc.getTimestamp().toString() : "";
            String userId = doc.getUserId() != null ? String.valueOf(doc.getUserId()) : "";
            String arrivalDate = doc.getArrivalDate() != null ? doc.getArrivalDate().format(DATE_FORMATTER) : "";
            String departureDate = doc.getDepartureDate() != null ? doc.getDepartureDate().format(DATE_FORMATTER) : "";
            writer.printf("%s,%s,%s,%s,%s%n", eventType, timestamp, userId, arrivalDate, departureDate);
        }

        writer.flush();
        return outputStream.toByteArray();
    }
}
