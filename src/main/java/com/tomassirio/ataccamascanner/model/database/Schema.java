package com.tomassirio.ataccamascanner.model.database;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Schema {
    private String schemaName;
    private List<Table> tables = new ArrayList<>();

    public void addTable(Table table){
        tables.add(table);
    }
}
