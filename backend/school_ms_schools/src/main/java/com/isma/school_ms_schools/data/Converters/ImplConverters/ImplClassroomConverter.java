package com.isma.school_ms_schools.data.Converters.ImplConverters;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.ClassroomDTO;
import com.isma.school_ms_schools.data.Entities.Classroom;
import com.isma.school_ms_schools.data.Entities.Session;
import com.isma.school_ms_schools.data.Repositories.SessionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("ImplClassroomConverter")
public class ImplClassroomConverter implements IConverter<Classroom,ClassroomDTO> {
    private final ModelMapper modelMapper;

    public ImplClassroomConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ClassroomDTO convertToDto(Classroom classroom) {
        return modelMapper.map(classroom,ClassroomDTO.class);
    }

    @Override
    public List<ClassroomDTO> convertListToListDto(List<Classroom> classrooms) {
        List<ClassroomDTO> classroomDTOS=new ArrayList<>();
        for (Classroom classroom:classrooms){
            classroomDTOS.add(this.convertToDto(classroom));
        }
        return classroomDTOS;
    }

    @Override
    public Classroom convertToEntity(ClassroomDTO classroomDTO) throws NoDataFoundException {
        return modelMapper.map(classroomDTO,Classroom.class);
    }

    @Override
    public List<Classroom> convertListDtoToListEntity(List<ClassroomDTO> classroomDTOS) throws NoDataFoundException {
        List<Classroom> classrooms=new ArrayList<>();
        for (ClassroomDTO classroomDTO:classroomDTOS){
            classrooms.add(this.convertToEntity(classroomDTO));
        }
        return classrooms;
    }
}
