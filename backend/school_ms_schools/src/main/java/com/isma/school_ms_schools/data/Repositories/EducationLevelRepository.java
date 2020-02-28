package com.isma.school_ms_schools.data.Repositories;

import com.isma.school_ms_schools.data.Entities.EducationLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationLevelRepository extends JpaRepository<EducationLevel,Long> {
    public EducationLevel findEducationLevelByName(String name);
}
