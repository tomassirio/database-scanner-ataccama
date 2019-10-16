package com.tomassirio.ataccamascanner.service;


import com.tomassirio.ataccamascanner.config.CommonConfiguration;
import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import com.tomassirio.ataccamascanner.model.InstanceInfo;
import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;
import com.tomassirio.ataccamascanner.repository.InstanceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DataSourceServiceImpl implements DataSourceService {

    private static final String MYSQL_JDBC = "jdbc:mysql";

    @Autowired
    private InstanceInfoRepository instanceInfoRepository;

    @Autowired
    private CommonConfiguration commonConfiguration;

    @Override
    public DataSource getDataSource(InstanceInfo instanceInfo) {
        return DataSourceBuilder.create()
                .url(MYSQL_JDBC + "://" + instanceInfo.getHost() + ":" + instanceInfo.getPort())
                .username(instanceInfo.getUser())
                .password(instanceInfo.getPassword()).build();
    }

    @Override
    public DatabaseStructure getDatabaseStructure(InstanceInfoDTO instanceInfoDTO) throws SQLException {
        Connection connection = null;
        try{
            InstanceInfo instanceInfo = instanceInfoRepository.findByInstanceName(instanceInfoDTO.getInstanceName());

            DataSource dataSource = getDataSource(instanceInfo);

            connection = dataSource.getConnection();

            String query = getQuery(commonConfiguration.getStructureFileMySql());



        }catch (Exception e){
            throw e;
        }
        return null;
    }

    private String getStructureFileName() {
        return commonConfiguration.getStructureFileMySql();
    }

    private String getQuery(String filename){
        return "";
    }

}
