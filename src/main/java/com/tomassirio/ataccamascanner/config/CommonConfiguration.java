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
}
