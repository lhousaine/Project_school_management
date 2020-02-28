package api.ms.users.ms.users.controllers;

import api.ms.users.ms.users.models.Student;
import api.ms.users.ms.users.services.servicesimpli.StudentService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/students")
@Api(value="clients API", description="clients")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "/name")
    public Student getStudentByName(@RequestParam(name = "first_name") String firstName,
                                    @RequestParam(name = "last_name") String lastName) {
        return studentService.getStudentByName(firstName, lastName);
    }

    @PutMapping(path = "/update/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable Long id) {
        return studentService.updateStudent(student, id);
    }

    @PostMapping(path = "/register")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
}
