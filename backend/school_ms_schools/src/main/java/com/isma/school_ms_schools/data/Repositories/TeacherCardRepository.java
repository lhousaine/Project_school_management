package com.isma.school_ms_schools.data.Repositories;

import com.isma.school_ms_schools.data.Entities.TeacherCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherCardRepository extends JpaRepository<TeacherCard, Long> {
    public List<TeacherCard> findTeacherCardsByTrainingNameAndLevelName(String trainingName,String levelName);
    public List<TeacherCard> findTeacherCardByTeacher_FirstNameAndTeacher_LastName(String teacherFirstName,String teacherLastName);
    public List<TeacherCard> findTeacherCardsByTeacher_Id(Long idTeacher);
}
