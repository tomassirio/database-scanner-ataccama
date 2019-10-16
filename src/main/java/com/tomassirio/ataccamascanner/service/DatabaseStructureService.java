package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import com.tomassirio.ataccamascanner.model.InstanceInfo;
import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;

public interface DatabaseStructureService {

    DataSource getDataSource(InstanceInfo instanceInfo);

    DatabaseStructure getColumnsStructure(String instanceName) throws SQLException;

    DatabaseStructure getSchemasStructure(String instanceName) throws SQLException;

    DatabaseStructure getTablesStructure(String instanceName) throws  SQLException;
}
