package com.isma.school_ms_schools.data.Converters.ImplConverters;

import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.BranchDTO;
import com.isma.school_ms_schools.data.Dto.SchoolDTO;
import com.isma.school_ms_schools.data.Entities.Branch;
import com.isma.school_ms_schools.data.Entities.School;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("ImplSchoolConverter")
public class ImplSchoolConverter implements IConverter<School,SchoolDTO>{
    private final ModelMapper modelMapper;
    private final IConverter<Branch, BranchDTO> branchConverter;
    public ImplSchoolConverter(ModelMapper modelMapper, IConverter<Branch, BranchDTO> branchConverter) {
        this.modelMapper = modelMapper;
        this.branchConverter = branchConverter;
    }

    @Override
    public SchoolDTO convertToDto(School school) {
        SchoolDTO schoolDTO=modelMapper.map(school,SchoolDTO.class);
        return schoolDTO;
    }

    @Override
    public List<SchoolDTO> convertListToListDto(List<School> schools) {
        List<SchoolDTO> schoolDTOS=new ArrayList<>();
        for (School school:schools) {
            schoolDTOS.add(this.convertToDto(school));
        }
        return schoolDTOS;
    }

    @Override
    public School convertToEntity(SchoolDTO schoolDTO) {
        return modelMapper.map(schoolDTO,School.class);
    }

    @Override
    public List<School> convertListDtoToListEntity(List<SchoolDTO> schoolDTOS) {
        List<School> schools=new ArrayList<>();
        School school;
        for (SchoolDTO schoolDTO:schoolDTOS) {
            school=modelMapper.map(schoolDTO,School.class);
            schools.add(school);
        }
        return schools;
    }
}
