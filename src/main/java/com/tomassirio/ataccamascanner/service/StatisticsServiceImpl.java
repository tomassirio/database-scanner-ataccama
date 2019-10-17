package com.tomassirio.ataccamascanner.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.tomassirio.ataccamascanner.config.CommonConfiguration;
import com.tomassirio.ataccamascanner.model.database.Column;
import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;
import com.tomassirio.ataccamascanner.model.database.Schema;
import com.tomassirio.ataccamascanner.model.database.Table;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Override
    public String statisticsColumn(DatabaseStructure databaseStructure) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(outputStream);
        generator.setPrettyPrinter(new DefaultPrettyPrinter());

        generator.writeStartObject();
        generator.writeArrayFieldStart(CommonConfiguration.SCHEMAS);
        for(Schema schema : databaseStructure.getSchemas()){
            generator.writeStartObject();
            generator.writeStringField(CommonConfiguration.SCHEMA, schema.getSchemaName());
            generator.writeArrayFieldStart(CommonConfiguration.TABLES);
            for (Table table : schema.getTables()){
                generator.writeStartObject();
                generator.writeStringField(CommonConfiguration.TABLE, table.getTableName());
                generator.writeArrayFieldStart(CommonConfiguration.COLUMNS);
                for (Column column : table.getColumns()){
                    generator.writeStartObject();
                    generator.writeStringField(CommonConfiguration.COLUMN_NAME, column.getColumnName());
                    generator.writeStringField(CommonConfiguration.COLUMN_TYPE, column.getColumnType());
                    generator.writeStringField(CommonConfiguration.MIN, column.getMin());
                    generator.writeStringField(CommonConfiguration.MAX, column.getMax());
                    generator.writeStringField(CommonConfiguration.AVG, column.getAvg());
//                    generator.writeStringField(MEDIAN, column.getMedian()); MEDIAN was not instantiated since I couldn't get my way through the SQL Query
                    generator.writeEndObject();
                }
                generator.writeEndArray();
                generator.writeEndObject();
            }
            generator.writeEndArray();
            generator.writeEndObject();
        }
        generator.writeEndArray();
        generator.writeEndObject();

        generator.close();
        return outputStream.toString();
    }

    @Override
    public String statisticsTable(DatabaseStructure databaseStructure) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(outputStream);
        generator.setPrettyPrinter(new DefaultPrettyPrinter());

        generator.writeStartObject();
        generator.writeArrayFieldStart(CommonConfiguration.SCHEMAS);
        for(Schema schema : databaseStructure.getSchemas()){
            generator.writeStartObject();
            generator.writeStringField(CommonConfiguration.SCHEMA, schema.getSchemaName());
            generator.writeArrayFieldStart(CommonConfiguration.TABLES);
            for (Table table : schema.getTables()){
                generator.writeStartObject();
                generator.writeStringField(CommonConfiguration.TABLE_NAME, table.getTableName());
                generator.writeNumberField(CommonConfiguration.TABLE_RECORDS, table.getRows().size());
                generator.writeNumberField(CommonConfiguration.TABLE_ATTRIBUTES, table.getAttributes().size());
                generator.writeEndObject();

            }
            generator.writeEndArray();
            generator.writeEndObject();
        }
        generator.writeEndArray();
        generator.writeEndObject();

        generator.close();
        return outputStream.toString();
    }
}
