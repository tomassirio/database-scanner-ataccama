package com.tomassirio.ataccamascanner.controller;

import com.tomassirio.ataccamascanner.model.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/instances")
public class InstancesController {

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
    public ResponseEntity<InstanceInfo> createInstance(@Valid @RequestBody InstanceInfoDTO instanceInfoDTO) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(instanceInfoDTO);

    }

    @PutMapping("/instance_info/by_instance")
    public ResponseEntity<InstanceInfo> updateInstance(@Valid @RequestBody InstanceInfoDTO instanceInfoDTO) throws Exception {

        instanceInfoService.updateInstance(instanceInfoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(instanceInfoDTO);
    }

    @DeleteMapping("/instance_info/by_instance")
    public ResponseEntity<instanceInfo> delete(@PathVariable(value = "id") Long id) throws Exception {

        instanceInfoService.deleteInstance(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

}