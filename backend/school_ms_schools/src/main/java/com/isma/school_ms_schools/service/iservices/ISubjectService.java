package com.isma.school_ms_schools.service.iservices;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.SubjectDTO;


public interface ISubjectService extends IAbstractService<Long, SubjectDTO> {
    public SubjectDTO findSubjectByName(String subjectName) throws NoDataFoundException;
}
