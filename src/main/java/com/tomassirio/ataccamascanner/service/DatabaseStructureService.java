package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import com.tomassirio.ataccamascanner.model.InstanceInfo;
import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;

import javax.sql.DataSource;
import java.sql.SQLException;

public interface DatabaseStructureService {

    DataSource getDataSource(InstanceInfo instanceInfo);

    DatabaseStructure getDatabaseStructure(String instanceName) throws SQLException;
}
