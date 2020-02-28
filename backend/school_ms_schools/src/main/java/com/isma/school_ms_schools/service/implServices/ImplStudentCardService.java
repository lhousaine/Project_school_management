package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Converters.ImplConverters.Mapper;
import com.isma.school_ms_schools.data.Dto.StudentCardDTO;
import com.isma.school_ms_schools.data.Entities.StudentCard;
import com.isma.school_ms_schools.data.Repositories.StudentCardRepository;
import com.isma.school_ms_schools.service.iservices.IStudentCardService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class ImplStudentCardService implements IStudentCardService {
    private final StudentCardRepository studentCardRepository;
    private final IConverter<StudentCard, StudentCardDTO> studentCardConverter;

    public ImplStudentCardService(StudentCardRepository studentCardRepository, @Qualifier("ImplStudentCardConverter") IConverter<StudentCard, StudentCardDTO> studentCardConverter) {
        this.studentCardRepository = studentCardRepository;
        this.studentCardConverter = studentCardConverter;
    }

    @Override
    public List<StudentCardDTO> getAll() throws NoDataFoundException {
        List<StudentCard> studentCards=studentCardRepository.findAll();
        if (studentCards==null)
            throw new NoDataFoundException("There is no studentsCards");
        return studentCardConverter.convertListToListDto(studentCards);
    }

    @Override
    public StudentCardDTO getById(Long idStuCard) throws NoDataFoundException {
        StudentCard studentCard=studentCardRepository.getOne(idStuCard);
        if (studentCard==null)
            throw new NoDataFoundException("there is no studentCrad identified by "+idStuCard);
        return studentCardConverter.convertToDto(studentCard);
    }

    @Override
    public StudentCardDTO create(StudentCardDTO studentCardDTO) throws DataAlreadyUsed, NoDataFoundException, ParseException {
        StudentCard studentCard=studentCardConverter.convertToEntity(studentCardDTO);
        studentCard=studentCardRepository.save(studentCard);
        return studentCardConverter.convertToDto(studentCard);
    }

    @Override
    public boolean update(Long idStuCard, StudentCardDTO studentCardDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        StudentCard studentCard=studentCardConverter.convertToEntity(studentCardDTO);
        StudentCard studentCard1=studentCardRepository.getOne(idStuCard);
        if (studentCard1==null) {
            throw new NoDataFoundException("No StudentCard identified by " + idStuCard);
        }
        studentCard1.setGroupName(studentCard.getGroupName());
        studentCard1.setTrainingName(studentCard.getTrainingName());
        studentCard1.setLevelName(studentCard.getLevelName());
        try {
            studentCardRepository.save(studentCard1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long idStuCard) throws NoDataFoundException {
        if (!studentCardRepository.findById(idStuCard).isPresent()) {
            throw new NoDataFoundException("No StudentCard identified by " + idStuCard);
        }
        try{
            studentCardRepository.deleteById(idStuCard);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public StudentCardDTO findStudentCardByStudentCode(Long code) throws NoDataFoundException {
        StudentCard studentCard=studentCardRepository.findStudentCardByStudent_Id(code);
        if (studentCard==null)
            throw new NoDataFoundException("There is No StudentCard identified by " +code);
        return studentCardConverter.convertToDto(studentCard);
    }

    @Override
    public List<StudentCardDTO> findStudentCardsByGroupName(String name) throws NoDataFoundException {
        List<StudentCard> studentCards=studentCardRepository.findStudentCardsByGroupName(name);
        if (studentCards==null)
            throw new NoDataFoundException("There is No Student in the Branch named " +name);
        return studentCardConverter.convertListToListDto(studentCards);
    }
    public List<StudentCardDTO> getStudentCardByFullName(String firstName,
                                                         String lastName) {
        return studentCardConverter.convertListToListDto(studentCardRepository.findStudentCardByStudent_FirstNameAndStudent_LastName(firstName, lastName));
    }

    public StudentCardDTO getStudentCardByClassInfo(String trainingName,
                                                    String levelName,
                                                    String groupName) {
        return new StudentCardDTO(studentCardRepository.findStudentCardByTrainingNameAndLevelNameAndGroupName(trainingName, levelName, groupName));
    }
}
