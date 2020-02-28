package com.isma.school_ms_schools.service.iservices;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.GroupDTO;

import java.util.List;

public interface IGroupService extends IAbstractService<Long, GroupDTO> {
    public GroupDTO findGroupByName(String groupName) throws NoDataFoundException;
    public List<GroupDTO> findGroupsByTrainingName(String name) throws NoDataFoundException;
    public List<GroupDTO> findGroupsByTrainingId(Long name) throws NoDataFoundException;
    public GroupDTO findGroupByTimeTableCode(String code) throws NoDataFoundException;
}
