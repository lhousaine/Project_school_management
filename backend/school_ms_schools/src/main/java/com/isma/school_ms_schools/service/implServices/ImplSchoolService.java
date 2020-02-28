package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.SchoolDTO;
import com.isma.school_ms_schools.data.Entities.Group;
import com.isma.school_ms_schools.data.Entities.School;
import com.isma.school_ms_schools.data.Repositories.SchoolRepository;
import com.isma.school_ms_schools.service.iservices.ISchoolService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class ImplSchoolService implements ISchoolService {
    private final SchoolRepository schoolRepository;
    private final IConverter<School, SchoolDTO> schoolConverter;

    public ImplSchoolService(SchoolRepository schoolRepository, @Qualifier("ImplSchoolConverter") IConverter<School, SchoolDTO> schoolConverter) {
        this.schoolRepository = schoolRepository;
        this.schoolConverter = schoolConverter;
    }

    @Override
    public List<SchoolDTO> getAll() throws NoDataFoundException {
        return null;
    }

    @Override
    public SchoolDTO getById(Long idSchool) throws NoDataFoundException {
        School school=schoolRepository.getOne(idSchool);
        if (school==null)
            throw new NoDataFoundException("There is No School identified by "+idSchool);
        return schoolConverter.convertToDto(school);
    }

    @Override
    public SchoolDTO create(SchoolDTO schoolDTO) throws DataAlreadyUsed, NoDataFoundException, ParseException {
        School school=schoolConverter.convertToEntity(schoolDTO);
        return schoolConverter.convertToDto(schoolRepository.save(school));
    }

    @Override
    public boolean update(Long idSchool, SchoolDTO schoolDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        School school=schoolConverter.convertToEntity(schoolDTO);
        School school1=schoolRepository.getOne(idSchool);
        if (school1==null) {
            throw new NoDataFoundException("No school identified by " +idSchool);
        }
        school1.setName(school.getName());
        school1.setDescription(school.getDescription());
        try {
            schoolRepository.save(school1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long aLong) throws NoDataFoundException {
        return false;
    }

    @Override
    public SchoolDTO findSchoolByName(String name) throws NoDataFoundException {
        School school=schoolRepository.findSchoolByName(name);
        if (school==null)
            throw new NoDataFoundException("There is No School named " +name);
        return schoolConverter.convertToDto(school);
    }
}
