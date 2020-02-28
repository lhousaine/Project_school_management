package com.isma.school_ms_schools.service.iservices;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.TeacherCardDTO;
import com.isma.school_ms_schools.data.Entities.TeacherCard;

import java.util.List;

public interface ITeacherCardService extends IAbstractService<Long,TeacherCardDTO>{
    public List<TeacherCardDTO> getTeacherCardByTeacherFullName(String firstName,
                                                                String lastName);
    public List<TeacherCardDTO> getTeacherCardByClassInfo(String trainingName,
                                                    String levelName);
    public List<TeacherCardDTO> findTeacherCardsByTeacher_Id(Long idTeacher) throws NoDataFoundException;
}
