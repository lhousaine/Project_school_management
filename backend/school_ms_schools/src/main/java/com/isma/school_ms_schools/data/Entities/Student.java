package com.isma.school_ms_schools.data.Entities;

import com.isma.school_ms_schools.core.helpers.Address;
import com.isma.school_ms_schools.core.helpers.Contact;
import com.isma.school_ms_schools.core.helpers.GenderEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student extends Person {
    @JoinColumn(name = "studentCardId", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private StudentCard studentCard;
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private Person parent;

    public Student(String firstName, String lastName, GenderEnum gender, String birthday, Contact contact,
                   Address address, @Nullable Person parent) {
        super(firstName, lastName, gender, birthday, contact, address);
        this.parent = parent;
    }
}
