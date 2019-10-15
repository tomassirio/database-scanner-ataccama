package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import com.tomassirio.ataccamascanner.model.InstanceInfo;

import java.util.List;

public interface InstanceInfoService {

    List<InstanceInfo> findAllInstances();

    InstanceInfo findById(Long id);

    InstanceInfo createInstance(InstanceInfoDTO instanceInfoDTO) throws Exception;

    InstanceInfo updateInstance(InstanceInfoDTO instanceInfoDTO);

    InstanceInfo deleteInstance(Long id);

}
