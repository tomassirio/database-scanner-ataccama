package com.tomassirio.ataccamascanner.service;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import com.tomassirio.ataccamascanner.model.database.Column;
import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;
import com.tomassirio.ataccamascanner.model.database.Schema;
import com.tomassirio.ataccamascanner.model.database.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.io.Writer;

@Service
public class ScannerServiceImpl implements ScannerService {

    @Override
    public String scanSchemas(DatabaseStructure databaseStructure) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(outputStream);
        generator.setPrettyPrinter(new DefaultPrettyPrinter());

        generator.writeStartObject();
        generator.writeArrayFieldStart("Schemas");
        for(Schema schema : databaseStructure.getSchemas()){
            generator.writeString(schema.getSchemaName());
        }
        generator.writeEndArray();
        generator.writeEndObject();

        generator.close();
        return outputStream.toString();
    }

    @Override
    public String scanTables(DatabaseStructure databaseStructure) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(outputStream);
        generator.setPrettyPrinter(new DefaultPrettyPrinter());

        generator.writeStartObject();
        generator.writeArrayFieldStart("Schemas");
        for(Schema schema : databaseStructure.getSchemas()){
            generator.writeStartObject();
            generator.writeStringField("Schema", schema.getSchemaName());
            generator.writeArrayFieldStart("Tables");
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
    public String scanColumns(DatabaseStructure databaseStructure) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(outputStream);
        generator.setPrettyPrinter(new DefaultPrettyPrinter());

        generator.writeStartObject();
        generator.writeArrayFieldStart("Schemas");
        for(Schema schema : databaseStructure.getSchemas()){
            generator.writeStartObject();
            generator.writeStringField("Schema", schema.getSchemaName());
            generator.writeArrayFieldStart("Tables");
            for (Table table : schema.getTables()){
                generator.writeStartObject();
                generator.writeStringField("Table", table.getTableName());
                generator.writeArrayFieldStart("Columns");
                for (Column column : table.getColumns()){
                    generator.writeStartObject();
                    generator.writeStringField("Column name", column.getColumnName());
                    generator.writeStringField("Column type", column.getColumnType());
                    generator.writeStringField("Primary Key", column.getPrimaryKey().toString());
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
