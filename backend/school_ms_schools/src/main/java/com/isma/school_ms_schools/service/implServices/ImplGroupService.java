package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.GroupDTO;
import com.isma.school_ms_schools.data.Entities.Group;
import com.isma.school_ms_schools.data.Repositories.GroupRepository;
import com.isma.school_ms_schools.service.iservices.IGroupService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class ImplGroupService implements IGroupService {
    private final GroupRepository groupRepository;
    private final IConverter<Group, GroupDTO> groupConverter;

    public ImplGroupService(GroupRepository groupRepository, @Qualifier("ImplGroupConverter") IConverter<Group, GroupDTO> groupConverter) {
        this.groupRepository = groupRepository;
        this.groupConverter = groupConverter;
    }

    @Override
    public List<GroupDTO> getAll() throws NoDataFoundException {
        List<Group> groups = groupRepository.findAll();
        if (groups == null)
            throw new NoDataFoundException("There is no groups");
        return groupConverter.convertListToListDto(groups);
    }

    @Override
    public GroupDTO getById(Long idGroup) throws NoDataFoundException {
        Group group = groupRepository.getOne(idGroup);
        if (group == null)
            throw new NoDataFoundException("there is no group identified by " + idGroup);
        return groupConverter.convertToDto(group);
    }

    @Override
    public GroupDTO create(GroupDTO groupDTO) throws DataAlreadyUsed, NoDataFoundException, ParseException {
        Group group = groupConverter.convertToEntity(groupDTO);
        group = groupRepository.save(group);
        return groupConverter.convertToDto(group);
    }

    @Override
    public boolean update(Long idGroup, GroupDTO groupDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        Group group = groupConverter.convertToEntity(groupDTO);
        Group g1 = groupRepository.getOne(idGroup);
        if (g1 == null) {
            throw new NoDataFoundException("No group identified by " + idGroup);
        }
        g1.setName(group.getName());
        g1.setTimeTable(group.getTimeTable());
        try {
            groupRepository.save(g1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long idGroup) throws NoDataFoundException {
        Group group = groupRepository.findById(idGroup).get();
        if (group == null) {
            throw new NoDataFoundException("No Group identified by " + idGroup);
        }
        try {
            groupRepository.deleteById(idGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public GroupDTO findGroupByName(String groupName) throws NoDataFoundException {
        Group group = groupRepository.findGroupByName(groupName);
        if (group == null)
            throw new NoDataFoundException("There is No group named by " + groupName);
        return groupConverter.convertToDto(group);
    }

    @Override
    public List<GroupDTO> findGroupsByTrainingName(String trainingName) throws NoDataFoundException {
        List<Group> groups = groupRepository.findGroupsByTrainingName(trainingName);
        if (groups == null)
            throw new NoDataFoundException("There is No group in the Training named " + trainingName);
        return groupConverter.convertListToListDto(groups);
    }

    @Override
    public List<GroupDTO> findGroupsByTrainingId(Long id) throws NoDataFoundException {
        List<Group> groups = groupRepository.findGroupsByTrainingId(id);
        if (groups == null)
            throw new NoDataFoundException("There is No group in the Training with id : " + id);
        return groupConverter.convertListToListDto(groups);
    }

    @Override
    public GroupDTO findGroupByTimeTableCode(String code) throws NoDataFoundException {
        Group group = groupRepository.findGroupByTimeTableCode(code);
        if (group == null)
            throw new NoDataFoundException("There is No group associated to the timeTable identified by code " + code);
        return groupConverter.convertToDto(group);
    }
}
