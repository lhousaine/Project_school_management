package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.EducationLevelDTO;
import com.isma.school_ms_schools.data.Entities.EducationLevel;
import com.isma.school_ms_schools.data.Entities.Training;
import com.isma.school_ms_schools.data.Repositories.EducationLevelRepository;
import com.isma.school_ms_schools.service.iservices.IEducationLevelService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class ImplEducationLevelService implements IEducationLevelService {
    private final EducationLevelRepository educationLevelRepository;
    private final IConverter<EducationLevel, EducationLevelDTO> educationLevelConverter;

    public ImplEducationLevelService(EducationLevelRepository educationLevelRepository, @Qualifier("ImplEducationLevelConverter") IConverter<EducationLevel, EducationLevelDTO> educationLevelConverter) {
        this.educationLevelRepository = educationLevelRepository;
        this.educationLevelConverter = educationLevelConverter;
    }

    @Override
    public List<EducationLevelDTO> getAll() throws NoDataFoundException {
        List<EducationLevel> educationLevels=educationLevelRepository.findAll();
        if (educationLevels==null)
            throw new NoDataFoundException("There is no EducationLevel");
        return educationLevelConverter.convertListToListDto(educationLevels);
    }

    @Override
    public EducationLevelDTO getById(Long idEduLevel) throws NoDataFoundException {
        EducationLevel educationLevel=educationLevelRepository.getOne(idEduLevel);
        if (educationLevel==null)
            throw new NoDataFoundException("There is no EducationLevel identified by"+idEduLevel);
        return educationLevelConverter.convertToDto(educationLevel);
    }

    @Override
    public EducationLevelDTO create(EducationLevelDTO educationLevelDTO) throws DataAlreadyUsed, NoDataFoundException, ParseException {
        EducationLevel educationLevel=educationLevelConverter.convertToEntity(educationLevelDTO);
        EducationLevel educationLevel1=educationLevelRepository.findEducationLevelByName(educationLevel.getName());
        if(educationLevel1!=null)
            throw new DataAlreadyUsed("EducationLevel name already in use");
        educationLevel1=educationLevelRepository.save(educationLevel);
        return educationLevelConverter.convertToDto(educationLevel1);
    }

    @Override
    public boolean update(Long idEduLevel, EducationLevelDTO educationLevelDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        EducationLevel educationLevel=educationLevelConverter.convertToEntity(educationLevelDTO);
        EducationLevel educationLevel1=educationLevelRepository.findEducationLevelByName(educationLevel.getName());
        if(educationLevel1!=null)
            throw new DataAlreadyUsed("EducationLevel name already in use");
        educationLevel1=educationLevelRepository.getOne(idEduLevel);
        if (educationLevel1==null)
            throw new NoDataFoundException("there is no educationLevel identified by "+idEduLevel);
        if (educationLevel.getDescription()!=null)
            educationLevel.setDescription(educationLevel.getDescription());
        try {
            educationLevelRepository.save(educationLevel1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long idEduLevel) throws NoDataFoundException {
        EducationLevel educationLevel=educationLevelRepository.getOne(idEduLevel);
        if (educationLevel==null) {
            throw new NoDataFoundException("No EducationLevel identified by " + idEduLevel);
        }
        try{
            educationLevelRepository.deleteById(idEduLevel);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public EducationLevelDTO findEducationLevelByName(String name) throws NoDataFoundException {
        EducationLevel educationLevel=educationLevelRepository.findEducationLevelByName(name);
        if (educationLevel==null)
            throw new NoDataFoundException("There is No educationLevel named by " +name);
        return educationLevelConverter.convertToDto(educationLevel);
    }
}
