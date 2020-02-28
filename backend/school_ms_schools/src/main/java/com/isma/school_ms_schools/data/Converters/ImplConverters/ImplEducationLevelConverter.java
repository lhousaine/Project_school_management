package com.isma.school_ms_schools.data.Converters.ImplConverters;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.EducationLevelDTO;
import com.isma.school_ms_schools.data.Dto.TrainingDTO;
import com.isma.school_ms_schools.data.Entities.EducationLevel;
import com.isma.school_ms_schools.data.Entities.Training;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("ImplEducationLevelConverter")
public class ImplEducationLevelConverter implements IConverter<EducationLevel, EducationLevelDTO> {
    private final ModelMapper modelMapper;
    private final IConverter<Training, TrainingDTO> trainingConverter;

    public ImplEducationLevelConverter(ModelMapper modelMapper, IConverter<Training, TrainingDTO> trainingConverter) {
        this.modelMapper = modelMapper;
        this.trainingConverter = trainingConverter;
    }

    @Override
    public EducationLevelDTO convertToDto(EducationLevel educationLevel) {
        EducationLevelDTO educationLevelDTO = modelMapper.map(educationLevel, EducationLevelDTO.class);
        if (educationLevel.getTrainings() != null)
            educationLevelDTO.setTrainings(trainingConverter.convertListToListDto(educationLevel.getTrainings()));
        return educationLevelDTO;
    }

    @Override
    public List<EducationLevelDTO> convertListToListDto(List<EducationLevel> educationLevels) {
        List<EducationLevelDTO> educationLevelDTOS = new ArrayList<>();
        for (EducationLevel educationLevel : educationLevels) {
                 educationLevelDTOS.add(this.convertToDto(educationLevel));
        }
        return educationLevelDTOS;
    }
    @Override
    public EducationLevel convertToEntity(EducationLevelDTO educationLevelDTO) throws NoDataFoundException {
        EducationLevel educationLevel=modelMapper.map(educationLevelDTO, EducationLevel.class);
        educationLevel.setTrainings(new ArrayList<>());
        if(educationLevelDTO.getTrainings()!=null && !educationLevelDTO.getTrainings().isEmpty()) {
            List<Training> trainings=trainingConverter.convertListDtoToListEntity(educationLevelDTO.getTrainings());
            for (Training training:trainings) {
                educationLevel.addTrainig(training);
            }
        }
        return educationLevel;
    }

    @Override
    public List<EducationLevel> convertListDtoToListEntity(List<EducationLevelDTO> educationLevelDTOS) throws NoDataFoundException {
        List<EducationLevel> educationLevels = new ArrayList<>();
        for (EducationLevelDTO educationLevelDTO : educationLevelDTOS) {
            educationLevels.add(this.convertToEntity(educationLevelDTO));
        }
        return educationLevels;
    }
}
