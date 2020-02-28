package api.ms.users.ms.users.repositories;

import api.ms.users.ms.users.models.StudentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCardRepository extends JpaRepository<StudentCard, Long> {
    public StudentCard findStudentCardByTrainingNameAndLevelNameAndGroupName(String trainingName,
                                                                             String levelName,
                                                                             String groupName);

    public List<StudentCard> findStudentCardByStudent_FirstNameAndStudent_LastName(String firstName,
                                                                                   String lastName);
}
