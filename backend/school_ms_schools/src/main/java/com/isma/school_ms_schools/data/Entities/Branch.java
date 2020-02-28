package com.isma.school_ms_schools.data.Entities;

import com.isma.school_ms_schools.core.helpers.Address;
import com.isma.school_ms_schools.core.helpers.ContactSchool;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "branches")
public class Branch implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    @Embedded
    private ContactSchool contact;
    @Embedded
    private Address address;
    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<StudentCard> studentCards=new ArrayList<>();

    @OneToMany(cascade = CascadeType.MERGE)
    private List<EducationLevel> educationLevels=new ArrayList<>();

    public Branch() {
    }

    public Branch(String name, String description, ContactSchool contact, Address address) {
        this.name = name;
        this.description = description;
        this.contact = contact;
        this.address = address;
    }

    public void addStudentCard(StudentCard studentCard){
        this.studentCards.add(studentCard);
    }

    public void removeStudentCard(StudentCard studentCard){
        this.studentCards.remove(studentCard);
    }

    public void addEducationLevel(EducationLevel educationLevel){
        this.educationLevels.add(educationLevel);
    }

    public void addStudentCard(EducationLevel educationLevel){
        this.educationLevels.remove(educationLevel);
    }
}
