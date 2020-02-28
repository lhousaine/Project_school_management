package com.isma.school_ms_schools.web.controllers;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.SchoolDTO;
import com.isma.school_ms_schools.service.iservices.ISchoolService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping(path ="schools")
@Api(value="School EndPoint", description="Operations for Schools")
public class SchoolController {
    private final ISchoolService schoolService;

    public SchoolController(ISchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping(path = "",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<SchoolDTO> getAll() throws NoDataFoundException {
        return schoolService.getAll();
    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public SchoolDTO getById(@PathVariable Long id) throws NoDataFoundException {
        return schoolService.getById(id);
    }

    @PostMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public SchoolDTO create(@RequestBody SchoolDTO schoolDTO) throws DataAlreadyUsed, NoDataFoundException, DateFormatException, ParseException {
        return schoolService.create(schoolDTO);
    }

    @PatchMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public boolean update(@PathVariable Long id, @RequestBody SchoolDTO schoolDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        return schoolService.update(id,schoolDTO);
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean delete(@PathVariable Long id) throws NoDataFoundException {
        return schoolService.delete(id);
    }

    @GetMapping(path = "/school-by-name",produces = {MediaType.APPLICATION_JSON_VALUE})
    public SchoolDTO findSchoolByName(@RequestParam("name") String name) throws NoDataFoundException{
        return schoolService.findSchoolByName(name);
    }

}
