package api.ms.users.ms.users.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class TeacherCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trainingName;
    private String levelName;
    private String groupName;
    private String courseName;
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    public TeacherCard(String trainingName, String levelName, String groupName, String course) {
        this.trainingName = trainingName;
        this.levelName = levelName;
        this.groupName = groupName;
        this.courseName = course;
    }
}
