package com.isma.school_ms_schools.data.Repositories;

import com.isma.school_ms_schools.data.Entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    public Session findSessionsByClassroom_Code(String code);
    public List<Session> findSessionsByTeacher_Id(Long idTeacher);
    public List<Session> findSessionsBySubjectName(String subjectName);
}
