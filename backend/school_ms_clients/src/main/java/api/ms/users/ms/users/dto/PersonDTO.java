package api.ms.users.ms.users.dto;

import api.ms.users.ms.users.core.enums.GenderEnum;
import api.ms.users.ms.users.models.Address;
import api.ms.users.ms.users.models.Contact;
import api.ms.users.ms.users.models.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PersonDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private Date birthday;
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
