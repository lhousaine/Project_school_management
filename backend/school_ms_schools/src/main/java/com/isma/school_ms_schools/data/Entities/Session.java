package com.isma.school_ms_schools.data.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessions")
public class Session implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double duration;
    @Temporal(TemporalType.TIMESTAMP)
    private Date started_At;
    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @OneToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "classroom_code")
    private Classroom classroom;
    @OneToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "group_id",
            referencedColumnName = "id")
    private Group group;
    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Session(double duration, Date started_At, Subject subject, Classroom classroom, Teacher teacher) {
        this.duration = duration;
        this.started_At = started_At;
        this.subject = subject;
        this.classroom = classroom;
        this.teacher = teacher;
    }
}
