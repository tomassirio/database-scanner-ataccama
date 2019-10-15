package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.InstanceInfo;

import javax.sql.DataSource;

public interface DataSourceService {

    DataSource getDataSource(InstanceInfo instanceInfo) throws Exception;
}
