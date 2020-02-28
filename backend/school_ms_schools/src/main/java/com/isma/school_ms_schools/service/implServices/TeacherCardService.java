package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.ImplConverters.Mapper;
import com.isma.school_ms_schools.data.Dto.PersonDTO;
import com.isma.school_ms_schools.data.Dto.TeacherCardDTO;
import com.isma.school_ms_schools.data.Entities.Person;
import com.isma.school_ms_schools.data.Entities.StudentCard;
import com.isma.school_ms_schools.data.Entities.TeacherCard;
import com.isma.school_ms_schools.data.Repositories.TeacherCardRepository;
import com.isma.school_ms_schools.service.iservices.ITeacherCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherCardService implements ITeacherCardService {
    private final TeacherCardRepository teacherCardRepository;
    private final Mapper mapper;

    public TeacherCardService(TeacherCardRepository teacherCardRepository, Mapper mapper) {
        this.teacherCardRepository = teacherCardRepository;
        this.mapper = mapper;
    }

    public List<TeacherCardDTO> getAll() {
        return mapper.toTeacherCardDTOs(teacherCardRepository.findAll());
    }

    @Override
    public TeacherCardDTO getById(Long id) throws NoDataFoundException {
            if (teacherCardRepository.findById(id).isPresent())
                return new TeacherCardDTO(teacherCardRepository.findById(id).get());
            else throw new NoDataFoundException("No TeacherCard identified by "+id);
    }

    @Override
    public TeacherCardDTO create(TeacherCardDTO teacherCardDTO) throws DataAlreadyUsed, NoDataFoundException {
        TeacherCard teacherCard =mapper.toTeacherCardEntity(teacherCardDTO);
        return new TeacherCardDTO(teacherCardRepository.save(teacherCard));
    }

    @Override
    public boolean update(Long aLong, TeacherCardDTO teacherCardDTO) throws NoDataFoundException, DataAlreadyUsed {
        return false;
    }

    @Override
    public boolean delete(Long idTeacherCard) throws NoDataFoundException {
        if (!teacherCardRepository.findById(idTeacherCard).isPresent()) {
            throw new NoDataFoundException("No TeacherCard identified by " + idTeacherCard);
        }
        try{
            teacherCardRepository.deleteById(idTeacherCard);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<TeacherCardDTO> getTeacherCardByTeacherFullName(String firstName, String lastName) {
        return mapper.toTeacherCardDTOs(
                teacherCardRepository.findTeacherCardByTeacher_FirstNameAndTeacher_LastName(firstName, lastName)
        );
    }

    public TeacherCard getTeacherCardByTeacherId(Long id) {
        return teacherCardRepository.findTeacherCardsByTeacher_Id(id).get(0);
    }

    // with all the card information except teacher
    public List<TeacherCardDTO> getTeacherCardByClassInfo(String trainingName,String levelName) {
        return mapper.toTeacherCardDTOs(
                teacherCardRepository.findTeacherCardsByTrainingNameAndLevelName(trainingName, levelName));
    }
    @Override
    public List<TeacherCardDTO> findTeacherCardsByTeacher_Id(Long idTeacher) throws NoDataFoundException {
        List<TeacherCard> teacherCards=teacherCardRepository.findTeacherCardsByTeacher_Id(idTeacher);
        if(teacherCards==null)
             throw new NoDataFoundException("No TeacherCard identified by " +idTeacher);
        return mapper.toTeacherCardDTOs(teacherCards);
    }
}
