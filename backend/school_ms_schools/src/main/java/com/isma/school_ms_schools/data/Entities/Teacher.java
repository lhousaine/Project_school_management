package com.isma.school_ms_schools.data.Entities;

import com.isma.school_ms_schools.core.helpers.Address;
import com.isma.school_ms_schools.core.helpers.Contact;
import com.isma.school_ms_schools.core.helpers.GenderEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher extends Person {
    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
    private List<TeacherCard> teacherCards= new ArrayList<>();;
    public Teacher(String firstName, String lastName, GenderEnum gender,String birthday, Contact contact,
                   Address address) {
        super(firstName, lastName, gender, birthday, contact, address);
    }

    public void addTeacherCard(TeacherCard teacherCard) {
        this.teacherCards.add(teacherCard);
    }

    public void removeSubject(TeacherCard teacherCard) {
        this.teacherCards.remove(teacherCard);
    }
}
