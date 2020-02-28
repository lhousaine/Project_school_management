package com.isma.school_ms_schools.service.iservices;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.SchoolDTO;

public interface ISchoolService extends IAbstractService<Long, SchoolDTO> {
    public SchoolDTO findSchoolByName(String name) throws NoDataFoundException;
}
