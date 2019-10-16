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

    private static final String SCHEMA = "Schema";
    private static final String TABLE = "Table";
    private static final String COLUMN = "Column";


    @Autowired
    private InstanceInfoService instanceInfoService;

    @Autowired
    private DatabaseStructureService databaseStructureService;

    @Autowired
    private ScannerService scannerService;

    @PostMapping("/{instanceName}/schemas")
    public ResponseEntity<String> scanSchemas(@PathVariable("instanceName") String instanceName) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(instanceName, SCHEMA);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanSchemas(databaseStructure));

    }

    @PostMapping("/{instanceName}/tables")
    public ResponseEntity<String> scanTables(@PathVariable("instanceName") String instanceName) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(instanceName, TABLE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanTables(databaseStructure));

    }

    @PostMapping("/{instanceName}/columns")
    public ResponseEntity<String> scanColumns(@PathVariable("instanceName") String instanceName) throws Exception {
        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(instanceName, COLUMN);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanColumns(databaseStructure));

    }
}
