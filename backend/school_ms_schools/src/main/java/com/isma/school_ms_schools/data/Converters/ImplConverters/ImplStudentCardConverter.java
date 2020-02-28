package com.isma.school_ms_schools.data.Converters.ImplConverters;

import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.StudentCardDTO;
import com.isma.school_ms_schools.data.Entities.StudentCard;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("ImplStudentCardConverter")
public class ImplStudentCardConverter implements IConverter<StudentCard, StudentCardDTO> {
    private final ModelMapper modelMapper;

    public ImplStudentCardConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentCardDTO convertToDto(StudentCard studentCard) {
        StudentCardDTO studentCardDTO=modelMapper.map(studentCard,StudentCardDTO.class);
        studentCardDTO.setStudentCode(studentCard.getStudent().getId()+"");
        studentCardDTO.setStudentFullName(studentCard.getStudent().getFirstName()+" "+studentCard.getStudent().getLastName());
        return studentCardDTO;
    }

    @Override
    public List<StudentCardDTO> convertListToListDto(List<StudentCard> studentCards) {
        List<StudentCardDTO> studentCardDTOS=new ArrayList<>();
        for (StudentCard studentCard:studentCards) {
            studentCardDTOS.add(this.convertToDto(studentCard));
        }
        return studentCardDTOS;
    }

    @Override
    public StudentCard convertToEntity(StudentCardDTO studentCardDTO) {
        return modelMapper.map(studentCardDTO,StudentCard.class);
    }

    @Override
    public List<StudentCard> convertListDtoToListEntity(List<StudentCardDTO> studentCardDTOS) {
        List<StudentCard> studentCards=new ArrayList<>();
        StudentCard studentCard;
        for (StudentCardDTO studentCardDTO:studentCardDTOS) {
            studentCard=modelMapper.map(studentCardDTO,StudentCard.class);
            studentCards.add(studentCard);
        }
        return studentCards;
    }
}
