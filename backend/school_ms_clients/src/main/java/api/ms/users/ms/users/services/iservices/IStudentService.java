package api.ms.users.ms.users.services.iservices;

import api.ms.users.ms.users.models.Student;
import api.ms.users.ms.users.repositories.StudentRepository;

import java.util.List;

public interface IStudentService {
    public List<Student> getStudents();
    public Student saveStudent(Student student);
    public void deleteStudent(Long id);
    public Student updateStudent(Student student);
}
