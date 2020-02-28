package api.ms.users.ms.users.models;

import api.ms.users.ms.users.core.enums.GenderEnum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private Date birthday;
    @Embedded
    private Contact contact;
    @Embedded
    private Address address;

    public Person(String firstName, String lastName, GenderEnum gender, Date birthday, Contact contact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.contact = contact;
    }

    public Person(String firstName, String lastName, GenderEnum gender, Date birthday, Contact contact, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.contact = contact;
        this.address = address;
    }
}
