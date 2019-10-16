package com.tomassirio.ataccamascanner.model.database;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Table {
    private String tableName;
    private String query;
    private long count;
    private List<Column> columns = new ArrayList<>();

    public void addColumn(Column column){
        columns.add(column);
    }
}
