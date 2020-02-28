package api.ms.users.ms.users.services.iservices;

import api.ms.users.ms.users.models.Teacher;

import java.util.List;

public interface ITeacherService {
    public List<Teacher> getTeachers();
    public Teacher saveTeacher(Teacher student);
    public void deleteTeacher(Long id);
    public Teacher updateTeacher(Teacher teacher);
}
