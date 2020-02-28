package com.isma.school_ms_schools.web.controllers;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.BranchDTO;
import com.isma.school_ms_schools.service.iservices.IBranchService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping(path ="/branches")
@Api(value="Branch API", description="Operations for branches")
public class BranchController {
    private final IBranchService branchService;

    public BranchController(IBranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping(path = "",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<BranchDTO> getAll() throws NoDataFoundException {
        return branchService.getAll();
    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public BranchDTO getById(@PathVariable Long id) throws NoDataFoundException {
        return branchService.getById(id);
    }

    @PostMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE},
                             consumes ={MediaType.APPLICATION_JSON_VALUE})
    public BranchDTO create(@RequestBody BranchDTO branchDTO) throws DataAlreadyUsed, NoDataFoundException, DateFormatException, ParseException {
        return branchService.create(branchDTO);
    }

    @PatchMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public boolean update(@PathVariable Long id,@RequestBody BranchDTO branchDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        return branchService.update(id,branchDTO);
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean delete(@PathVariable Long id) throws NoDataFoundException {
        return branchService.delete(id);
    }

    @GetMapping(path = "/branch-name", produces = {MediaType.APPLICATION_JSON_VALUE})
    public BranchDTO getBranchByName(@RequestParam(name = "name") String branchName) throws NoDataFoundException{
        return branchService.getBranchByName(branchName);
    }

    @GetMapping(path = "/school-branches", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<BranchDTO> getBranchesBySchoolName(@RequestParam(name = "schoolName")String schoolName) throws NoDataFoundException{
        return branchService.getBranchesBySchoolName(schoolName);
    }

}
