package com.isma.school_ms_schools.data.Entities;


import com.isma.school_ms_schools.core.helpers.Address;
import com.isma.school_ms_schools.core.helpers.Contact;
import com.isma.school_ms_schools.core.helpers.GenderEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private String birthday;
    @Embedded
    private Contact contact;
    @Embedded
    private Address address;

    public Person(String firstName, String lastName, GenderEnum gender, String birthday, Contact contact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.contact = contact;
    }

    public Person(String firstName, String lastName, GenderEnum gender, String birthday, Contact contact, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.contact = contact;
        this.address = address;
    }
}
