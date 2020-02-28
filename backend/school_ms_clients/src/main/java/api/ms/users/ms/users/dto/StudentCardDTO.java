package api.ms.users.ms.users.dto;

import api.ms.users.ms.users.models.StudentCard;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCardDTO {
    private Long id;
    private String trainingName;
    private String levelName;
    private String groupName;
    private String studentCode;

    public StudentCardDTO(StudentCard studentCard) {
        this.id = studentCard.getId();
        this.trainingName = studentCard.getTrainingName();
        this.groupName = studentCard.getGroupName();
        this.levelName = studentCard.getLevelName();
        this.studentCode = "" + studentCard.getStudent().getId();
    }
}
