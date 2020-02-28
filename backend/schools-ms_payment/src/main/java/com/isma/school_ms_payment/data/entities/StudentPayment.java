package com.isma.school_ms_payment.data.entities;

import com.isma.school_ms_payment.core.enums.PaymentStateEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studentPayments")
public class StudentPayment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int month;
    private String studentCode;
    @JoinColumn(name = "studentCardId", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private StudentCard studentCard;
    private PaymentStateEnum paymentStateEnum;
    private Double amountLeft=1000D;
    /*
        @OneToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "payment-id")
    private Payment payment;
     */
}
