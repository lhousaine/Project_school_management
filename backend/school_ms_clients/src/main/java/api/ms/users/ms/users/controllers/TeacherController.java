package api.ms.users.ms.users.controllers;

import api.ms.users.ms.users.models.Teacher;
import api.ms.users.ms.users.services.servicesimpli.TeacherService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/teachers")
@Api(value="clients API", description="clients")
public class TeacherController {
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    @GetMapping(path = "/name")
    public Teacher getTeacherByName(@RequestParam(name = "first_name") String firstName,
                                    @RequestParam(name = "last_name") String lastName) {
        return teacherService.getTeacherByName(firstName, lastName);
    }

    @PutMapping(path = "/update/{id}")
    public Teacher updateTeacher(@RequestBody Teacher teacher, @PathVariable Long id) {
        return teacherService.updateTeacher(teacher, id);
    }

    @PostMapping(path = "/register")
    public Teacher saveTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteTeacher(@PathVariable Long id){
        teacherService.deleteTeacher(id);
    }
}
