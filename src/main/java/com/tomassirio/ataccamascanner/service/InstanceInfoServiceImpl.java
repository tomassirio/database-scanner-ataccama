package com.tomassirio.ataccamascanner.service;

import com.tomassirio.ataccamascanner.exceptions.InstanceNotFoundException;
import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import com.tomassirio.ataccamascanner.model.InstanceInfo;
import com.tomassirio.ataccamascanner.repository.InstanceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public InstanceInfo findById(Long id) throws InstanceNotFoundException {
        InstanceInfo instanceInfo = instanceInfoRepository.findInstanceInfoById(id);
        return instanceInfo;
    }

    @Override
    public InstanceInfo createInstance(InstanceInfoDTO instanceInfoDTO) throws InstanceNotFoundException {
        isValidInstance(instanceInfoDTO);
        InstanceInfo instanceInfo = new InstanceInfo();
        mapInstance(instanceInfo, instanceInfoDTO);
        return instanceInfo;

    }

    @Override
    public InstanceInfo updateInstance(InstanceInfoDTO instanceInfoDTO) throws InstanceNotFoundException{
        try{
            InstanceInfo instanceInfo = instanceInfoRepository.findInstanceInfoById(instanceInfoDTO.getId());
            mapInstance(instanceInfo, instanceInfoDTO);
            return instanceInfo;
        }catch (Exception e){
            throw new InstanceNotFoundException(instanceInfoDTO.getId().toString());
        }

    }

    @Override
    public void deleteInstance(Long id) throws InstanceNotFoundException{
        try {
            InstanceInfo instanceInfo = instanceInfoRepository.findInstanceInfoById(id);

            instanceInfoRepository.delete(instanceInfo);

        } catch (Exception e) {
            throw new InstanceNotFoundException(id.toString());
        }
    }

    private boolean isValidInstance(InstanceInfoDTO instanceInfoDTO) throws InstanceNotFoundException {
        InstanceInfo instanceInfo = instanceInfoRepository.findByInstanceName(instanceInfoDTO.getInstanceName());

        if (Objects.isNull(instanceInfo)) {
            return true;
        } else {
            throw new InstanceNotFoundException(instanceInfoDTO.getInstanceName());
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
