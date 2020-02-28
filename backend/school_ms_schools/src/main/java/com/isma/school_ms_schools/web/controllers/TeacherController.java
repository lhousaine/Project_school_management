package com.isma.school_ms_schools.web.controllers;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.TeacherCardDTO;
import com.isma.school_ms_schools.data.Dto.TeacherDTO;
import com.isma.school_ms_schools.data.Entities.Teacher;
import com.isma.school_ms_schools.service.implServices.TeacherService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/teachers")
@Api(value="clients API", description="clients")
public class TeacherController {
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<TeacherDTO> getTeachers() throws NoDataFoundException {
        return teacherService.getAll();
    }

    @GetMapping(path = "/{id}")
    public TeacherDTO getTeacherById(@PathVariable Long id) throws NoDataFoundException {
        return this.teacherService.getById(id);
    }

    @PatchMapping(path = "")
    public Boolean updateTeacher(@PathVariable Long id,@RequestBody TeacherDTO teacherDTO) throws NoDataFoundException, DataAlreadyUsed {
        return teacherService.update(id,teacherDTO);
    }

    @PostMapping(path = "")
    public TeacherDTO saveTeacher(@RequestBody TeacherDTO teacherDTO) throws DataAlreadyUsed, NoDataFoundException, DateFormatException {
        return teacherService.create(teacherDTO);
    }

    @DeleteMapping(path = "/{id}")
    public Boolean deleteTeacher(@PathVariable Long id) throws NoDataFoundException {
        return teacherService.delete(id);
    }

    @PostMapping(path = "/teacherCards")
    public Boolean addTeacher(@RequestBody TeacherCardDTO teacherCardDTO) {
        return teacherService.addTeacherCardToTeacher(teacherCardDTO);
    }

}
