package api.ms.users.ms.users.controllers;

import api.ms.users.ms.users.dto.StudentCardDTO;
import api.ms.users.ms.users.models.StudentCard;
import api.ms.users.ms.users.services.servicesimpli.StudentCardService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/student_cards")
@Api(value="clients API", description="clients")
public class StudentCardController {
    private StudentCardService studentCardService;

    @GetMapping
    public List<StudentCardDTO> getStudentCards() {
        return studentCardService.getStudentCards();
    }

    @GetMapping(path = "class")
    public StudentCardDTO getStudentCardByClassInfo(@RequestParam(name = "training_name") String trainingName,
                                                 @RequestParam(name = "level_name") String levelName,
                                                 @RequestParam(name = "group_name") String groupName) {
        return studentCardService.getStudentCardByClassInfo(trainingName, levelName, groupName);
    }

    @GetMapping(path = "/student")
    public List<StudentCardDTO> getStudentCradByStudent(@RequestParam(name = "first_name") String firstName,
                                                     @RequestParam(name = "last_name") String lastName) {
        return studentCardService.getStudentCardByFullName(firstName, lastName);
    }

    @PostMapping(path = "/register/student_card")
    public StudentCard saveStudentCard(StudentCard studentCard) {
        return studentCardService.saveStudentCard(studentCard);
    }

    @PutMapping(path = "/update/student_card/{id}")
    public StudentCard updateStudentCard(@RequestBody StudentCard studentCard, @PathVariable Long id) {
        return studentCardService.updateTeacherCard(studentCard, id);
    }
}
