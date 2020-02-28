package api.ms.users.ms.users.services.servicesimpli;

import api.ms.users.ms.users.models.Teacher;
import api.ms.users.ms.users.repositories.TeacherRepository;
import api.ms.users.ms.users.services.iservices.ITeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements ITeacherService {
    public TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher saveTeacher(Teacher student) {
        return teacherRepository.save(student);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
