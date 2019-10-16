package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;

public interface StatisticsService {
    String statisticsColumn(DatabaseStructure databaseStructure) throws Exception;

    String statisticsTable(DatabaseStructure databaseStructure) throws Exception;
}
