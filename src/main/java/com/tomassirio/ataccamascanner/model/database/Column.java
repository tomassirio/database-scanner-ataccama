package com.tomassirio.ataccamascanner.model.database;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Column {
    private String columnName;
    private String columnType;
    private Boolean primaryKey;
    //Statistics purposes
    private String query;
    private String min;
    private String max;
    private String median;
    private String avg;
}
