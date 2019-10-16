package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.exceptions.InstanceInfoValidationException;
import com.tomassirio.ataccamascanner.exceptions.InstanceNotFoundException;
import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import com.tomassirio.ataccamascanner.model.InstanceInfo;

import java.util.List;

public interface InstanceInfoService {

    List<InstanceInfo> findAllInstances();

    InstanceInfo findById(Long id) throws InstanceNotFoundException;

    InstanceInfo createInstance(InstanceInfoDTO instanceInfoDTO) throws InstanceInfoValidationException, InstanceNotFoundException;

    InstanceInfo updateInstance(InstanceInfoDTO instanceInfoDTO) throws InstanceInfoValidationException, InstanceNotFoundException;

    void deleteInstance(Long id) throws InstanceNotFoundException;

}
