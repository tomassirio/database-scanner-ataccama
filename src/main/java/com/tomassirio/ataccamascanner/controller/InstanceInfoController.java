package com.tomassirio.ataccamascanner.controller;

import com.tomassirio.ataccamascanner.model.DTO.InstanceInfoDTO;
import com.tomassirio.ataccamascanner.model.InstanceInfo;
import com.tomassirio.ataccamascanner.service.InstanceInfoService;
import com.tomassirio.ataccamascanner.utils.ErrorDetails;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(
            value = "Retrieve all Instances on the app"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class)

    })
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