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
public class Teacher extends Person {
    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<TeacherCard> teacherCards;

    public Teacher(String firstName, String lastName, GenderEnum gender, Date birthday, Contact contact,
                   Address address, @Nullable List<TeacherCard> teacherCardList) {
        super(firstName, lastName, gender, birthday, contact, address);
        this.teacherCards = teacherCardList;
    }
}
