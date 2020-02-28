package com.isma.school_ms_schools.data.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TrainingDTO implements Serializable {
    private long id;
    private String name;
    private String description;
    private double cost;
    private List<GroupDTO> groups;
    private List<SubjectDTO> subjects;
    private String educationLevelName;
}
