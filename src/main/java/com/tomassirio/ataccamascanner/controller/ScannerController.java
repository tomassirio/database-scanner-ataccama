package com.tomassirio.ataccamascanner.controller;

import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;
import com.tomassirio.ataccamascanner.service.DatabaseStructureService;
import com.tomassirio.ataccamascanner.service.InstanceInfoService;
import com.tomassirio.ataccamascanner.service.ScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/scanner")
public class ScannerController {

    @Autowired
    private InstanceInfoService instanceInfoService;

    @Autowired
    private DatabaseStructureService databaseStructureService;

    @Autowired
    private ScannerService scannerService;

    @PostMapping("/{instanceName}/schemas")
    public ResponseEntity<String> scanSchemas(@PathVariable("instanceName") String instanceName) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getSchemasStructure(instanceName);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanSchemas(databaseStructure));

    }

    @PostMapping("/{instanceName}/tables")
    public ResponseEntity<String> scanTables(@PathVariable("instanceName") String instanceName) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getTablesStructure(instanceName);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanTables(databaseStructure));

    }

    @PostMapping("/{instanceName}/columns")
    public ResponseEntity<String> scanColumns(@PathVariable("instanceName") String instanceName) throws Exception {
        DatabaseStructure databaseStructure = databaseStructureService.getColumnsStructure(instanceName);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanColumns(databaseStructure));

    }
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
