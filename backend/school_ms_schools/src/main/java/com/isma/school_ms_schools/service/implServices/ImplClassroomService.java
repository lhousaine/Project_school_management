package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.ClassroomDTO;
import com.isma.school_ms_schools.data.Entities.Classroom;
import com.isma.school_ms_schools.core.helpers.ClassroomStatus;
import com.isma.school_ms_schools.data.Repositories.ClassroomRepository;
import com.isma.school_ms_schools.service.iservices.IClassroomService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ImplClassroomService implements IClassroomService {
    private final ClassroomRepository classroomRepository;
    private final IConverter<Classroom, ClassroomDTO> classroomConverter;

    public ImplClassroomService(ClassroomRepository classroomRepository, IConverter<Classroom, ClassroomDTO> classroomConverter) {
        this.classroomRepository = classroomRepository;
        this.classroomConverter = classroomConverter;
    }

    @Override
    public List<ClassroomDTO> getAll() throws NoDataFoundException {
        List<Classroom> classrooms=classroomRepository.findAll();
        if (classrooms==null)
            throw new NoDataFoundException("There is no classroom");
        return classroomConverter.convertListToListDto(classrooms);
    }

    @Override
    public ClassroomDTO getById(String code) throws NoDataFoundException {
        Classroom classroom=classroomRepository.getOne(code);
        if (classroom==null)
            throw new NoDataFoundException("there Classroom identified by "+code);
        return classroomConverter.convertToDto(classroom);
    }

    @Override
    public ClassroomDTO create(ClassroomDTO classroomDTO) throws DataAlreadyUsed, NoDataFoundException, ParseException {
        Classroom classroom=classroomConverter.convertToEntity(classroomDTO);
        Classroom classroom1=classroomRepository.save(classroom);
        return classroomConverter.convertToDto(classroom1);
    }

    @Override
    public boolean update(String codeClassroom, ClassroomDTO classroomDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        Classroom classroom=classroomConverter.convertToEntity(classroomDTO);
        Classroom cl=classroomRepository.getOne(codeClassroom);
        if (cl==null) {
            throw new NoDataFoundException("No Classroom identified by " + codeClassroom);
        }
        cl.setStatus(classroom.getStatus());
        try {
            classroomRepository.save(cl);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String codeClassroom) throws NoDataFoundException {
        Classroom classroom=classroomRepository.getOne(codeClassroom);
        if (classroom==null) {
            throw new NoDataFoundException("No branch identified by " + codeClassroom);
        }
        try{
            classroomRepository.deleteById(codeClassroom);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<ClassroomDTO> findClassroomByCapacityGreaterThan(int capcity) throws NoDataFoundException {
        List<Classroom> classrooms=classroomRepository.findClassroomByCapacityGreaterThan(capcity);
        if (classrooms==null)
            throw new NoDataFoundException("There is no classroom with capacity greater than " +capcity);
        return classroomConverter.convertListToListDto(classrooms);
    }

    @Override
    public List<ClassroomDTO> findClassroomByStatus(ClassroomStatus status) throws NoDataFoundException{
        List<Classroom> classrooms=classroomRepository.findClassroomByStatus(status);
        if (classrooms==null)
            throw new NoDataFoundException("There is no classroom in status of " +status);
        return classroomConverter.convertListToListDto(classrooms);
    }
}
