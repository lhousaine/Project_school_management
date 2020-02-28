package api.ms.users.ms.users.core.mapper;

import api.ms.users.ms.users.dto.PersonDTO;
import api.ms.users.ms.users.dto.StudentCardDTO;
import api.ms.users.ms.users.dto.TeacherCardDTO;
import api.ms.users.ms.users.models.Person;
import api.ms.users.ms.users.models.StudentCard;
import api.ms.users.ms.users.models.TeacherCard;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public List<PersonDTO> toPersonDTOS(List<Person> people) {
        List<PersonDTO> personDTOS = new ArrayList<>();
        for (Person person : people
        ) {
            personDTOS.add(new PersonDTO(person));
        }
        return personDTOS;
    }

    public List<StudentCardDTO> toStudentCardDTOs(List<StudentCard> studentCards) {
        List<StudentCardDTO> studentCardDTOs = new ArrayList<>();
        for (StudentCard studentCard1: studentCards
        ) {
            studentCardDTOs.add(new StudentCardDTO(studentCard1));
        }
        return studentCardDTOs;
    }

    public List<TeacherCardDTO> toTeacherCardDTOs(List<TeacherCard> teacherCards) {
        List<TeacherCardDTO> teacherCardDTOS = new ArrayList<>();
        for (TeacherCard teacherCard: teacherCards
        ) {
            teacherCardDTOS.add(new TeacherCardDTO(teacherCard));
        }
        return teacherCardDTOS;
    }
}
