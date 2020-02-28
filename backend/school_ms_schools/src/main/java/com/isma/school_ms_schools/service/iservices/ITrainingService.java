package com.isma.school_ms_schools.service.iservices;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.TrainingDTO;

import java.util.List;

public interface ITrainingService extends IAbstractService<Long, TrainingDTO> {
    public TrainingDTO findTrainingByName(String trainingName) throws NoDataFoundException;
    public List<TrainingDTO> findTrainingsByEducationLevelName(String eduLevelName) throws NoDataFoundException;
    public List<TrainingDTO> findTrainingsByEducationLevelId(Long id) throws NoDataFoundException;
}
