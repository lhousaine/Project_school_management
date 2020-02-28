package com.isma.school_ms_schools.service.iservices;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.SessionDTO;
import com.isma.school_ms_schools.data.Entities.Session;

import java.util.List;

public interface ISessionService extends IAbstractService<Long, SessionDTO> {
    public SessionDTO findSessionByClassroom_Code(String code) throws NoDataFoundException;
    public List<SessionDTO> findSessionsByTeacher_Id(Long idTeacher) throws NoDataFoundException;
    public List<SessionDTO> findSessionsBySubjectName(String subjectName) throws NoDataFoundException;
}
