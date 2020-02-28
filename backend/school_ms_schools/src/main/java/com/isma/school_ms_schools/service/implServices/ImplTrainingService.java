package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.TrainingDTO;
import com.isma.school_ms_schools.data.Entities.Training;
import com.isma.school_ms_schools.data.Repositories.TrainingRepository;
import com.isma.school_ms_schools.service.iservices.ITrainingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class ImplTrainingService implements ITrainingService {
    private final TrainingRepository trainingRepository;
    private final IConverter<Training, TrainingDTO> trainingConverter;

    public ImplTrainingService(TrainingRepository trainingRepository, @Qualifier("ImplTrainingConverter") IConverter<Training, TrainingDTO> trainingConverter) {
        this.trainingRepository = trainingRepository;
        this.trainingConverter = trainingConverter;
    }

    @Override
    public List<TrainingDTO> getAll() throws NoDataFoundException {
        List<Training> trainings = trainingRepository.findAll();
        if (trainings == null)
            throw new NoDataFoundException("There is no training");
        return trainingConverter.convertListToListDto(trainings);
    }

    @Override
    public TrainingDTO getById(Long idTraining) throws NoDataFoundException {
        Training training = trainingRepository.getOne(idTraining);
        if (training == null)
            throw new NoDataFoundException("there is no training identified by " + idTraining);
        return trainingConverter.convertToDto(training);
    }

    @Override
    public TrainingDTO create(TrainingDTO trainingDTO) throws DataAlreadyUsed, NoDataFoundException, ParseException {
        Training training = trainingConverter.convertToEntity(trainingDTO);
        Training training1 = trainingRepository.findTrainingByName(training.getName());
        if (training1 != null)
            throw new DataAlreadyUsed("Training " + training.getName() + " already in use");
        training1 = trainingRepository.save(training);
        return trainingConverter.convertToDto(training1);
    }

    @Override
    public boolean update(Long idTraining, TrainingDTO trainingDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        Training training = trainingConverter.convertToEntity(trainingDTO);
        Training training1 = trainingRepository.getOne(idTraining);
        if (training1 == null)
            throw new DataAlreadyUsed("No Training was identified by " + idTraining);
        training1.setCost(training.getCost());
        try {
            trainingRepository.save(training1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long idTraining) throws NoDataFoundException {
        if (!trainingRepository.findById(idTraining).isPresent()) {
            throw new NoDataFoundException("No Training identified by " + idTraining);
        }
        try {
            trainingRepository.deleteById(idTraining);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public TrainingDTO findTrainingByName(String trainingName) throws NoDataFoundException {
        Training training = trainingRepository.findTrainingByName(trainingName);
        if (training == null)
            throw new NoDataFoundException("There is No Training named " + trainingName);
        return trainingConverter.convertToDto(training);
    }

    @Override
    public List<TrainingDTO> findTrainingsByEducationLevelName(String eduLevelName) throws NoDataFoundException {
        List<Training> trainings = trainingRepository.findTrainingsByEducationLevelName(eduLevelName);
        if (trainings == null)
            throw new NoDataFoundException("There is no trainings in the education level : " + eduLevelName);
        return trainingConverter.convertListToListDto(trainings);
    }

    @Override
    public List<TrainingDTO> findTrainingsByEducationLevelId(Long id) throws NoDataFoundException {
        List<Training> trainings = trainingRepository.findTrainingsByEducationLevelId(id);
        if (trainings == null)
            throw new NoDataFoundException("There is no trainings in the education level with id : " + id);
        return trainingConverter.convertListToListDto(trainings);
    }

}
