package api.ms.users.ms.users.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class StudentCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trainingName;
    private String levelName;
    private String groupName;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public StudentCard(String trainingName, String levelName, String groupName) {
        this.trainingName = trainingName;
        this.levelName = levelName;
        this.groupName = groupName;
    }
}
