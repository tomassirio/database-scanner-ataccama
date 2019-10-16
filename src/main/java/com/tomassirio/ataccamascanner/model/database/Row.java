package com.tomassirio.ataccamascanner.model.database;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Row {

    private List<Field> fields = new ArrayList<>();

    public void addField(Field field){
        fields.add(field);
    }

}
