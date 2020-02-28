package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.BranchDTO;
import com.isma.school_ms_schools.data.Entities.Branch;
import com.isma.school_ms_schools.data.Repositories.BranchRepository;
import com.isma.school_ms_schools.service.iservices.IBranchService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class ImplBranchService implements IBranchService {
    private final BranchRepository branchRepository;
    private final IConverter<Branch, BranchDTO> branchConverter;

    public ImplBranchService(BranchRepository branchRepository, @Qualifier("ImplBranchConverter") IConverter<Branch, BranchDTO> branchConverter) {
        this.branchRepository = branchRepository;
        this.branchConverter = branchConverter;
    }

    @Override
    public List<BranchDTO> getAll() throws NoDataFoundException {
        List<Branch> branches=branchRepository.findAll();
        return branchConverter.convertListToListDto(branches);
    }

    @Override
    public BranchDTO getById(Long id) throws NoDataFoundException {
        Branch branch=branchRepository.getOne(id);
        if (branch==null)
            throw new NoDataFoundException("No branch identified by "+id);
        return branchConverter.convertToDto(branch);
    }

    @Override
    public BranchDTO create(BranchDTO branchDTO) throws DataAlreadyUsed, NoDataFoundException, ParseException {
        Branch branch=branchConverter.convertToEntity(branchDTO);
        Branch branch1=branchRepository.getBranchByName(branch.getName());
        if(branch1!=null)
            throw new DataAlreadyUsed("Branch "+branch.getName()+" Name already in use");
        branch1=branchRepository.save(branch);
        return branchConverter.convertToDto(branch1);
    }

    @Override
    public boolean update(Long id, BranchDTO branchDTO) throws NoDataFoundException, DataAlreadyUsed {
        Branch br=branchRepository.getOne(id);
        if (br==null) {
            throw new NoDataFoundException("No branch identified by " + id);
        }
        if (branchRepository.getBranchByName(branchDTO.getName())!=null)
            throw new DataAlreadyUsed("Branch "+branchDTO.getName()+" Name already in use");
        br.setName(branchDTO.getName());
        br.setDescription(branchDTO.getDescription());
        try {
        branchRepository.save(br);
        }catch (Exception e){
             e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) throws NoDataFoundException {
        Branch branch=branchRepository.getOne(id);
        if (branch==null) {
            throw new NoDataFoundException("No branch identified by " + id);
        }
        try{
            branchRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public BranchDTO getBranchByName(String branchName) throws NoDataFoundException {
        Branch branch=branchRepository.getBranchByName(branchName);
        if(branch==null)
            throw new NoDataFoundException("No Branch identified By Name "+branchName);
        return branchConverter.convertToDto(branch);
    }

    @Override
    public List<BranchDTO> getBranchesBySchoolName(String schoolName) throws NoDataFoundException {
        List<Branch> branches=branchRepository.findAllBySchoolName(schoolName);
        if (branches==null)
            throw new NoDataFoundException("There is no branch for school named "+schoolName);
        return branchConverter.convertListToListDto(branches);
    }

}
