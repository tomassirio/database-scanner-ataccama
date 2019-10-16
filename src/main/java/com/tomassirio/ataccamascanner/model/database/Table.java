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
    private List<Column> columns = new ArrayList<>();
    private List<Row> rows = new ArrayList<>();

    public void addColumn(Column column){
        columns.add(column);
    }
    public void addRow(Row row){
        rows.add(row);
    }

}
