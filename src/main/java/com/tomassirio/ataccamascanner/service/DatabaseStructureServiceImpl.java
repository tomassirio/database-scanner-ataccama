package com.tomassirio.ataccamascanner.service;


import com.tomassirio.ataccamascanner.config.CommonConfiguration;
import com.tomassirio.ataccamascanner.model.InstanceInfo;
import com.tomassirio.ataccamascanner.model.database.Column;
import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;
import com.tomassirio.ataccamascanner.model.database.Schema;
import com.tomassirio.ataccamascanner.model.database.Table;
import com.tomassirio.ataccamascanner.repository.InstanceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.util.Objects;

@Service
public class DatabaseStructureServiceImpl implements DatabaseStructureService {

    private static final String MYSQL_JDBC = "jdbc:mysql";

    @Autowired
    private InstanceInfoRepository instanceInfoRepository;

    @Autowired
    private CommonConfiguration commonConfiguration;

    @Override
    public DataSource getDataSource(InstanceInfo instanceInfo) {
        return DataSourceBuilder.create()
                .url(MYSQL_JDBC + "://" + instanceInfo.getHost() + ":" + instanceInfo.getPort())
                .username(instanceInfo.getUser())
                .password(instanceInfo.getPassword()).build();
    }

    @Override
    public DatabaseStructure getDatabaseStructure(String instanceName) throws SQLException {
        Connection connection = null;

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            InstanceInfo instanceInfo = instanceInfoRepository.findByInstanceName(instanceName);

            DataSource dataSource = getDataSource(instanceInfo);

            connection = dataSource.getConnection();

            /*
            -- https://en.wikipedia.org/wiki/Information_schema
            -- https://dev.mysql.com/doc/refman/5.5/en/performance-schema.html
            -- Performance and information schema are not scanned because the client doesn't need that data
             */
            String query = getQuery(commonConfiguration.getStructureFileMySql());

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            DatabaseStructure databaseStructure = new DatabaseStructure();

            String previousSchema = "";
            String previousTable = "";

            while (resultSet.next()) {

                String currentSchema = resultSet.getString("DATABASESCHEMA"); //This are the names assigned on mysql-query.sql
                String currentTable = resultSet.getString("TABLENAME");
                String currentColumnName = resultSet.getString("COLUMNNAME");
                String currentColumnType = resultSet.getString("COLUMNTYPE");

                if (!previousSchema.equals(currentSchema)) { //This branch will be reached if i reach a new Schema

                    Schema schema = new Schema();
                    schema.setSchemaName(currentSchema);

                    Column column = mapColumn(currentColumnName, currentColumnType);

                    Table table = mapTable(currentTable, column);

                    schema.addTable(table);
                    databaseStructure.addSchema(schema);

                } else { //While this one is going to be reached if i'm on the Last Schema
                    Schema schema = databaseStructure.getSchemas().get(databaseStructure.getSchemas().size() - 1);
                    if (!previousTable.equals(currentTable)) { //Same way but with tables

                        Column column = mapColumn(currentColumnName, currentColumnType);

                        Table table = mapTable(currentTable, column);

                        schema.addTable(table);

                    } else {
                        Table table = schema.getTables().get(schema.getTables().size() - 1);

                        Column column = mapColumn(currentColumnName, currentColumnType);
                        table.addColumn(column);
                    }
                }

                previousSchema = currentSchema;
                previousTable = currentTable;
            }
            return databaseStructure;
        }catch(Exception e){
                throw e;
            }
        finally {
            if (Objects.nonNull(resultSet)) {
                resultSet.close();
            }
            if (Objects.nonNull(statement)) {
                statement.close();
            }
        }
    }

    private String getStructureFileName() {
        return commonConfiguration.getStructureFileMySql();
    }

    private String getQuery(String fileName){
        StringBuilder result = new StringBuilder("");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                result.append(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private Column mapColumn(String currentColumnName, String currentColumnType) {
        Column column = new Column();
        column.setColumnName(currentColumnName);
        column.setColumnType(currentColumnType);
        return column;
    }

    private Table mapTable(String currentTable, Column column) {
        Table table = new Table();
        table.setTableName(currentTable);
        table.addColumn(column);
        return table;
    }

}
