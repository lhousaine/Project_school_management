package com.isma.school_ms_schools.service.iservices;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.StudentDTO;
import com.isma.school_ms_schools.data.Entities.Student;

import java.util.List;

public interface IStudentService extends IAbstractService<Long,StudentDTO>{
    public StudentDTO createNewStudent(Student student) throws DataAlreadyUsed, NoDataFoundException, DateFormatException;
    public StudentDTO findStudentByStudentCard_Id(Long idStuCard);
    public StudentDTO findStudentByParent_Id(Long idParent) throws NoDataFoundException;
    public List<StudentDTO> findStudentsByGroup_Id(Long idGroup) throws NoDataFoundException;

}
