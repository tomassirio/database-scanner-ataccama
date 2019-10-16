package com.tomassirio.ataccamascanner.controller;


import com.tomassirio.ataccamascanner.config.CommonConfiguration;
import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;
import com.tomassirio.ataccamascanner.service.DatabaseStructureService;
import com.tomassirio.ataccamascanner.service.ScannerService;
import com.tomassirio.ataccamascanner.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/statistics")
public class StatisticsController {

    @Autowired
    private DatabaseStructureService databaseStructureService;

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/{instanceName}/column")
    public ResponseEntity<String> getStatisticsColumn(@PathVariable("instanceName") String instanceName) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(instanceName, CommonConfiguration.STATISTICS_COLUMN, Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.statisticsColumn(databaseStructure));

    }

    @GetMapping("/{instanceName}/table")
    public ResponseEntity<String> getStatisticsTable(@PathVariable("instanceName") String instanceName) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(instanceName, CommonConfiguration.STATISTICS_TABLE, Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.statisticsTable(databaseStructure));

    }


    @GetMapping("/{instanceName}/database/column")
    public ResponseEntity<String> getStatisticsColumnByDatabase(@PathVariable("instanceName") String instanceName) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(instanceName, CommonConfiguration.STATISTICS_COLUMN, Boolean.FALSE);

        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.statisticsColumn(databaseStructure));

    }

    @GetMapping("/{instanceName}/database/table")
    public ResponseEntity<String> getStatisticsTableByDatabase(@PathVariable("instanceName") String instanceName) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(instanceName, CommonConfiguration.STATISTICS_TABLE, Boolean.FALSE);

        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.statisticsTable(databaseStructure));

    }
}
