package api.ms.users.ms.users.services.servicesimpli;

import api.ms.users.ms.users.core.mapper.Mapper;
import api.ms.users.ms.users.dto.StudentCardDTO;
import api.ms.users.ms.users.models.StudentCard;
import api.ms.users.ms.users.repositories.StudentCardRepository;
import api.ms.users.ms.users.services.iservices.IStudentCardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCardService implements IStudentCardService {
    private StudentCardRepository studentCardRepository;
    private Mapper mapper = new Mapper();

    public StudentCardService(StudentCardRepository studentCardRepository) {
        this.studentCardRepository = studentCardRepository;
    }

    public List<StudentCardDTO> getStudentCards() {
        return mapper.toStudentCardDTOs(studentCardRepository.findAll());
    }

    public List<StudentCardDTO> getStudentCardByFullName(String firstName,
                                                      String lastName) {
        return mapper.toStudentCardDTOs(studentCardRepository.findStudentCardByStudent_FirstNameAndStudent_LastName(firstName, lastName));
    }

    public StudentCardDTO getStudentCardByClassInfo(String trainingName,
                                                 String levelName,
                                                 String groupName) {
        return new StudentCardDTO(studentCardRepository.findStudentCardByTrainingNameAndLevelNameAndGroupName(trainingName, levelName, groupName));
    }

    public StudentCard saveStudentCard(StudentCard studentCard) {
        return studentCardRepository.save(studentCard);
    }

    public StudentCard updateTeacherCard(StudentCard studentCard) {
        return studentCardRepository.save(studentCard);
    }


}
