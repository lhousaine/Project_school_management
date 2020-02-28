package com.isma.school_ms_schools.web.controllers;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.SubjectDTO;
import com.isma.school_ms_schools.service.iservices.ISubjectService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping(path ="subjects")
@Api(value="Subject EndPoint", description="Operations for Subjects")
public class SubjectController{
    private final ISubjectService subjectService;

    public SubjectController(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(path = "",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<SubjectDTO> getAll() throws NoDataFoundException {
        return subjectService.getAll();
    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public SubjectDTO getById(@PathVariable Long id) throws NoDataFoundException {
        return subjectService.getById(id);
    }

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public SubjectDTO create(@RequestBody SubjectDTO subjectDTO) throws DataAlreadyUsed, NoDataFoundException, DateFormatException, ParseException {
        return subjectService.create(subjectDTO);
    }

    @PatchMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public boolean update(@PathVariable Long id,@RequestBody SubjectDTO subjectDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        return subjectService.update(id,subjectDTO);
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean delete(@PathVariable Long id) throws NoDataFoundException {
        return subjectService.delete(id);
    }

    @GetMapping(path = "/subject-by-name",produces = {MediaType.APPLICATION_JSON_VALUE})
    public SubjectDTO findSubjectByName(@RequestParam("subjectName") String subjectName) throws NoDataFoundException{
        return subjectService.findSubjectByName(subjectName);
    }

}
