package com.isma.school_ms_payment.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudentCard{
    @Id
    private long id;
    private String studentCode;
    private String levelName;
    private String trainingName;
    private String groupName;
    @Transient
    private Training training;
}
