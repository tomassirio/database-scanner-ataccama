package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;

public interface ScannerService {

    String scanSchemas(DatabaseStructure databaseStructure) throws Exception;

    String scanTables(DatabaseStructure databaseStructure) throws Exception;

    String scanColumns(DatabaseStructure databaseStructure) throws Exception;

    String scanFields(DatabaseStructure databaseStructure) throws Exception;
}
