package com.isma.school_ms_schools.service.iservices;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.EducationLevelDTO;

import java.util.List;

public interface IEducationLevelService extends IAbstractService<Long, EducationLevelDTO> {
    public EducationLevelDTO findEducationLevelByName(String name) throws NoDataFoundException;
}
