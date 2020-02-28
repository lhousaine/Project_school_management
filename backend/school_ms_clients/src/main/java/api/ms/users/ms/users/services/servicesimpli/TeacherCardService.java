package api.ms.users.ms.users.services.servicesimpli;

import api.ms.users.ms.users.core.mapper.Mapper;
import api.ms.users.ms.users.dto.TeacherCardDTO;
import api.ms.users.ms.users.models.TeacherCard;
import api.ms.users.ms.users.repositories.TeacherCardRepository;
import api.ms.users.ms.users.services.iservices.ITeacherCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherCardService implements ITeacherCardService {
    private TeacherCardRepository teacherCardRepository;
    private Mapper mapper = new Mapper();

    public TeacherCardService(TeacherCardRepository teacherCardRepository) {
        this.teacherCardRepository = teacherCardRepository;
    }

    public List<TeacherCardDTO> getTeacherCards() {
        return mapper.toTeacherCardDTOs(teacherCardRepository.findAll());
    }

    public List<TeacherCardDTO> getTeacherCardByTeacherFullName(String firstName,
                                                             String lastName) {
        return mapper.toTeacherCardDTOs(teacherCardRepository.findTeacherCardByTeacher_FirstNameAndTeacher_LastName(firstName, lastName));
    }

    // with all the card information except teacher
    public TeacherCardDTO getTeacherCardByClassInfo(String trainingName,
                                                 String levelName,
                                                 String course,
                                                 String groupName) {
        return new TeacherCardDTO(teacherCardRepository.findTeacherCardByTrainingNameAndLevelNameAndCourseAndGroupName(trainingName, levelName, course, groupName));
    }

    public TeacherCard saveTeacherCard(TeacherCard teacherCard) {
        return teacherCardRepository.save(teacherCard);
    }

    public TeacherCard updateTeacherCard(TeacherCard teacherCard) {
        return teacherCardRepository.save(teacherCard);
    }

    public void deleteTeacherCard(Long id) {
        teacherCardRepository.deleteById(id);
    }

}
