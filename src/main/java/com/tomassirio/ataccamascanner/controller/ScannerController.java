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
    private static final String ROWS = "Row";

    @Autowired
    private DatabaseStructureService databaseStructureService;

    @Autowired
    private ScannerService scannerService;

    @GetMapping("/{instanceName}/schemas")
    public ResponseEntity<String> scanSchemas(@PathVariable("instanceName") String instanceName) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(instanceName, SCHEMA, Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanSchemas(databaseStructure));

    }

    @GetMapping("/{instanceName}/tables")
    public ResponseEntity<String> scanTables(@PathVariable("instanceName") String instanceName) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(instanceName, TABLE, Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanTables(databaseStructure));

    }

    @GetMapping("/{instanceName}/columns")
    public ResponseEntity<String> scanColumns(@PathVariable("instanceName") String instanceName) throws Exception {
        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(instanceName, COLUMN, Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanColumns(databaseStructure));

    }

    @GetMapping("/{instanceName}/rows")
    public ResponseEntity<String> scanFields(@PathVariable("instanceName") String instanceName) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(instanceName, ROWS, Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanFields(databaseStructure));

    }

    @GetMapping("/{instanceName}/database/tables")
    public ResponseEntity<String> scanTablesFromDatabase(@PathVariable("instanceName") String instanceName) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(instanceName, TABLE, Boolean.FALSE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanTables(databaseStructure));

    }

    @GetMapping("/{instanceName}/database/columns")
    public ResponseEntity<String> scanColumnsFromDatabase(@PathVariable("instanceName") String instanceName) throws Exception {
        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(instanceName, COLUMN, Boolean.FALSE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanColumns(databaseStructure));

    }

    @GetMapping("/{instanceName}/database/rows")
    public ResponseEntity<String> scanFieldsFromDatabase(@PathVariable("instanceName") String instanceName) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(instanceName, ROWS, Boolean.FALSE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanFields(databaseStructure));

    }
}
