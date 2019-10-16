package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;

import java.io.IOException;

public interface StatisticsService {
    String statisticsColumn(DatabaseStructure databaseStructure) throws IOException;

    String statisticsTable(DatabaseStructure databaseStructure) throws IOException;
}
