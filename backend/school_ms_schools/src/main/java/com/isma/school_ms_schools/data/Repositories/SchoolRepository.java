package com.isma.school_ms_schools.data.Repositories;

import com.isma.school_ms_schools.data.Entities.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School,Long> {
    public School findSchoolByName(String name);
}
