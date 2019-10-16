package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScannerServiceImpl implements ScannerService {

    @Autowired
    private DatabaseStructureService databaseStructureService;

    @Override
    public String scan(InstanceInfoDTO instanceInfoDTO) throws Exception {
//        DataSource dataSource = dataSourceService.getDataSource(instanceInfoDTO);
//
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try{
//            Connection connection = dataSource.getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery();
//
//        }catch (Exception e){
//            throw e;
//        }

        return null;
    }
}
