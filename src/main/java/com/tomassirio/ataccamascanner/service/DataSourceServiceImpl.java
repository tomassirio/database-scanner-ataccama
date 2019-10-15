package com.tomassirio.ataccamascanner.service;


import com.tomassirio.ataccamascanner.model.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Slf4j
@Service
public class DataSourceServiceImpl implements DataSourceService {

    private static final String MYSQL_JDBC = "jdbc:mysql";

    @Override
    public DataSource getDataSource(InstanceInfo instanceInfo) throws Exception {
        return DataSourceBuilder.create()
                .url(MYSQL_JDBC + "://" + instanceInfo.getHost() + ":" + instanceInfo.getPort())
                .username(instanceInfo.getUser())
                .password(instanceInfo.getPassword()).build();
    }
}
