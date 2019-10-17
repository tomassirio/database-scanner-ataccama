package com.tomassirio.ataccamascanner.model.database;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class DatabaseStructure {
    private List<Schema> schemas = new ArrayList<>();

    public void addSchema(Schema schema){
        schemas.add(schema);
    }
}
