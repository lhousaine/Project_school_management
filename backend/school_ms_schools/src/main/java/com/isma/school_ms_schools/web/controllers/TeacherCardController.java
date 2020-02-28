package com.isma.school_ms_schools.web.controllers;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.TeacherCardDTO;
import com.isma.school_ms_schools.data.Entities.TeacherCard;
import com.isma.school_ms_schools.service.implServices.TeacherCardService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/teacher-cards")
@Api(value="clients API", description="clients")
public class TeacherCardController {
    private TeacherCardService teacherCardService;

    public TeacherCardController(TeacherCardService teacherCardService) {
        this.teacherCardService = teacherCardService;
    }

    @GetMapping
    public List<TeacherCardDTO> getTeacherCards() {
        return teacherCardService.getAll();
    }

    @GetMapping(path = "/{id}")
    public TeacherCardDTO getTeacherCardById(@PathVariable Long id) throws NoDataFoundException {
        return this.teacherCardService.getById(id);
    }

    @GetMapping(path = "/class")
    public List<TeacherCardDTO> getTeacherCardByClassInfo(@RequestParam(name = "training_name") String trainingName,
                                                 @RequestParam(name = "level_name") String levelName) {
        return teacherCardService.getTeacherCardByClassInfo(trainingName, levelName);
    }

    @GetMapping(path = "/teacher")
    public List<TeacherCardDTO> getTeacherCardByTeacher(@RequestParam(name = "first_name") String firstName,
                                                     @RequestParam(name = "last_name") String lastName) {
        return teacherCardService.getTeacherCardByTeacherFullName(firstName, lastName);
    }


    @GetMapping(path = "/teachercode/{id}")
    public TeacherCard getTeacherCardByTeacherCode(@PathVariable(value = "id") Long id) {
        return teacherCardService.getTeacherCardByTeacherId(id);
    }


    @PostMapping(path = "")

    public TeacherCardDTO saveTeacherCard(@RequestBody TeacherCardDTO teacherCardDTO) throws DataAlreadyUsed, NoDataFoundException {
        return teacherCardService.create(teacherCardDTO);
    }

    @PutMapping(path = "/{id}")
    public Boolean updateTeacherCard( @PathVariable Long id,@RequestBody TeacherCardDTO teacherCardDTO) throws NoDataFoundException, DataAlreadyUsed {
        return teacherCardService.update(id,teacherCardDTO);
    }

    @DeleteMapping(path = "/{id}")
    public Boolean deleteTeacherCard(@PathVariable Long id) throws NoDataFoundException {
        return teacherCardService.delete(id);
    }
    @GetMapping(path = "/student-card")
    public List<TeacherCardDTO> findTeacherCardsByTeacherId(@RequestParam("idTeacher") Long idTeacher) throws NoDataFoundException {
        return teacherCardService.findTeacherCardsByTeacher_Id(idTeacher);
    }
}
