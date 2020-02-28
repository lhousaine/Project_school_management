package com.isma.school_ms_schools.data.Dto;

import com.isma.school_ms_schools.core.helpers.Address;
import com.isma.school_ms_schools.core.helpers.Contact;
import com.isma.school_ms_schools.core.helpers.GenderEnum;
import com.isma.school_ms_schools.data.Entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private String birthday;
    private Contact contact;
    private Address address;
    private String parentId;
    private String studentCardId;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.gender = student.getGender();
        this.birthday = student.getBirthday();
        this.contact = student.getContact();
        this.address = student.getAddress() != null ? student.getAddress() : new Address();
        this.parentId = student.getParent() != null ? "" + student.getParent().getId() : "";
        this.studentCardId = student.getStudentCard()!= null ? "" + student.getStudentCard().getId() : "";
    }
}
