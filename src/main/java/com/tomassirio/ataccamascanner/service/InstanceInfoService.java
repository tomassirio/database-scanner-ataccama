package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import com.tomassirio.ataccamascanner.model.InstanceInfo;

import java.util.List;

public interface InstanceInfoService {

    List<InstanceInfo> findAllInstances();

    InstanceInfo findById(Long id) throws Exception;

    InstanceInfo createInstance(InstanceInfoDTO instanceInfoDTO) throws Exception;

    InstanceInfoDTO updateInstance(InstanceInfoDTO instanceInfoDTO) throws Exception;

    void deleteInstance(Long id) throws Exception;

}
