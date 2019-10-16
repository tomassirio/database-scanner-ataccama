package com.tomassirio.ataccamascanner.controller;

import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import com.tomassirio.ataccamascanner.model.InstanceInfo;
import com.tomassirio.ataccamascanner.service.InstanceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class InstanceInfoController {

    @Autowired
    private InstanceInfoService instanceInfoService;

    @GetMapping("/instance_info")
    public ResponseEntity<List<InstanceInfo>> findAll() {

        List<InstanceInfo> instanceInfos = instanceInfoService.findAllInstances();
        return ResponseEntity.status(HttpStatus.OK).body(instanceInfos);
    }

    @GetMapping("/instance_info/{id}")
    public ResponseEntity<InstanceInfo> findById(@PathVariable(value = "id")Long id) throws Exception {

        InstanceInfo instanceInfo = instanceInfoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(instanceInfo);
    }

    @PostMapping("/instance_info")
    public ResponseEntity<InstanceInfo> createInstance(@Valid @RequestBody InstanceInfoDTO instanceInfoDTO) throws Exception {

        InstanceInfo instanceInfo = instanceInfoService.createInstance(instanceInfoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(instanceInfo);

    }

    @PutMapping("/instance_info/")
    public ResponseEntity<InstanceInfo> updateInstance(@Valid @RequestBody InstanceInfoDTO instanceInfoDTO) throws Exception {

        InstanceInfo instanceInfo = instanceInfoService.updateInstance(instanceInfoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(instanceInfo);
    }

    @DeleteMapping("/instance_info/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) throws Exception {

        instanceInfoService.deleteInstance(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

}