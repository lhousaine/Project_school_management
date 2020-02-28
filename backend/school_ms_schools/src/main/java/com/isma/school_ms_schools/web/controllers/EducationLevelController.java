package com.isma.school_ms_schools.web.controllers;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.EducationLevelDTO;
import com.isma.school_ms_schools.service.iservices.IEducationLevelService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping(path ="education-levels")
@Api(value="EducationLevel API", description="Operations for EducationLevels")
public class EducationLevelController{
    private final IEducationLevelService educationLevelService;

    public EducationLevelController(IEducationLevelService educationLevelService) {
        this.educationLevelService = educationLevelService;
    }

    @GetMapping(path = "",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<EducationLevelDTO> getAll() throws NoDataFoundException {
        return educationLevelService.getAll();
    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public EducationLevelDTO getById(@PathVariable Long id) throws NoDataFoundException {
        return educationLevelService.getById(id);
    }

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public EducationLevelDTO create(@RequestBody EducationLevelDTO educationLevelDTO) throws DataAlreadyUsed, NoDataFoundException, DateFormatException, ParseException {
        return educationLevelService.create(educationLevelDTO);
    }

    @PatchMapping(path = "/{id}",consumes ={MediaType.APPLICATION_JSON_VALUE})
    public boolean update(@PathVariable Long id,@RequestBody EducationLevelDTO educationLevelDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        return educationLevelService.update(id,educationLevelDTO);
    }

    @DeleteMapping(path = "/{id}")
    public boolean delete(@PathVariable Long id) throws NoDataFoundException {
        return educationLevelService.delete(id);
    }

    @GetMapping(path = "/eduLevel-by-name",produces = {MediaType.APPLICATION_JSON_VALUE})
    public EducationLevelDTO findEducationLevelByName(@RequestParam("name") String name) throws NoDataFoundException{
        return educationLevelService.findEducationLevelByName(name);
    }

}
