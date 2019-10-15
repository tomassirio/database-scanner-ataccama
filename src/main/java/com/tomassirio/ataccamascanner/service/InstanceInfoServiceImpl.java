package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import com.tomassirio.ataccamascanner.model.InstanceInfo;
import com.tomassirio.ataccamascanner.repository.InstanceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InstanceInfoServiceImpl implements InstanceInfoService {

    @Autowired
    InstanceInfoRepository instanceInfoRepository;

    @Override
    public List<InstanceInfo> findAllInstances() {
        List<InstanceInfo> list = instanceInfoRepository.findAll();
        return list;
    }

    @Override
    public InstanceInfo findById(Long id) throws Exception{
        InstanceInfo instanceInfo = instanceInfoRepository.findInstanceInfoById(id);
        return instanceInfo;
    }

    @Override
    public InstanceInfo createInstance(InstanceInfoDTO instanceInfoDTO) throws Exception{
        isValidInstance(instanceInfoDTO);
        InstanceInfo instanceInfo = new InstanceInfo();
        mapInstance(instanceInfo, instanceInfoDTO);
        return instanceInfo;

    }

    @Override
    public InstanceInfoDTO updateInstance(InstanceInfoDTO instanceInfoDTO) throws Exception{
        Optional<InstanceInfo> instanceInfo = instanceInfoRepository.findById(instanceInfoDTO.getId());

        if (!instanceInfo.isPresent()) {
            throw new Exception(instanceInfoDTO.getInstanceName());
        }

        mapInstance(instanceInfo.get(), instanceInfoDTO);
        return instanceInfoDTO;
    }

    @Override
    public void deleteInstance(Long id) throws Exception{
        try {
            InstanceInfo instanceInfo = instanceInfoRepository.findInstanceInfoById(id);

            instanceInfoRepository.delete(instanceInfo);

        } catch (Exception e) {
            throw new Exception(id.toString());
        }
    }

    private boolean isValidInstance(InstanceInfoDTO instanceInfoDTO) throws Exception {
        InstanceInfo instanceInfo = instanceInfoRepository.findByInstanceName(instanceInfoDTO.getInstanceName());

        if (Objects.isNull(instanceInfo)) {
            return true;
        } else {
            throw new Exception(instanceInfoDTO.getInstanceName());
        }
    }

    private void mapInstance(InstanceInfo instanceInfo, InstanceInfoDTO instanceInfoDTO) {

        instanceInfo.setInstanceName(instanceInfoDTO.getInstanceName());
        instanceInfo.setHost(instanceInfoDTO.getHost());
        instanceInfo.setPort(instanceInfoDTO.getPort());
        instanceInfo.setUser(instanceInfoDTO.getUser());
        instanceInfo.setPassword(instanceInfoDTO.getPassword());
        instanceInfo.setDatabase(instanceInfoDTO.getDatabase());

        instanceInfoRepository.save(instanceInfo);
    }


}
