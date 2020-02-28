package com.isma.school_ms_payment.data.repositories;

import com.isma.school_ms_payment.data.entities.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckRepository extends JpaRepository<Check,Long> {

}
