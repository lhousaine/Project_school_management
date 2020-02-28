package com.isma.school_ms_schools.web.controllers;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;

import com.isma.school_ms_schools.data.Dto.GroupDTO;
import com.isma.school_ms_schools.service.iservices.IGroupService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping(path ="groups")
@Api(value="Group EndPoint", description="Operations for Groups")
public class GroupController {
    private final IGroupService groupService;

    public GroupController(IGroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path = "",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<GroupDTO> getAll() throws NoDataFoundException {
        return groupService.getAll();
    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public GroupDTO getById(@PathVariable Long id) throws NoDataFoundException {
        return groupService.getById(id);
    }

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public GroupDTO create(@RequestBody GroupDTO groupDTO) throws DataAlreadyUsed, NoDataFoundException, DateFormatException, ParseException {
        return groupService.create(groupDTO);
    }
    @PatchMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public boolean update(@PathVariable Long id,@RequestBody GroupDTO groupDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        return groupService.update(id,groupDTO);
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean delete(@PathVariable Long id) throws NoDataFoundException {
        return groupService.delete(id);
    }

    @GetMapping(path = "/group-by-name",produces = {MediaType.APPLICATION_JSON_VALUE})
    public GroupDTO findGroupByName(@RequestParam("groupName") String groupName) throws NoDataFoundException{
        return groupService.findGroupByName(groupName);
    }

    @GetMapping(path = "/groups-by-training-name", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<GroupDTO> findGroupsByTrainingName(@RequestParam("trainingName") String trainingName) throws NoDataFoundException {
        return groupService.findGroupsByTrainingName(trainingName);
    }

    @GetMapping(path = "/groups-by-training-id", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<GroupDTO> findGroupsByTrainingId(@RequestParam("trainingId") Long trainingId) throws NoDataFoundException {
        return groupService.findGroupsByTrainingId(trainingId);
    }

    @GetMapping(path = "/timeTable-group",produces = {MediaType.APPLICATION_JSON_VALUE})
    public GroupDTO findGroupByTimeTableCode(@RequestParam("timeTableCode") String timeTableCode) throws NoDataFoundException{
        return groupService.findGroupByTimeTableCode(timeTableCode);
    }
}
