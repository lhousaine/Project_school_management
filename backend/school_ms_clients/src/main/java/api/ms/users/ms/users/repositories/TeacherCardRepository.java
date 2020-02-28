package api.ms.users.ms.users.repositories;

import api.ms.users.ms.users.models.TeacherCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherCardRepository extends JpaRepository<TeacherCard, Long> {
    public TeacherCard findTeacherCardByTrainingNameAndLevelNameAndCourseAndGroupName(String trainingName,
                                                                                            String levelName,
                                                                                            String course,
                                                                                            String groupName);

    public List<TeacherCard> findTeacherCardByTeacher_FirstNameAndTeacher_LastName(String teacherFirstName,
                                                                                   String teacherLastName);
}
