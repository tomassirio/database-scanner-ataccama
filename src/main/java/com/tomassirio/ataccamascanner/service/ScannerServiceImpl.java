package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;

@Service
public class ScannerServiceImpl implements ScannerService {

    @Autowired
    private DataSourceService dataSourceService;

    @Override
    public String scan(InstanceInfoDTO instanceInfoDTO) throws Exception {
        DataSource dataSource = dataSourceService.getDataSource(instanceInfoDTO);
        Connection connection = dataSource.getConnection();

        return null;
    }
}
