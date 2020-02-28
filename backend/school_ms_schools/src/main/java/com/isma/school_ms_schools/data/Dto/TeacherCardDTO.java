package com.isma.school_ms_schools.data.Dto;

import com.isma.school_ms_schools.data.Entities.TeacherCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherCardDTO {
    private Long id;
    private String trainingName;
    private String levelName;
    private Long teacherCode;
    private List<SessionDTO> sessions;

    public TeacherCardDTO(TeacherCard teacherCard) {
        this.id = teacherCard.getId();
        this.levelName = teacherCard.getLevelName();
        this.trainingName = teacherCard.getTrainingName();
        this.teacherCode = teacherCard.getTeacher().getId();
    }
}
