package com.isma.school_ms_schools.data.Converters.ImplConverters;

import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.GroupDTO;
import com.isma.school_ms_schools.data.Dto.SubjectDTO;
import com.isma.school_ms_schools.data.Dto.TrainingDTO;
import com.isma.school_ms_schools.data.Entities.Group;
import com.isma.school_ms_schools.data.Entities.Subject;
import com.isma.school_ms_schools.data.Entities.Training;
import com.isma.school_ms_schools.data.Repositories.EducationLevelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("ImplTrainingConverter")
public class ImplTrainingConverter implements IConverter<Training,TrainingDTO> {
    private final IConverter<Group, GroupDTO> groupConverter;
    private final IConverter<Subject, SubjectDTO> subjectConverter;
    private final EducationLevelRepository educationLevelRepository;
    private final ModelMapper modelMapper;

    public ImplTrainingConverter(IConverter<Group, GroupDTO> groupConverter, IConverter<Subject, SubjectDTO> subjectConverter, EducationLevelRepository educationLevelRepository, ModelMapper modelMapper) {
        this.groupConverter = groupConverter;
        this.subjectConverter = subjectConverter;
        this.educationLevelRepository = educationLevelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TrainingDTO convertToDto(Training training) {
        TrainingDTO trainingDTO=modelMapper.map(training,TrainingDTO.class);
        if(training.getGroups()!=null)
            trainingDTO.setGroups(groupConverter.convertListToListDto(training.getGroups()));
        if(training.getGroups()!=null)
            trainingDTO.setSubjects(subjectConverter.convertListToListDto(training.getSubjects()));
      //  trainingDTO.setEducationLevelName(training.getEducationLevel().getName());
        return trainingDTO;
    }

    @Override
    public List<TrainingDTO> convertListToListDto(List<Training> trainings) {
        List<TrainingDTO> trainingDTOS=new ArrayList<>();
        for (Training training:trainings) {
            trainingDTOS.add(this.convertToDto(training));
        }
        return trainingDTOS;
    }

    @Override
    public Training convertToEntity(TrainingDTO trainingDTO) {
        Training training=modelMapper.map(trainingDTO,Training.class);
        training.setEducationLevel(educationLevelRepository.findEducationLevelByName(trainingDTO.getEducationLevelName()));
        return training;
    }

    @Override
    public List<Training> convertListDtoToListEntity(List<TrainingDTO> trainingDTOS) {
        List<Training> trainings=new ArrayList<>();
        for (TrainingDTO trainingDTO:trainingDTOS) {
            trainings.add(modelMapper.map(trainingDTO,Training.class));
        }
        return trainings;
    }
}
