package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.FailedToSaveDataException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.ImplConverters.Mapper;
import com.isma.school_ms_schools.data.Dto.TeacherCardDTO;
import com.isma.school_ms_schools.data.Dto.TeacherDTO;
import com.isma.school_ms_schools.data.Entities.Teacher;
import com.isma.school_ms_schools.data.Entities.TeacherCard;
import com.isma.school_ms_schools.data.Repositories.TeacherRepository;
import com.isma.school_ms_schools.service.iservices.ITeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static com.isma.school_ms_schools.core.params.GlobalParams.DATEFORMAT;

@Service
public class TeacherService implements ITeacherService {
    private final TeacherRepository teacherRepository;
    private final Mapper mapper;

    public TeacherService(TeacherRepository teacherRepository, Mapper mapper) {
        this.teacherRepository = teacherRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TeacherDTO> getAll() throws NoDataFoundException {
        return mapper.toTeacherDTOs(teacherRepository.findAll());
    }

    @Override
    public TeacherDTO getById(Long id) throws NoDataFoundException {
        if (teacherRepository.findById(id).isPresent())
            return new TeacherDTO(teacherRepository.findById(id).get());
        else throw new RuntimeException("Person not found");
    }

    @Override
    public TeacherDTO create(TeacherDTO teacherDTO) throws DataAlreadyUsed, NoDataFoundException, DateFormatException {
        Teacher teacher = mapper.toTeacherEntity(teacherDTO);
        if (!Pattern.matches(DATEFORMAT, teacher.getBirthday()))
            throw new DateFormatException(teacher.getBirthday() + "date format not respected");
        return new TeacherDTO(teacherRepository.save(teacher));
    }

    @Override
    public boolean update(Long idTeacher, TeacherDTO teacherDTO) throws NoDataFoundException, DataAlreadyUsed {
        return false;
    }

    @Override
    public boolean delete(Long idTeacher) throws NoDataFoundException {
        if (!teacherRepository.findById(idTeacher).isPresent()) {
            throw new NoDataFoundException("No Teacher identified by " + idTeacher);
        }
        try {
            teacherRepository.deleteById(idTeacher);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean addTeacherCardToTeacher(TeacherCardDTO teacherCardDTO) {
        TeacherCard teacherCard = mapper.toTeacherCardEntity(teacherCardDTO);
        Teacher teacher = teacherRepository.getOne(teacherCard.getId());
        teacher.addTeacherCard(teacherCard);
        try {
            teacherRepository.save(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
