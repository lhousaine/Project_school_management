package com.isma.school_ms_schools.web.controllers;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.ClassroomDTO;
import com.isma.school_ms_schools.core.helpers.ClassroomStatus;
import com.isma.school_ms_schools.service.iservices.IClassroomService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping(path ="classrooms")
@Api(value="Classroom API", description="Operations for Classrooms")
public class ClassroomController{
    private final IClassroomService classroomService;

    public ClassroomController(IClassroomService classroomService){
        this.classroomService = classroomService;
    }

    @GetMapping(path = "",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ClassroomDTO> getAll() throws NoDataFoundException {
        return classroomService.getAll();
    }

    @GetMapping(path = "/{code}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ClassroomDTO getById(@PathVariable String code) throws NoDataFoundException {
        return classroomService.getById(code);
    }

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public ClassroomDTO create(@RequestBody ClassroomDTO classroomDTO) throws DataAlreadyUsed, NoDataFoundException, DateFormatException, ParseException {
        return classroomService.create(classroomDTO);
    }

    @PatchMapping(path = "/{code}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public boolean update(@PathVariable String code,@RequestBody ClassroomDTO classroomDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        return classroomService.update(code,classroomDTO);
    }

    @DeleteMapping(path = "/{code}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean delete(@PathVariable String code) throws NoDataFoundException {
        return classroomService.delete(code);
    }

    @GetMapping(path = "/classroom-by-capacity", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ClassroomDTO> findClassroomByCapacityGreaterThan(@PathParam("capacity") int capacity) throws NoDataFoundException{
        return classroomService.findClassroomByCapacityGreaterThan(capacity);
    }

    @GetMapping(path = "/classroom-status", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ClassroomDTO> findClassroomByStatus(@RequestParam("status") ClassroomStatus status) throws NoDataFoundException{
        return classroomService.findClassroomByStatus(status);
    }
}
