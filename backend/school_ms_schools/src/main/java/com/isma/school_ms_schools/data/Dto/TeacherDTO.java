package com.isma.school_ms_schools.data.Dto;

import com.isma.school_ms_schools.core.helpers.Address;
import com.isma.school_ms_schools.core.helpers.Contact;
import com.isma.school_ms_schools.core.helpers.GenderEnum;
import com.isma.school_ms_schools.data.Entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private String birthday;
    private Contact contact;
    private Address address;

    public TeacherDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.firstName = teacher.getFirstName();
        this.lastName = teacher.getLastName();
        this.gender = teacher.getGender();
        this.birthday = teacher.getBirthday();
        this.contact = teacher.getContact();
        this.address = teacher.getAddress();
    }
}
