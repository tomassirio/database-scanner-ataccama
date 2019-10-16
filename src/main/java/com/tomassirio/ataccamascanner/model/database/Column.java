package com.tomassirio.ataccamascanner.model.database;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Column {
    private String columnName;
    private String columnType;
    private Boolean primaryKey;

}
