package com.isma.school_ms_schools.data.Converters.ImplConverters;

import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.GroupDTO;
import com.isma.school_ms_schools.data.Entities.Group;
import com.isma.school_ms_schools.data.Repositories.TimeTableRepository;
import com.isma.school_ms_schools.data.Repositories.TrainingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("ImplGroupConverter")
public class ImplGroupConverter implements IConverter<Group, GroupDTO> {
    private final TrainingRepository trainingRepository;
    private final TimeTableRepository timeTableRepository;
    private final ModelMapper modelMapper;

    public ImplGroupConverter(TrainingRepository trainingRepository, TimeTableRepository timeTableRepository, ModelMapper modelMapper) {
        this.trainingRepository = trainingRepository;
        this.timeTableRepository = timeTableRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GroupDTO convertToDto(Group group) {
        GroupDTO groupDTO = modelMapper.map(group, GroupDTO.class);
        if (group.getTraining() != null)
            groupDTO.setTrainingName(group.getTraining().getName());
        if (group.getTimeTable() != null)
            groupDTO.setCodeTimeTable(group.getTimeTable().getCode());
        groupDTO.setStudentCount(group.getStudents().size());
        return groupDTO;
    }

    @Override
    public List<GroupDTO> convertListToListDto(List<Group> groups) {
        List<GroupDTO> groupDTOS = new ArrayList<>();
        for (Group group : groups) {
            groupDTOS.add(this.convertToDto(group));
        }
        return groupDTOS;
    }

    @Override
    public Group convertToEntity(GroupDTO groupDTO) {
        Group group=modelMapper.map(groupDTO,Group.class);
        if(groupDTO.getName()!=null)
            group.setTraining(trainingRepository.findTrainingByName(groupDTO.getTrainingName()));
        if(groupDTO.getCodeTimeTable()!=null)
            group.setTimeTable(timeTableRepository.getOne(groupDTO.getCodeTimeTable()));
        return group;
    }

    @Override
    public List<Group> convertListDtoToListEntity(List<GroupDTO> groupDTOS) {
        List<Group> groups = new ArrayList<>();
        Group group;
        for (GroupDTO groupDTO : groupDTOS) {
            group = modelMapper.map(groupDTO, Group.class);
            groups.add(group);
        }
        return groups;
    }
}
