package com.tomassirio.ataccamascanner.controller;


import com.tomassirio.ataccamascanner.config.CommonConfiguration;
import com.tomassirio.ataccamascanner.model.database.DatabaseStructure;
import com.tomassirio.ataccamascanner.service.DatabaseStructureService;
import com.tomassirio.ataccamascanner.service.StatisticsService;
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
@RequestMapping(value = "/statistics")
public class StatisticsController {

    @Autowired
    private DatabaseStructureService databaseStructureService;

    @Autowired
    private StatisticsService statisticsService;


    @ApiOperation(value = "Gets the statistics asked in the exercise for each column on every Schema")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class)

    })
    @GetMapping("/{id}/column")
    public ResponseEntity<String> getStatisticsColumn(@PathVariable("id") Long id) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(id, CommonConfiguration.STATISTICS_COLUMN, Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.statisticsColumn(databaseStructure));

    }

    @ApiOperation(value = "Gets the statistics asked in the exercise for each table on every Schema")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class)

    })
    @GetMapping("/{id}/table")
    public ResponseEntity<String> getStatisticsTable(@PathVariable("id") Long id) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(id, CommonConfiguration.STATISTICS_TABLE, Boolean.TRUE);

        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.statisticsTable(databaseStructure));

    }


    @ApiOperation(value = "Gets the statistics asked in the exercise for each column on the database setablished")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class)

    })
    @GetMapping("/{id}/database/column")
    public ResponseEntity<String> getStatisticsColumnByDatabase(@PathVariable("id") Long id) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(id, CommonConfiguration.STATISTICS_COLUMN, Boolean.FALSE);

        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.statisticsColumn(databaseStructure));

    }

    @ApiOperation(value = "Gets the statistics asked in the exercise for each table on the database established")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetails.class)

    })
    @GetMapping("/{id}/database/table")
    public ResponseEntity<String> getStatisticsTableByDatabase(@PathVariable("id") Long id) throws Exception {

        DatabaseStructure databaseStructure = databaseStructureService.getDatabaseStructure(id, CommonConfiguration.STATISTICS_TABLE, Boolean.FALSE);

        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.statisticsTable(databaseStructure));

    }
}
