package com.isma.school_ms_schools.data.Converters.ImplConverters;

import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.SubjectDTO;
import com.isma.school_ms_schools.data.Entities.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("ImplSubjectConverter")
public class ImplSubjectConverter implements IConverter<Subject,SubjectDTO> {
    private final ModelMapper modelMapper;

    public ImplSubjectConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SubjectDTO convertToDto(Subject subject){
        return modelMapper.map(subject,SubjectDTO.class);
    }

    @Override
    public List<SubjectDTO> convertListToListDto(List<Subject> subjects) {
        List<SubjectDTO> subjectDTOS=new ArrayList<>();
        SubjectDTO subjectDTO;
        for (Subject subject:subjects){
            subjectDTO=modelMapper.map(subject,SubjectDTO.class);
            subjectDTOS.add(subjectDTO);
        }
        return subjectDTOS;
    }

    @Override
    public Subject convertToEntity(SubjectDTO subjectDTO) {
        return modelMapper.map(subjectDTO,Subject.class);
    }

    @Override
    public List<Subject> convertListDtoToListEntity(List<SubjectDTO> subjectDTOS){
        List<Subject> subjects=new ArrayList<>();
        Subject subject;
        for (SubjectDTO subjectDTO:subjectDTOS){
            subject=modelMapper.map(subjectDTO,Subject.class);
            subjects.add(subject);
        }
        return subjects;
    }
}
