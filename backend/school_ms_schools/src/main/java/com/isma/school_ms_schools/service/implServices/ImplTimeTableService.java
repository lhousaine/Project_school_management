package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.TimeTableDTO;
import com.isma.school_ms_schools.data.Entities.TimeTable;
import com.isma.school_ms_schools.data.Repositories.TimeTableRepository;
import com.isma.school_ms_schools.service.iservices.ITimeTableService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class ImplTimeTableService implements ITimeTableService {
    private final IConverter<TimeTable, TimeTableDTO> timeTableConverter;
    private final TimeTableRepository timeTableRepository;

    public ImplTimeTableService(@Qualifier("ImplTimeTableConverter") IConverter<TimeTable, TimeTableDTO> timeTableConverter, TimeTableRepository timeTableRepository) {
        this.timeTableConverter = timeTableConverter;
        this.timeTableRepository = timeTableRepository;
    }

    @Override
    public List<TimeTableDTO> getAll() throws NoDataFoundException {
        List<TimeTable> timeTables=timeTableRepository.findAll();
        if (timeTables==null)
            throw new NoDataFoundException("There is no timeTable");
        return timeTableConverter.convertListToListDto(timeTables);
    }

    @Override
    public TimeTableDTO getById(String code) throws NoDataFoundException {
        TimeTable timeTable=timeTableRepository.getOne(code);
        if (timeTable==null)
            throw new NoDataFoundException("there is no timeTable identified by "+code);
        return timeTableConverter.convertToDto(timeTable);
    }

    @Override
    public TimeTableDTO create(TimeTableDTO timeTableDTO) throws DataAlreadyUsed, NoDataFoundException, ParseException {
        TimeTable timeTable=timeTableConverter.convertToEntity(timeTableDTO);
        TimeTable timeTable1=timeTableRepository.getOne(timeTableDTO.getCode());
        if(timeTable1!=null)
            throw new DataAlreadyUsed("TimeTable "+timeTable.getCode()+" Code already in use");
        timeTable1=timeTableRepository.save(timeTable);
        return timeTableConverter.convertToDto(timeTable1);
    }

    @Override
    public boolean update(String code, TimeTableDTO timeTableDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        TimeTable timeTable=timeTableConverter.convertToEntity(timeTableDTO);
        TimeTable timeTable1=timeTableRepository.getOne(code);
        if (timeTable1==null) {
            throw new NoDataFoundException("No timeTable identified by " + code);
        }
        timeTable1.setGroup(timeTable.getGroup());
        try {
            timeTableRepository.save(timeTable1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String code) throws NoDataFoundException {
        if (!timeTableRepository.findById(code).isPresent()) {
            throw new NoDataFoundException("No TimeTable identified by " + code);
        }
        try{
            timeTableRepository.deleteById(code);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public TimeTableDTO findTimeTableByGroupName(String groupName) throws NoDataFoundException {
        TimeTable timeTable=timeTableRepository.findTimeTableByGroupName(groupName);
        if (timeTable==null)
            throw new NoDataFoundException("There is No TimeTable affected to the group named " +groupName);
        return timeTableConverter.convertToDto(timeTable);
    }
}
