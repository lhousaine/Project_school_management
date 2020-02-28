package com.isma.school_ms_payment.data.repositories;

import com.isma.school_ms_payment.data.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit,Long> {

}
