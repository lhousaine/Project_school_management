package com.isma.school_ms_schools.service.iservices;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.ClassroomDTO;
import com.isma.school_ms_schools.core.helpers.ClassroomStatus;

import java.util.List;

public interface IClassroomService extends IAbstractService<String, ClassroomDTO> {
    public List<ClassroomDTO> findClassroomByCapacityGreaterThan(int capcity) throws NoDataFoundException;
    public List<ClassroomDTO> findClassroomByStatus(ClassroomStatus status) throws NoDataFoundException;
}
