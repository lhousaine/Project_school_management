package com.isma.school_ms_schools.data.Entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "educationLevels")
public class EducationLevel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(length = 1000)
    private String description;

    @OneToMany(cascade =CascadeType.MERGE,orphanRemoval = true,mappedBy = "educationLevel")
    private List<Training> trainings=new ArrayList<>();

    public EducationLevel(String name) {
        this.name = name;
    }

    public void addTrainig(Training training) {
        this.trainings.add(training);
    }

    public void removeTrainig(Training training) {
        this.trainings.remove(training);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Training> getTrainings() {
        return trainings;
    }
}
