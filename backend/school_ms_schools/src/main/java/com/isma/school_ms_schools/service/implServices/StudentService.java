package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.ImplConverters.Mapper;
import com.isma.school_ms_schools.data.Dto.StudentDTO;
import com.isma.school_ms_schools.data.Entities.Group;
import com.isma.school_ms_schools.data.Entities.Student;
import com.isma.school_ms_schools.data.Repositories.GroupRepository;
import com.isma.school_ms_schools.data.Repositories.StudentRepository;
import com.isma.school_ms_schools.service.iservices.IStudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static com.isma.school_ms_schools.core.params.GlobalParams.DATEFORMAT;

@Service
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final Mapper mapper;

    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository, Mapper mapper) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.mapper = mapper;
    }

    @Override
    public StudentDTO createNewStudent(Student student) throws DataAlreadyUsed, DateFormatException {
        if (!Pattern.matches(DATEFORMAT, student.getBirthday()) || !Pattern.matches(DATEFORMAT, student.getParent().getBirthday()))
            throw new DateFormatException(student.getBirthday() + "date format not respected");
        Student student1 = studentRepository.findStudentByContact_Email(student.getContact().getEmail());
        if (student1 != null)
            throw new DataAlreadyUsed("the email " + student.getContact().getEmail() + "was already in use");
        student1 = studentRepository.findStudentByContact_Email(student.getContact().getEmail());
        if (student1 != null)
            throw new DataAlreadyUsed("the Phone " + student.getContact().getPhone() + "was already in use");
        student1 = studentRepository.save(student);
        Group group = groupRepository.findGroupByName(student1.getStudentCard().getGroupName());
        group.addStudent(student1);
        groupRepository.save(group);
        return new StudentDTO(student1);
    }

    @Override
    public StudentDTO findStudentByStudentCard_Id(Long idStuCard) {
        return new StudentDTO(studentRepository.findStudentByStudentCard_Id(idStuCard));
    }

    @Override
    public StudentDTO findStudentByParent_Id(Long idParent) throws NoDataFoundException {
        Student student = studentRepository.findStudentByParent_Id(idParent);
        if (student == null)
            throw new NoDataFoundException("No Student assciated with parent iedntified by " + idParent);
        return new StudentDTO(student);
    }

    @Override
    public List<StudentDTO> getAll() throws NoDataFoundException {
        return mapper.toStudentDTOS(studentRepository.findAll());
    }

    @Override
    public StudentDTO getById(Long id) throws NoDataFoundException {
        if (studentRepository.findById(id).isPresent())
            return new StudentDTO(studentRepository.findById(id).get());
        else
            throw new NoDataFoundException("No Student identified by " + id);
    }

    @Override
    public StudentDTO create(StudentDTO studentDTO) throws DataAlreadyUsed, NoDataFoundException {
        Student student = mapper.toStudentEntity(studentDTO);
        return new StudentDTO(studentRepository.save(student));
    }

    @Override
    public boolean update(Long idStudent, StudentDTO studentDTO) throws NoDataFoundException, DataAlreadyUsed {
        Student student = mapper.toStudentEntity(studentDTO);
        Student student1 = studentRepository.findById(idStudent).get();
        if (student1 == null)
            throw new DataAlreadyUsed("No Student was identified by " + idStudent);
        student1.setStudentCard(student.getStudentCard());
        student1.setAddress(student.getAddress());
        try {
            studentRepository.save(student1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long idStudent) throws NoDataFoundException {
        if (!studentRepository.findById(idStudent).isPresent()) {
            throw new NoDataFoundException("No Student identified by " + idStudent);
        }
        try {
            studentRepository.deleteById(idStudent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<StudentDTO> findStudentsByGroup_Id(Long idGroup) throws NoDataFoundException {
      /*  List<Student> students=studentRepository.findStudentsByGroup_Id(idGroup);
        if(students==null)
            throw new NoDataFoundException("No student enrolled in the group identified by "+idGroup);
        return mapper.toStudentDTOS(students);*/
        return null;
    }

}
