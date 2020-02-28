package com.isma.school_ms_schools.web.controllers;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.TimeTableDTO;
import com.isma.school_ms_schools.service.iservices.ITimeTableService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping(path ="time-tables")
@Api(value="TimeTable EndPoint", description="Operations for TimeTables")
public class TimeTableController{
    private final ITimeTableService timeTableService;

    public TimeTableController(ITimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    @GetMapping(path = "",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TimeTableDTO> getAll() throws NoDataFoundException {
        return timeTableService.getAll();
    }

    @GetMapping(path = "/{code}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public TimeTableDTO getById(@PathVariable String code) throws NoDataFoundException {
        return timeTableService.getById(code);
    }

    @PostMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public TimeTableDTO create(@RequestBody TimeTableDTO timeTableDTO) throws DataAlreadyUsed, NoDataFoundException, DateFormatException, ParseException {
        return timeTableService.create(timeTableDTO);
    }

    @PatchMapping(path = "/{code}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public boolean update(@PathVariable String code,@RequestBody TimeTableDTO timeTableDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        return timeTableService.update(code,timeTableDTO);
    }

    @DeleteMapping(path = "/{code}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean delete(@PathVariable String code) throws NoDataFoundException {
        return timeTableService.delete(code);
    }

    @GetMapping(path = "/group-timeTable")
    public TimeTableDTO findTimeTableByGroupName(@RequestParam("groupName") String groupName) throws NoDataFoundException{
        return timeTableService.findTimeTableByGroupName(groupName);
    }

}
