package com.isma.school_ms_schools.data.Converters.ImplConverters;


import com.isma.school_ms_schools.data.Dto.*;
import com.isma.school_ms_schools.data.Entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {
    public final ModelMapper modelMapper;

    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<StudentDTO> toStudentDTOS(List<Student> students){
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student1: students) {
            studentDTOs.add(new StudentDTO(student1));
        }
        return studentDTOs;
    }

    public Student toStudentEntity(StudentDTO studentDTO){
        return modelMapper.map(studentDTO,Student.class);
    }

    public List<PersonDTO> toPersonDTOS(List<Person> people) {
        List<PersonDTO> personDTOS = new ArrayList<>();
        for (Person person : people) {
            personDTOS.add(new PersonDTO(person));
        }
        return personDTOS;
    }

    public Person toPersonEntity(PersonDTO personDTO){
        return modelMapper.map(personDTO,Person.class);
    }

    public List<TeacherCardDTO> toTeacherCardDTOs(List<TeacherCard> teacherCards) {
        List<TeacherCardDTO> teacherCardDTOS = new ArrayList<>();
        for (TeacherCard teacherCard: teacherCards
        ) {
            teacherCardDTOS.add(new TeacherCardDTO(teacherCard));
        }
        return teacherCardDTOS;
    }

    public TeacherCard toTeacherCardEntity(TeacherCardDTO teacherCardDTO){
        return modelMapper.map(teacherCardDTO,TeacherCard.class);
    }

    public List<TeacherDTO> toTeacherDTOs(List<Teacher> teachers) {
        List<TeacherDTO> teacherDTOS = new ArrayList<>();
        for (Teacher teacher: teachers
        ) {
            teacherDTOS.add(new TeacherDTO(teacher));
        }
        return teacherDTOS;
    }

    public Teacher toTeacherEntity(TeacherDTO teacherDTO){
        return modelMapper.map(teacherDTO,Teacher.class);
    }
}
