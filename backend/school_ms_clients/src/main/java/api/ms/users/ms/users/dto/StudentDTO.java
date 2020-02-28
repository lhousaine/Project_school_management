package api.ms.users.ms.users.dto;

import api.ms.users.ms.users.core.enums.GenderEnum;
import api.ms.users.ms.users.models.Address;
import api.ms.users.ms.users.models.Contact;
import api.ms.users.ms.users.models.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private Date birthday;
    private Contact contact;
    private Address address;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.gender = student.getGender();
        this.birthday = student.getBirthday();
        this.contact = student.getContact();
        this.address = student.getAddress();
    }
}
