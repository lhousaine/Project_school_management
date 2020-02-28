package com.isma.school_ms_schools.service.iservices;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.TimeTableDTO;

public interface ITimeTableService extends IAbstractService<String, TimeTableDTO> {
    public TimeTableDTO findTimeTableByGroupName(String groupName) throws NoDataFoundException;
}
