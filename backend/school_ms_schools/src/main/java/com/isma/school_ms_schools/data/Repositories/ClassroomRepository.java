package com.isma.school_ms_schools.data.Repositories;

import com.isma.school_ms_schools.data.Entities.Classroom;
import com.isma.school_ms_schools.core.helpers.ClassroomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom,String> {
     public List<Classroom> findClassroomByCapacityGreaterThan(int capcity);
     public List<Classroom> findClassroomByStatus(ClassroomStatus status);
}
