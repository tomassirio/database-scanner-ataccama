package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;

public interface ScannerService {

    String scan(InstanceInfoDTO instanceInfoDTO) throws Exception;
}
