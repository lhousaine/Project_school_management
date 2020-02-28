package com.isma.school_ms_schools.data.Dto;

import com.isma.school_ms_schools.data.Entities.StudentCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCardDTO {
    private long id;
    private String studentCode;
    private String studentFullName;
    private String levelName;
    private String trainingName;
    private String groupName;

    public StudentCardDTO(StudentCard studentCard) {
        this.id = studentCard.getId();
        this.studentCode = "" + studentCard.getStudent().getId();
        this.studentFullName=studentCard.getStudent().getFirstName()+" "+studentCard.getStudent().getLastName();
        this.trainingName = studentCard.getTrainingName();
        this.groupName = studentCard.getGroupName();
        this.levelName = studentCard.getLevelName();
    }
}
