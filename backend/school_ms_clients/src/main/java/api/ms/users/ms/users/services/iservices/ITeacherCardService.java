package api.ms.users.ms.users.services.iservices;

import api.ms.users.ms.users.dto.TeacherCardDTO;
import api.ms.users.ms.users.models.TeacherCard;

import java.util.List;

public interface ITeacherCardService {
    public List<TeacherCardDTO> getTeacherCards();
    public List<TeacherCardDTO> getTeacherCardByTeacherFullName(String firstName,
                                                                String lastName);
    public TeacherCardDTO getTeacherCardByClassInfo(String trainingName,
                                                    String levelName,
                                                    String course,
                                                    String groupName);
    public TeacherCard saveTeacherCard(TeacherCard teacherCard);
    public TeacherCard updateTeacherCard(TeacherCard teacherCard);
    public void deleteTeacherCard(Long id);
}
