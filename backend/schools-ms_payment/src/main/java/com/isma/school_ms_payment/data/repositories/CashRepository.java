package com.isma.school_ms_payment.data.repositories;

import com.isma.school_ms_payment.data.entities.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashRepository extends JpaRepository<Cash,Long> {

}
