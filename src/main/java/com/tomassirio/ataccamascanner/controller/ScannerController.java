package com.tomassirio.ataccamascanner.controller;

import com.tomassirio.ataccamascanner.config.CommonConfiguration;
import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;
import com.tomassirio.ataccamascanner.service.DatabaseStructureService;
import com.tomassirio.ataccamascanner.service.ScannerService;
import com.tomassirio.ataccamascanner.utils.ErrorDetails;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/scanner")
public class ScannerController {


    @Autowired
    private DatabaseStructureService databaseStructureService;

    @Autowired
    private ScannerService scannerService;

    @ApiOperation(value = "Gets evey Schema through the Instance params")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class)

    })
    @GetMapping("/{id}/schemas")
    public ResponseEntity<String> scanSchemas(@PathVariable("id") Long id) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(id, CommonConfiguration.SCHEMA, Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanSchemas(databaseStructure));

    }

    @ApiOperation(value = "Gets evey Table through the Instance params")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class)

    })
    @GetMapping("/{id}/tables")
    public ResponseEntity<String> scanTables(@PathVariable("id") Long id) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(id, CommonConfiguration.TABLE, Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanTables(databaseStructure));

    }

    @ApiOperation(value = "Gets evey Column through the Instance params")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class)

    })
    @GetMapping("/{id}/columns")
    public ResponseEntity<String> scanColumns(@PathVariable("id") Long id) throws Exception {
        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(id, CommonConfiguration.COLUMN, Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanColumns(databaseStructure));

    }

    @ApiOperation(value = "Gets evey Row with it's data through the Instance params")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class)

    })
    @GetMapping("/{id}/rows")
    public ResponseEntity<String> scanFields(@PathVariable("id") Long id) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(id, CommonConfiguration.ROW, Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanFields(databaseStructure));

    }

    @ApiOperation(value = "Gets evey Table on the Database established on the Instance params")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class)

    })
    @GetMapping("/{id}/database/tables")
    public ResponseEntity<String> scanTablesFromDatabase(@PathVariable("id") Long id) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(id, CommonConfiguration.TABLE, Boolean.FALSE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanTables(databaseStructure));

    }

    @ApiOperation(value = "Gets evey Column on the Database established on the Instance params")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class)

    })
    @GetMapping("/{id}/database/columns")
    public ResponseEntity<String> scanColumnsFromDatabase(@PathVariable("id") Long id) throws Exception {
        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(id, CommonConfiguration.COLUMN, Boolean.FALSE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanColumns(databaseStructure));

    }

    @ApiOperation(value = "Gets evey Row with it's data on the Database established on the Instance params")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class)

    })
    @GetMapping("/{id}/database/rows")
    public ResponseEntity<String> scanFieldsFromDatabase(@PathVariable("id") Long id) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(id, CommonConfiguration.ROW, Boolean.FALSE);

        return ResponseEntity.status(HttpStatus.OK).body(scannerService.scanFields(databaseStructure));

    }
}
