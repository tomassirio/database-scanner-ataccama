package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;

import java.io.IOException;

public interface ScannerService {

    String scanSchemas(DatabaseStructure databaseStructure) throws IOException;

    String scanTables(DatabaseStructure databaseStructure) throws IOException;

    String scanColumns(DatabaseStructure databaseStructure) throws IOException;

    String scanFields(DatabaseStructure databaseStructure) throws IOException;
}
