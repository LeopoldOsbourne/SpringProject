package org.example.statistics.repository;

import org.example.statistics.document.StatisticsDocument;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StatisticsRepository extends MongoRepository<StatisticsDocument, String> {

    default List<StatisticsDocument> findAllByOrderByTimestampAsc() {
        return findAll(Sort.by("timestamp").ascending());
    }
}
