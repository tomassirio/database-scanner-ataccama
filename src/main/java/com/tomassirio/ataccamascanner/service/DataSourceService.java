package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;

import javax.sql.DataSource;

public interface DataSourceService {

    DataSource getDataSource(InstanceInfoDTO instanceInfoDTO) throws Exception;
}
