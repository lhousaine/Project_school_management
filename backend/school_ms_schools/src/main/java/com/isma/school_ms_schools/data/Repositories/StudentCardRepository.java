package com.isma.school_ms_schools.data.Repositories;

import com.isma.school_ms_schools.data.Entities.StudentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCardRepository extends JpaRepository<StudentCard,Long> {
    public StudentCard findStudentCardByStudent_Id(Long code);
    public List<StudentCard> findStudentCardsByGroupName(String name);
    public StudentCard findStudentCardByTrainingNameAndLevelNameAndGroupName(String trainingName,
                                                                             String levelName,
                                                                             String groupName);
    public List<StudentCard> findStudentCardByStudent_FirstNameAndStudent_LastName(String firstName,
                                                                                   String lastName);
}
