package com.tomassirio.ataccamascanner.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.tomassirio.ataccamascanner.config.CommonConfiguration;
import com.tomassirio.ataccamascanner.model.database.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Service
public class ScannerServiceImpl implements ScannerService {


    @Override
    public String scanSchemas(DatabaseStructure databaseStructure) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(outputStream);
        generator.setPrettyPrinter(new DefaultPrettyPrinter());

        generator.writeStartObject();
        generator.writeArrayFieldStart(CommonConfiguration.SCHEMAS);
        for(Schema schema : databaseStructure.getSchemas()){
            generator.writeString(schema.getSchemaName());
        }
        generator.writeEndArray();
        generator.writeEndObject();

        generator.close();
        return outputStream.toString();
    }

    @Override
    public String scanTables(DatabaseStructure databaseStructure) throws IOException {
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
                generator.writeString(table.getTableName());
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
    public String scanColumns(DatabaseStructure databaseStructure) throws IOException {
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
                    generator.writeStringField(CommonConfiguration.PRIMARY_KEY, column.getPrimaryKey().toString());
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
    public String scanFields(DatabaseStructure databaseStructure) throws IOException {
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
                generator.writeArrayFieldStart(CommonConfiguration.ROWS);
                for (Row row : table.getRows()){
                    generator.writeStartObject();
                    generator.writeArrayFieldStart(CommonConfiguration.ROW);
                    for (Field field : row.getFields()){
                        generator.writeStartObject();
                        generator.writeStringField(CommonConfiguration.FIELD_NAME, field.getName());
                        generator.writeStringField(CommonConfiguration.FIELD_VALUE, field.getValue());
                        generator.writeStringField(CommonConfiguration.FIELD_TYPE, field.getDataType());
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
        }
        generator.writeEndArray();
        generator.writeEndObject();

        generator.close();
        return outputStream.toString();
    }
}
