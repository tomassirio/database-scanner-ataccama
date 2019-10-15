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
@RequestMapping(value = "/instances")
public class InstanceInfoController {

    @Autowired
    private InstanceInfoService instanceInfoService;

    @GetMapping("/instance_info")
    public ResponseEntity<List<InstanceInfo>> findAll() {

        List<InstanceInfo> instanceInfos = instanceInfoService.findAllInstances();
        return ResponseEntity.status(HttpStatus.OK).body(instanceInfos);
    }

    @GetMapping("/instance_info/by_instance")
    public ResponseEntity<List<InstanceInfo>> findAllByInstance(@RequestParam(value = "instanceName") String instanceName) throws Exception {

        List<InstanceInfo> instanceInfos = instanceInfoService.findAllByInstance(instanceName);
        return ResponseEntity.status(HttpStatus.OK).body(instanceInfos);
    }

    @PostMapping("/instance_info")
    public ResponseEntity<InstanceInfoDTO> createInstance(@Valid @RequestBody InstanceInfoDTO instanceInfoDTO) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(instanceInfoDTO);

    }

    @PutMapping("/instance_info/by_instance")
    public ResponseEntity<InstanceInfoDTO> updateInstance(@Valid @RequestBody InstanceInfoDTO instanceInfoDTO) throws Exception {

        instanceInfoService.updateInstance(instanceInfoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(instanceInfoDTO);
    }

    @DeleteMapping("/instance_info/by_instance")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) throws Exception {

        instanceInfoService.deleteInstance(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

}