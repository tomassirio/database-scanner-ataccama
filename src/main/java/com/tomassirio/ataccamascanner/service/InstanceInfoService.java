package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import com.tomassirio.ataccamascanner.model.InstanceInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstanceInfoService {

    List<InstanceInfo> findAllInstances();

    List<InstanceInfo> findAllByInstance(String instance);

    InstanceInfo createInstance(InstanceInfoDTO instanceInfoDTO);

    InstanceInfo updateInstance(InstanceInfoDTO instanceInfoDTO);

    InstanceInfo deleteInstance(Long id);

    InstanceInfo getInstanceInfo(String instanceName);
}
