package com.isma.school_ms_schools.data.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studentsCards")
public class StudentCard implements Serializable {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private Long id;
    private String groupName;
    private String trainingName;
    private String levelName;
    @OneToOne(cascade = CascadeType.ALL,mappedBy ="studentCard")
    private Student student;

    public StudentCard(String levelName, String trainingName, String groupName) {
        this.levelName = levelName;
        this.trainingName = trainingName;
        this.groupName = groupName;
    }

}
