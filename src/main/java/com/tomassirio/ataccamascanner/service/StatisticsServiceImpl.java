package com.tomassirio.ataccamascanner.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.tomassirio.ataccamascanner.model.database.Column;
import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;
import com.tomassirio.ataccamascanner.model.database.Schema;
import com.tomassirio.ataccamascanner.model.database.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private static final String SCHEMAS = "Schemas";
    private static final String TABLES = "Tables";
    private static final String COLUMNS = "Columns";

    private static final String SCHEMA = "Schema";
    private static final String TABLE = "Table";

    private static final String COLUMN_NAME = "Column name";
    private static final String COLUMN_TYPE = "Column type";

    private static final String MIN = "MIN";
    private static final String MAX = "MAX";
    private static final String AVG = "AVG";
    private static final String MEDIAN = "MEDIAN";

    private static final String TABLE_NAME = "Table Name";
    private static final String TABLE_RECORDS = "Table Records";
    private static final String TABLE_ATTRIBUTES = "Table Attributes";

    @Override
    public String statisticsColumn(DatabaseStructure databaseStructure) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(outputStream);
        generator.setPrettyPrinter(new DefaultPrettyPrinter());

        generator.writeStartObject();
        generator.writeArrayFieldStart(SCHEMAS);
        for(Schema schema : databaseStructure.getSchemas()){
            generator.writeStartObject();
            generator.writeStringField(SCHEMA, schema.getSchemaName());
            generator.writeArrayFieldStart(TABLES);
            for (Table table : schema.getTables()){
                generator.writeStartObject();
                generator.writeStringField(TABLE, table.getTableName());
                generator.writeArrayFieldStart(COLUMNS);
                for (Column column : table.getColumns()){
                    generator.writeStartObject();
                    generator.writeStringField(COLUMN_NAME, column.getColumnName());
                    generator.writeStringField(COLUMN_TYPE, column.getColumnType());
                    generator.writeStringField(MIN, column.getMin());
                    generator.writeStringField(MAX, column.getMax());
                    generator.writeStringField(AVG, column.getAvg());
//                    generator.writeStringField(MEDIAN, column.getMedian());
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
    public String statisticsTable(DatabaseStructure databaseStructure) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(outputStream);
        generator.setPrettyPrinter(new DefaultPrettyPrinter());

        generator.writeStartObject();
        generator.writeArrayFieldStart(SCHEMAS);
        for(Schema schema : databaseStructure.getSchemas()){
            generator.writeStartObject();
            generator.writeStringField(SCHEMA, schema.getSchemaName());
            generator.writeArrayFieldStart(TABLES);
            for (Table table : schema.getTables()){
                generator.writeStartObject();
                generator.writeStringField(TABLE_NAME, table.getTableName());
                generator.writeNumberField(TABLE_RECORDS, table.getRows().size());
                generator.writeNumberField(TABLE_ATTRIBUTES, table.getAttributes().size());
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
