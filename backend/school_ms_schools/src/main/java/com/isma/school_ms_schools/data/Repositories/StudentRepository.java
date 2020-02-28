package com.isma.school_ms_schools.data.Repositories;

import com.isma.school_ms_schools.data.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  public Student findStudentByStudentCard_Id(Long idStuCard);
  public Student findStudentByContact_Email(String email);
  public Student findStudentByContact_Phone(String phone);
  public Student findStudentByParent_Id(Long idParent);
}
