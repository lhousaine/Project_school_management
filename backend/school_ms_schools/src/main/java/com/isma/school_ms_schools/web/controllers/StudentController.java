package com.isma.school_ms_schools.web.controllers;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.StudentDTO;
import com.isma.school_ms_schools.data.Entities.Student;
import com.isma.school_ms_schools.service.implServices.StudentService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/students")
@Api(value="clients API", description="clients")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getStudents() throws NoDataFoundException {
        return studentService.getAll();
    }

    @GetMapping(path = "/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) throws NoDataFoundException {
        return this.studentService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public Boolean updateStudent(@PathVariable Long id,@RequestBody StudentDTO studentDTO) throws NoDataFoundException, DataAlreadyUsed {
        return studentService.update(id,studentDTO);
    }

    @PostMapping(path = "")
    public StudentDTO saveStudent(@RequestBody Student student) throws DataAlreadyUsed, NoDataFoundException, DateFormatException {
        return studentService.createNewStudent(student);
    }

    @DeleteMapping(path = "/{id}")
    public Boolean deleteStudent(@PathVariable Long id) throws NoDataFoundException {
        return studentService.delete(id);
    }

    @GetMapping(path = "/students-by-group")
    public List<StudentDTO> findStudentsByGroupId(@RequestParam("idGroup") Long idGroup) throws NoDataFoundException {
        return studentService.findStudentsByGroup_Id(idGroup);
    }
    @GetMapping(path = "/student-parent")
    public StudentDTO findStudentsByParentId(@RequestParam("idParent") Long idParent) throws NoDataFoundException {
        return studentService.findStudentByParent_Id(idParent);
    }

    @GetMapping(path = "/student-card")
    public StudentDTO findStudentsByStudentCardId(@RequestParam("idStuCard") Long idStuCard) throws NoDataFoundException {
        return studentService.findStudentByStudentCard_Id(idStuCard);
    }
}
