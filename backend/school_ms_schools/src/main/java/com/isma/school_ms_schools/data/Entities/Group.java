package com.isma.school_ms_schools.data.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "training_id")
    private Training training;

    @JoinColumn(name = "timeTableCode", unique = true)
    @OneToOne
    @Fetch(FetchMode.SELECT)
    private TimeTable timeTable;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Student> students = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }
}
