package com.isma.school_ms_schools.data.Entities;

import com.isma.school_ms_schools.core.helpers.ClassroomStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "classrooms")
public class Classroom implements Serializable {
    @Id
    @Column(length = 20)
    private String code;
    private  double width;
    private double height;
    private int capacity;
    private ClassroomStatus status;

    public Classroom(String code, double width, double height, int capacity) {
        this.code = code;
        this.width = width;
        this.height = height;
        this.capacity = capacity;
        this.status = ClassroomStatus.EMPTY;
    }
}
