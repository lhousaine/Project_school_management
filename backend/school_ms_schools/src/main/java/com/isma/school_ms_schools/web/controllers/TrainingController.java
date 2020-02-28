package com.isma.school_ms_schools.web.controllers;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.TrainingDTO;
import com.isma.school_ms_schools.service.iservices.ITrainingService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping(path ="trainings")
@Api(value="Training EndPoint", description="Operations for Trainings")
public class TrainingController{
    private final ITrainingService trainingService;

    public TrainingController(ITrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping(path = "",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TrainingDTO> getAll() throws NoDataFoundException {
        return trainingService.getAll();
    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public TrainingDTO getById(@PathVariable Long id) throws NoDataFoundException {
        return trainingService.getById(id);
    }

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public TrainingDTO create(@RequestBody TrainingDTO trainingDTO) throws DataAlreadyUsed, NoDataFoundException, DateFormatException, ParseException {
        return trainingService.create(trainingDTO);
    }

    @PatchMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public boolean update(@PathVariable Long id,@RequestBody TrainingDTO trainingDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        return trainingService.update(id,trainingDTO);
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean delete(@PathVariable Long id) throws NoDataFoundException {
        return trainingService.delete(id);
    }

    @GetMapping(path = "/training-by-name",produces = {MediaType.APPLICATION_JSON_VALUE})
    public TrainingDTO findTrainingByName(@RequestParam("trainingName") String trainingName) throws NoDataFoundException{
        return trainingService.findTrainingByName(trainingName);
    }

    @GetMapping(path = "/trainings-by-level-name",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TrainingDTO> findTrainingsByLevelName(@RequestParam("levelName") String levelName) throws NoDataFoundException{
        return trainingService.findTrainingsByEducationLevelName(levelName);
    }

    @GetMapping(path = "/trainings-by-level-id",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TrainingDTO> findTrainingsByLevelId(@RequestParam("levelId") Long levelId) throws NoDataFoundException{
        return trainingService.findTrainingsByEducationLevelId(levelId);
    }
}
