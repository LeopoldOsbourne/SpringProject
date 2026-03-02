package org.example.statistics.service;

import org.example.statistics.document.StatisticsDocument;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface StatisticsService {

    List<StatisticsDocument> getAllStatistics();

    byte[] exportToCsv();
}
