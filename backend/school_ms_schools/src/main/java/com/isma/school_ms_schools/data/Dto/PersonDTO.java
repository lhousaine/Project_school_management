package com.isma.school_ms_schools.data.Dto;

import com.isma.school_ms_schools.core.helpers.Address;
import com.isma.school_ms_schools.core.helpers.Contact;
import com.isma.school_ms_schools.core.helpers.GenderEnum;
import com.isma.school_ms_schools.data.Entities.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private String birthday;
    private Contact contact;
    private Address address;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.gender = person.getGender();
        this.birthday = person.getBirthday();
        this.contact = person.getContact();
        this.address = person.getAddress();
    }
}
