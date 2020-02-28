package api.ms.users.ms.users.controllers;

import api.ms.users.ms.users.dto.TeacherCardDTO;
import api.ms.users.ms.users.models.TeacherCard;
import api.ms.users.ms.users.services.servicesimpli.TeacherCardService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/teacher_cards")
@Api(value="clients API", description="clients")
public class TeacherCardController {
    private TeacherCardService teacherCardService;

    public TeacherCardController(TeacherCardService teacherCardService) {
        this.teacherCardService = teacherCardService;
    }

    @GetMapping
    public List<TeacherCardDTO> getTeacherCards() {
        return teacherCardService.getTeacherCards();
    }

    @GetMapping(path = "/class")
    public TeacherCardDTO getTeacherCardByClassInfo(@RequestParam(name = "training_name") String trainingName,
                                                 @RequestParam(name = "level_name") String levelName,
                                                 @RequestParam(name = "course") String course,
                                                 @RequestParam(name = "group_name") String groupName) {
        return teacherCardService.getTeacherCardByClassInfo(trainingName, levelName, course, groupName);
    }

    @GetMapping(path = "/teacher")
    public List<TeacherCardDTO> getTeacherCardByTeacher(@RequestParam(name = "first_name") String firstName,
                                                     @RequestParam(name = "last_name") String lastName) {
        return teacherCardService.getTeacherCardByTeacherFullName(firstName, lastName);
    }

    @PostMapping(path = "/register")
    public TeacherCard saveTeacherCard(@RequestBody TeacherCard teacherCard) {
        return teacherCardService.saveTeacherCard(teacherCard);
    }

    @PutMapping(path = "/update/{id}")
    public TeacherCard updateTeacherCard(@RequestBody TeacherCard teacherCard, @PathVariable Long id) {
        return teacherCardService.updateTeacherCard(teacherCard, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteTeacherCard(@PathVariable Long id) {
        teacherCardService.deleteTeacherCard(id);
    }
}
