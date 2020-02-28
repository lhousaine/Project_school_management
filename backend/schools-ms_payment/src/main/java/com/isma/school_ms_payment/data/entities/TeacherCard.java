package com.isma.school_ms_payment.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCard {
    private Long id;
    private String trainingName;
    private String levelName;
    private String teacherName;
    private Long teacherCode;
    private List<Session> sessions;

    public TeacherCard(String trainingName, String levelName) {
        this.trainingName = trainingName;
        this.levelName = levelName;
    }
}
