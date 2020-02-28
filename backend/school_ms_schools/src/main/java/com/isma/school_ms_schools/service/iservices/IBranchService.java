package com.isma.school_ms_schools.service.iservices;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.BranchDTO;

import java.util.List;

public interface IBranchService extends IAbstractService<Long, BranchDTO> {
    public BranchDTO getBranchByName(String branchName) throws NoDataFoundException;
    public List<BranchDTO> getBranchesBySchoolName(String schoolName) throws NoDataFoundException;
}
