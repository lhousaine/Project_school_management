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
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "timeTables")
public class TimeTable implements Serializable {
    @Id
    @Column(length = 20)
    private String code;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Fetch(FetchMode.SELECT)
    @OneToOne(mappedBy = "timeTable")
    private Group group;

    @OneToMany(cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<Session> sessions = new ArrayList<>();

    public TimeTable(String code) {
        this.code = code;
        this.createdAt=new Date();
    }
    public void addSession(Session session){
        this.sessions.add(session);
    }
    public void removeSession(Session session){
        this.sessions.remove(session);
    }
}
