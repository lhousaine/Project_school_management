package com.isma.school_ms_schools.data.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EducationLevelDTO {
    private long id;
    private String name;
    private String description;
    private List<TrainingDTO> trainings;
}
