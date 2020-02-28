package api.ms.users.ms.users.services.iservices;

import api.ms.users.ms.users.dto.StudentCardDTO;
import api.ms.users.ms.users.models.StudentCard;

import java.util.List;

public interface IStudentCardService {
    public List<StudentCardDTO> getStudentCards();
    public List<StudentCardDTO> getStudentCardByFullName(String firstName,
                                                         String lastName);
    public StudentCardDTO getStudentCardByClassInfo(String trainingName,
                                                    String levelName,
                                                    String groupName);
    public StudentCard saveStudentCard(StudentCard studentCard);
    public StudentCard updateTeacherCard(StudentCard studentCard);
}
