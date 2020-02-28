package com.isma.school_ms_schools.data.Repositories;

import com.isma.school_ms_schools.data.Entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    public Subject findSubjectByName(String subjectName);
}
