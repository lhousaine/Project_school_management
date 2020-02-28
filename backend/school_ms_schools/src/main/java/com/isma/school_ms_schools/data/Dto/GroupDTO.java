package com.isma.school_ms_schools.data.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private long id;
    private String name;
    private int studentCount;
    private String trainingName;
    private String codeTimeTable;
}
