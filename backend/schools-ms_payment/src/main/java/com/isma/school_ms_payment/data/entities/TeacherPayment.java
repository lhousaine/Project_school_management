package com.isma.school_ms_payment.data.entities;

import com.isma.school_ms_payment.core.enums.PaymentStateEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacherPayments")
public class TeacherPayment {
    public static Double amount= 1000D;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teacherCode;
    private int month;
    private PaymentStateEnum paymentStateEnum;
    @Transient
    private List<TeacherCard> teacherCards;
    private Double amountLeft;
}
