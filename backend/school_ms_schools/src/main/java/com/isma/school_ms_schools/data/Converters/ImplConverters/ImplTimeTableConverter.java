package com.isma.school_ms_schools.data.Converters.ImplConverters;

import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.GroupDTO;
import com.isma.school_ms_schools.data.Dto.TimeTableDTO;
import com.isma.school_ms_schools.data.Entities.TimeTable;
import com.isma.school_ms_schools.data.Repositories.GroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("ImplTimeTableConverter")
public class ImplTimeTableConverter implements IConverter<TimeTable,TimeTableDTO> {
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;

    public ImplTimeTableConverter(GroupRepository groupRepository, ModelMapper modelMapper) {
        this.groupRepository = groupRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TimeTableDTO convertToDto(TimeTable timeTable) {
        TimeTableDTO timeTableDTO=modelMapper.map(timeTable,TimeTableDTO.class);
        timeTableDTO.setGroupName(timeTable.getGroup().getName());
        return timeTableDTO;
    }

    @Override
    public List<TimeTableDTO> convertListToListDto(List<TimeTable> timeTables) {
        List<TimeTableDTO> timeTableDTOS=new ArrayList<>();
        TimeTableDTO timeTableDTO;
        for (TimeTable timeTable:timeTables) {
            timeTableDTO=modelMapper.map(timeTable,TimeTableDTO.class);
            timeTableDTO.setGroupName(timeTable.getGroup().getName());
            timeTableDTOS.add(timeTableDTO);
        }
        return timeTableDTOS;
    }

    @Override
    public TimeTable convertToEntity(TimeTableDTO timeTableDTO) {
        TimeTable timeTable=modelMapper.map(timeTableDTO,TimeTable.class);
        timeTable.setGroup(groupRepository.findGroupByName(timeTableDTO.getGroupName()));
        return timeTable;
    }

    @Override
    public List<TimeTable> convertListDtoToListEntity(List<TimeTableDTO> timeTableDTOS) {
        List<TimeTable> timeTables=new ArrayList<>();
        TimeTable timeTable;
        for (TimeTableDTO timeTableDTO:timeTableDTOS) {
            timeTable=modelMapper.map(timeTableDTO,TimeTable.class);
            timeTables.add(timeTable);
        }
        return timeTables;
    }
}
