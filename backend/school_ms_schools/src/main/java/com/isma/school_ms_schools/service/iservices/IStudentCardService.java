package com.isma.school_ms_schools.service.iservices;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.StudentCardDTO;

import java.util.List;

public interface IStudentCardService extends IAbstractService<Long, StudentCardDTO> {
    public StudentCardDTO findStudentCardByStudentCode(Long code) throws NoDataFoundException;
    public List<StudentCardDTO> findStudentCardsByGroupName(String name) throws NoDataFoundException;
    public List<StudentCardDTO> getStudentCardByFullName(String firstName,String lastName);
    public StudentCardDTO getStudentCardByClassInfo(String trainingName,String levelName,String groupName);
}
