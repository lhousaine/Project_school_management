package com.isma.school_ms_schools.data.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subjects")
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private String description;
    @ManyToMany(mappedBy = "subjects",cascade = CascadeType.MERGE)
    private List<Training> trainings;

    public Subject(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
