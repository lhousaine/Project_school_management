package com.isma.school_ms_schools.data.Repositories;

import com.isma.school_ms_schools.data.Entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
