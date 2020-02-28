package com.isma.school_ms_schools.service.iservices;


import com.isma.school_ms_schools.data.Dto.TeacherCardDTO;
import com.isma.school_ms_schools.data.Dto.TeacherDTO;

import java.util.List;

public interface ITeacherService extends IAbstractService<Long, TeacherDTO>{
    public Boolean addTeacherCardToTeacher(TeacherCardDTO teacherCardDTO);
}
