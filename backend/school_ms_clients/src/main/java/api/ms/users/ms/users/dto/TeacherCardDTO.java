package api.ms.users.ms.users.dto;

import api.ms.users.ms.users.models.TeacherCard;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherCardDTO {
    private Long id;
    private String trainingName;
    private String levelName;
    private String groupName;
    private String teacherCode;
    private String course;

    public TeacherCardDTO(TeacherCard teacherCard) {
        this.id = teacherCard.getId();
        this.course = teacherCard.getCourseName();
        this.groupName = teacherCard.getGroupName();
        this.levelName = teacherCard.getLevelName();
        this.trainingName = teacherCard.getTrainingName();
        this.teacherCode = "" + teacherCard.getTeacher().getId();
    }
}
