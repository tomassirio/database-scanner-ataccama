package com.tomassirio.ataccamascanner.controller;

import com.tomassirio.ataccamascanner.model.InstanceInfo;
import com.tomassirio.ataccamascanner.service.InstanceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/scanner")
public class ScannerController {

    @Autowired
    private InstanceInfoService instanceInfoService;

    @PostMapping("/schemas/{instanceName}")
    public ResponseEntity<String> scan(@PathVariable("instanceName") String instanceName) throws Exception {

//        InstanceInfo instanceInfo = instanceInfoService.findById(instanceInfoDTO);
//        scannerService.execute(instanceInfo);
        return ResponseEntity.status(HttpStatus.OK).body("");

    }

//    @PostMapping("/tables/{schemaName}")
//    public ResponseEntity<String> scan(@@PathVariable("schemaName") String instanceName) throws Exception {
//
//        InstanceInfo instanceInfo = instanceInfoService.findById(instanceInfoDTO);
//        scannerService.execute(instanceInfo);
//        return ResponseEntity.status(HttpStatus.OK).body(instanceInfo);
//
//    }
//
//    @PostMapping("/columns/{tableName}")
//    public ResponseEntity<String> scan(@PathVariable("tableName") String instanceName) throws Exception {
//
//        InstanceInfo instanceInfo = instanceInfoService.findById(instanceInfoDTO);
//        scannerService.execute(instanceInfo);
//        return ResponseEntity.status(HttpStatus.OK).body(instanceInfo);
//
//    }
//
//    @PostMapping("/data/{columnName}")
//    public ResponseEntity<String> scan(@PathVariable("columnName") String instanceName) throws Exception {
//
//        InstanceInfo instanceInfo = instanceInfoService.findById(instanceInfoDTO);
//        scannerService.execute(instanceInfo);
//        return ResponseEntity.status(HttpStatus.OK).body(instanceInfo);
//
//    }
}
