package com.isma.school_ms_payment.data.repositories;

import com.isma.school_ms_payment.data.entities.TeacherPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherPaymentRepository extends JpaRepository<TeacherPayment,Long> {

}
