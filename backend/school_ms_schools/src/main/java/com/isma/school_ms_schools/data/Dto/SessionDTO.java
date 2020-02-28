package com.isma.school_ms_schools.data.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SessionDTO {
    private Long id;
    private double duration;
    private String Started_At;
    private String codeClassroom;
    private String subjectName;
    private String teacherName;
}
