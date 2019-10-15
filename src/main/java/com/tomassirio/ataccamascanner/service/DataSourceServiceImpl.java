package com.tomassirio.ataccamascanner.service;


import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Slf4j
@Service
public class DataSourceServiceImpl implements DataSourceService {

    private static final String MYSQL_JDBC = "jdbc:mysql";

    @Override
    public DataSource getDataSource(InstanceInfoDTO instanceInfoDTO) {
        return DataSourceBuilder.create()
                .url(MYSQL_JDBC + "://" + instanceInfoDTO.getHost() + ":" + instanceInfoDTO.getPort())
                .username(instanceInfoDTO.getUser())
                .password(instanceInfoDTO.getPassword()).build();
    }
}
