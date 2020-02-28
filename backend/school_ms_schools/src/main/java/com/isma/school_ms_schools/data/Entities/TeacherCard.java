package com.isma.school_ms_schools.data.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacherCards")
public class TeacherCard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trainingName;
    private String levelName;
    @JoinColumn(name = "teacher_id")
    @ManyToOne
    private Teacher teacher;
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Session> sessions=new ArrayList<>();

    public TeacherCard(String trainingName, String levelName) {
        this.trainingName = trainingName;
        this.levelName = levelName;
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    public void removeSession(Session session) {
        this.sessions.add(session);
    }

}
