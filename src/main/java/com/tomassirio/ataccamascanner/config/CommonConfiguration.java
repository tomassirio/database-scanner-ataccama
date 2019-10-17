package com.tomassirio.ataccamascanner.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class CommonConfiguration {
    @Value("${scanner.structure.filename.mysql}")
    private String structureFileMySql;
    @Value("${scanner.structure.filename.mysqlbydatabase}")
    private String structureFileMySqlByDatabase;

    //Final hardcoded variables listed through the app
    public static final String SCHEMAS = "Schemas";
    public static final String TABLES = "Tables";
    public static final String COLUMNS = "Columns";
    public static final String COLUMN = "Column";

    public static final String SCHEMA = "Schema";
    public static final String TABLE = "Table";

    public static final String COLUMN_NAME = "Column name";
    public static final String COLUMN_TYPE = "Column type";
    public static final String PRIMARY_KEY = "Primary key";

    public static final String ROWS = "Rows";
    public static final String ROW = "Row";
    public static final String FIELD_NAME = "Field Name";
    public static final String FIELD_VALUE = "Field Value";
    public static final String FIELD_TYPE = "Field Type";

    public static final String MIN = "MIN";
    public static final String MAX = "MAX";
    public static final String AVG = "AVG";
    public static final String MEDIAN = "MEDIAN";

    public static final String TABLE_NAME = "Table Name";
    public static final String TABLE_RECORDS = "Table Records";
    public static final String TABLE_ATTRIBUTES = "Table Attributes";

    public static final String STATISTICS_COLUMN = "Statistics Column";
    public static final String STATISTICS_TABLE = "Statistics Table";

    public static final String MYSQL_JDBC = "jdbc:mysql";

    public static final String REFERED_SCHEMA = "REFEREDSCHEMA";
    public static final String DATABASESCHEMA = "DATABASESCHEMA";
    public static final String TABLENAME = "TABLENAME";
    public static final String COLUMNNAME = "COLUMNNAME";
    public static final String COLUMNTYPE = "COLUMNTYPE";
    public static final String KEYCOLUMN = "KEYCOLUMN";


}
