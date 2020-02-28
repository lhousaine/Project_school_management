package api.ms.users.ms.users.models;

import api.ms.users.ms.users.core.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor

public class Student extends Person {
    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<StudentCard> studentCards;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Person person;

    public Student(String firstName, String lastName, GenderEnum gender, Date birthday, Contact contact,
                   Address address, @Nullable List<StudentCard> studentCards, @Nullable Person person) {
        super(firstName, lastName, gender, birthday, contact, address);
        this.studentCards = studentCards;
        this.person = person;
    }
}
