package com.tomassirio.ataccamascanner.model.database;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Table {
    private String tableName;
    private String query;
    private List<Column> columns = new ArrayList<>();
    private List<Row> rows = new ArrayList<>();

    //Mostly for statistics purposes
    private Set<String> attributes = new HashSet<>();

    public void addColumn(Column column){
        columns.add(column);
    }
    public void addRow(Row row){
        rows.add(row);
    }

}
