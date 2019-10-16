package com.tomassirio.ataccamascanner.service;


import com.tomassirio.ataccamascanner.config.CommonConfiguration;
import com.tomassirio.ataccamascanner.model.InstanceInfo;
import com.tomassirio.ataccamascanner.model.database.*;
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
    private static final String PRIMARY_KEY = "PRIMARY KEY";
    private static final String MIN = "MIN";
    private static final String MAX = "MAX";
    private static final String AVG = "AVG";
    //private static final String MEDIAN = "MEDIAN";

    private static final String SCHEMA = "Schema";
    private static final String TABLE = "Table";
    private static final String COLUMN = "Column";
    private static final String ROW = "Row";
    private static final String STATISTICS_COLUMN = "Statistics Column";
    private static final String STATISTICS_TABLE = "Statistics Table";

    private static final String REFEREDSCHEMA = "REFEREDSCHEMA";


    @Autowired
    private InstanceInfoRepository instanceInfoRepository;

    @Autowired
    private CommonConfiguration commonConfiguration;

    @Override
    public DataSource getDataSource(InstanceInfo instanceInfo, Boolean allDatabases) {
        if (allDatabases)
            return DataSourceBuilder.create()
                    .url(MYSQL_JDBC + "://" + instanceInfo.getHost() + ":" + instanceInfo.getPort())
                    .username(instanceInfo.getUser())
                    .password(instanceInfo.getPassword()).build();
        else
            return DataSourceBuilder.create()
                    .url(MYSQL_JDBC + "://" + instanceInfo.getHost() + ":" + instanceInfo.getPort() + "/" + instanceInfo.getDatabase())
                    .username(instanceInfo.getUser())
                    .password(instanceInfo.getPassword()).build();
    }

    @Override
    public DatabaseStructure getDatabaseStructure(String instanceName, String structure, Boolean allDatabases) throws SQLException {
        Connection connection = null;

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            InstanceInfo instanceInfo = instanceInfoRepository.findByInstanceName(instanceName);

            DataSource dataSource = getDataSource(instanceInfo, allDatabases);

            connection = dataSource.getConnection();

            /*
            -- https://en.wikipedia.org/wiki/Information_schema
            -- https://dev.mysql.com/doc/refman/5.5/en/performance-schema.html
            -- Performance, information schema and sys are not scanned because the client doesn't need that data
             */
            String query = getQuery(allDatabases ? commonConfiguration.getStructureFileMySql() : commonConfiguration.getStructureFileMySqlByDatabase());

            if (!allDatabases) //I might want to get the info on a given database or an all databases. So i change the query on that decission
                query = query.replace(REFEREDSCHEMA, "\'" + instanceInfo.getDatabase() + "\'");

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            DatabaseStructure databaseStructure = new DatabaseStructure();

            String previousSchema = "";
            String previousTable = "";

            switch (structure) {
                case SCHEMA:
                    getSChemaStructure(resultSet, previousSchema, databaseStructure);
                    break;
                case TABLE:
                    getTableStructure(resultSet, previousSchema, previousTable, databaseStructure);
                    break;
                case COLUMN:
                    getColumnStructure(resultSet, previousSchema, previousTable, databaseStructure);
                    break;
                case STATISTICS_TABLE: //This is intended as they retrieve the same structure
                case ROW: {
                    getColumnStructure(resultSet, previousSchema, previousTable, databaseStructure);
                    getRowsStructure(databaseStructure, connection);
                }
                break;
                case STATISTICS_COLUMN:
                    getColumnStructure(resultSet, previousSchema, previousTable, databaseStructure);
                    getStatisticsColumnStructure(databaseStructure, connection);
                    break;
                default:
                    break;
            }
            return databaseStructure;
        } catch (Exception e) {
            throw e;
        } finally {
            if (Objects.nonNull(resultSet)) {
                resultSet.close();
            }
            if (Objects.nonNull(statement)) {
                statement.close();
            }
        }
    }

    private DatabaseStructure getSChemaStructure(ResultSet resultSet, String previousSchema, DatabaseStructure databaseStructure) throws SQLException {
        while (resultSet.next()) {

            String currentSchema = resultSet.getString("DATABASESCHEMA"); //This are the names assigned on mysql-query.sql

            if (!previousSchema.equals(currentSchema)) { //This branch will be reached if i reach a new Schema

                Schema schema = new Schema();
                schema.setSchemaName(currentSchema);
                databaseStructure.addSchema(schema);

            }
            previousSchema = currentSchema; //Map the currentSchema to the Previous one
        }
        return databaseStructure;
    }

    private DatabaseStructure getColumnStructure(ResultSet resultSet, String previousSchema, String previousTable, DatabaseStructure databaseStructure) throws SQLException {
        while (resultSet.next()) {

            String currentSchema = resultSet.getString("DATABASESCHEMA"); //This are the names assigned on mysql-query.sql
            String currentTable = resultSet.getString("TABLENAME");
            String currentColumnName = resultSet.getString("COLUMNNAME");
            String currentColumnType = resultSet.getString("COLUMNTYPE");
            String currentColumnKeyUsage = resultSet.getString("KEYCOLUMN");

            if (!previousSchema.equals(currentSchema)) { //This branch will be reached if i reach a new Schema

                Schema schema = new Schema();
                schema.setSchemaName(currentSchema);

                Column column = mapColumn(currentColumnName, currentColumnType, currentColumnKeyUsage);

                Table table = mapTable(currentTable, column);

                schema.addTable(table);
                databaseStructure.addSchema(schema);

            } else { //While this one is going to be reached if i'm on the Last Schema
                Schema schema = databaseStructure.getSchemas().get(databaseStructure.getSchemas().size() - 1);
                if (!previousTable.equals(currentTable)) { //Same way but with tables

                    Column column = mapColumn(currentColumnName, currentColumnType, currentColumnKeyUsage);
                    Table table = mapTable(currentTable, column);
                    table.getAttributes().add(currentColumnType);
                    schema.addTable(table);


                } else {

                    Table table = schema.getTables().get(schema.getTables().size() - 1);
                    Column column = mapColumn(currentColumnName, currentColumnType, currentColumnKeyUsage);
                    table.getAttributes().add(currentColumnType);
                    table.addColumn(column);
                }
            }

            previousSchema = currentSchema; //Map the currentSchema to the Previous one
            previousTable = currentTable; //Same but with table
        }
        generateQueries(databaseStructure);
        return databaseStructure;
    }

    private DatabaseStructure getTableStructure(ResultSet resultSet, String previousSchema, String previousTable, DatabaseStructure databaseStructure) throws SQLException {
        while (resultSet.next()) {

            String currentSchema = resultSet.getString("DATABASESCHEMA"); //This are the names assigned on mysql-query.sql
            String currentTable = resultSet.getString("TABLENAME");
            String currentColumnName = resultSet.getString("COLUMNNAME");
            String currentColumnType = resultSet.getString("COLUMNTYPE");
            String currentColumnKeyUsage = resultSet.getString("KEYCOLUMN");

            if (!previousSchema.equals(currentSchema)) { //This branch will be reached if i reach a new Schema

                Schema schema = new Schema();
                schema.setSchemaName(currentSchema);

                Column column = mapColumn(currentColumnName, currentColumnType, currentColumnKeyUsage);

                Table table = mapTable(currentTable, column);

                schema.addTable(table);
                databaseStructure.addSchema(schema);

            } else { //While this one is going to be reached if i'm on the Last Schema
                Schema schema = databaseStructure.getSchemas().get(databaseStructure.getSchemas().size() - 1);
                if (!previousTable.equals(currentTable)) { //Same way but with tables

                    Table table = new Table();
                    table.setTableName(currentTable);
                    schema.addTable(table);
                }
            }

            previousSchema = currentSchema; //Map the currentSchema to the Previous one
            previousTable = currentTable; //Same but with table
        }
        return databaseStructure;
    }

    private DatabaseStructure getRowsStructure(DatabaseStructure databaseStructure, Connection connection) throws SQLException {
        Statement fieldStatement = null;
        ResultSet fieldResultSet = null;
        for (Schema schema : databaseStructure.getSchemas()) {
            for (Table table : schema.getTables()) {
                fieldStatement = connection.createStatement();
                fieldResultSet = fieldStatement.executeQuery(table.getQuery());
                while (fieldResultSet.next()) {
                    Row row = new Row();
                    for (Column column : table.getColumns()) {
                        Field field = new Field();
                        field.setName(column.getColumnName());
                        field.setDataType(column.getColumnType());
                        field.setValue(fieldResultSet.getString(column.getColumnName()));
                        row.addField(field);

                    }
                    table.addRow(row);
                }

            }
        }
        return databaseStructure;
    }

    private DatabaseStructure getStatisticsColumnStructure(DatabaseStructure databaseStructure, Connection connection) throws SQLException {
        Statement colStatement = null;
        ResultSet colResultSet = null;
        for (Schema schema : databaseStructure.getSchemas()) {
            for (Table table : schema.getTables()) {
                for(Column column : table.getColumns()){
                    colStatement = connection.createStatement();
                    colResultSet = colStatement.executeQuery(column.getQuery());
                    while(colResultSet.next()){
                        column.setMin(colResultSet.getString(MIN));
                        column.setMax(colResultSet.getString(MAX));
                        column.setAvg(colResultSet.getString(AVG));
                    }

                }
            }

        }

        return databaseStructure;
    }

    private String getStructureFileName() {
        return commonConfiguration.getStructureFileMySql();
    }

    private String getQuery(String fileName) {
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

    private Column mapColumn(String currentColumnName, String currentColumnType, String currentColumnKeyUsage) {
        Column column = new Column();
        column.setColumnName(currentColumnName);
        column.setColumnType(currentColumnType);
        column.setPrimaryKey(currentColumnKeyUsage.equals(PRIMARY_KEY) ? Boolean.TRUE : Boolean.FALSE);
        return column;
    }

    private Table mapTable(String currentTable, Column column) {
        Table table = new Table();
        table.setTableName(currentTable);
        table.addColumn(column);
        return table;
    }

    private void generateQueries(DatabaseStructure databaseStructure) {
        for (Schema schema : databaseStructure.getSchemas()) {
            for (Table table : schema.getTables()) {

                StringBuilder sbQuery = (new StringBuilder()).append("select ");
                for (Column column : table.getColumns()) {
                    sbQuery.append("`").append(column.getColumnName()).append("`, ");
                    generateColumnQuery(column, schema.getSchemaName(), table.getTableName());
                }
                sbQuery.delete(sbQuery.length() - 2, sbQuery.length() - 1);
                sbQuery.append("from ").append(schema.getSchemaName()).append(".").append(table.getTableName());

                table.setQuery(sbQuery.toString());
            }
        }

    }

    private void generateColumnQuery(Column column, String schemaName, String tableName) {
        StringBuilder sbQuery = (
                new StringBuilder()).append("SELECT ")
                .append("MIN(")
                .append(column.getColumnName())
                .append(") as MIN, ")
                .append("MAX(")
                .append(column.getColumnName())
                .append(") as MAX, ")
                .append("AVG(")
                .append(column.getColumnName())
                .append(") as AVG ")
                .append("FROM ")
                .append(schemaName + "." + tableName)
                .append(";");

        column.setQuery(sbQuery.toString());

    }
}
