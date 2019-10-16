package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.exceptions.InstanceNotFoundException;
import com.tomassirio.ataccamascanner.model.InstanceInfo;
import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;

import javax.sql.DataSource;
import java.sql.SQLException;

public interface DatabaseStructureService {

    DataSource getDataSource(InstanceInfo instanceInfo, Boolean allDatabases);

    DatabaseStructure getDatabaseStructure(Long id, String structure, Boolean allDatabases) throws SQLException, InstanceNotFoundException;

}
