package api.ms.users.ms.users.dto;

import api.ms.users.ms.users.core.enums.GenderEnum;
import api.ms.users.ms.users.models.Address;
import api.ms.users.ms.users.models.Contact;
import api.ms.users.ms.users.models.Teacher;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private Date birthday;
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
