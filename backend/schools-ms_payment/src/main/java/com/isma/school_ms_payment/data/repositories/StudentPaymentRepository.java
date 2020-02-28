package com.isma.school_ms_payment.data.repositories;

import com.isma.school_ms_payment.data.entities.StudentPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentPaymentRepository extends JpaRepository<StudentPayment, Long> {
    public List<StudentPayment> findByStudentCode(String id);
    public StudentPayment findByStudentCodeAndMonth(String id, int month);
}
