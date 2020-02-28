package com.isma.school_ms_schools.web.controllers;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.StudentCardDTO;
import com.isma.school_ms_schools.service.iservices.IStudentCardService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping(path ="students-cards")
@Api(value="StudentCard EndPoint", description="Operations for StudentCards")
public class StudentCardController{
    private final IStudentCardService studentCardService;

    public StudentCardController(IStudentCardService studentCardService) {
        this.studentCardService = studentCardService;
    }

    @GetMapping(path = "",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<StudentCardDTO> getAll() throws NoDataFoundException {
        return studentCardService.getAll();
    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public StudentCardDTO getById(@PathVariable Long id) throws NoDataFoundException {
        return studentCardService.getById(id);
    }

    @PostMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public StudentCardDTO create(@RequestBody StudentCardDTO studentCardDTO) throws DataAlreadyUsed, NoDataFoundException, DateFormatException, ParseException {
        return studentCardService.create(studentCardDTO);
    }

    @PatchMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public boolean update(@PathVariable Long id,@RequestBody StudentCardDTO studentCardDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        return studentCardService.update(id,studentCardDTO);
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean delete(@PathVariable Long id) throws NoDataFoundException {
        return studentCardService.delete(id);
    }

    @GetMapping(path = "/stuCard-by-stuCode/{studentCode}")
    public StudentCardDTO findStudentCardByStudentCode(@PathVariable("studentCode") Long studentCode) throws NoDataFoundException{
        return studentCardService.findStudentCardByStudentCode(studentCode);
    }
    @GetMapping(path = "/stuCards-by-group")
    public List<StudentCardDTO> findStudentCardsByGroupName(String groupName) throws NoDataFoundException{
        return studentCardService.findStudentCardsByGroupName(groupName);
    }
}
