package com.isma.school_ms_schools.data.Converters.ImplConverters;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.BranchDTO;
import com.isma.school_ms_schools.data.Entities.Branch;
import com.isma.school_ms_schools.data.Entities.School;
import com.isma.school_ms_schools.data.Repositories.SchoolRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Qualifier("ImplBranchConverter")
public class ImplBranchConverter implements IConverter<Branch, BranchDTO> {
    private final ModelMapper modelMapper;
    private final SchoolRepository schoolRepository;

    public ImplBranchConverter(ModelMapper modelMapper, SchoolRepository schoolRepository) {
        this.modelMapper = modelMapper;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public BranchDTO convertToDto(Branch branch) {
        BranchDTO branchDTO=modelMapper.map(branch, BranchDTO.class);
        branchDTO.setSchoolName(branch.getSchool().getName());
        return branchDTO;
    }

    @Override
    public List<BranchDTO> convertListToListDto(List<Branch> branches) {
        List<BranchDTO> branchDTOS=new ArrayList<>();
        BranchDTO branchDTO;
        for (Branch branch:branches){
            branchDTO=modelMapper.map(branch,BranchDTO.class);
            branchDTO.setSchoolName(branch.getSchool().getName());
            branchDTOS.add(branchDTO);
        }
        return branchDTOS;
    }

    @Override
    public Branch convertToEntity(BranchDTO branchDTO) throws NoDataFoundException {
        Branch branch=modelMapper.map(branchDTO, Branch.class);
        if(branchDTO.getSchoolName()!=null)
              branch.setSchool(this.getSchoolByName(branchDTO.getSchoolName()));
        return branch;
    }

    @Override
    public List<Branch> convertListDtoToListEntity(List<BranchDTO> branchDTOS) throws NoDataFoundException {
        List<Branch> branches=new ArrayList<>();
        Branch branch;
        for (BranchDTO branchDTO:branchDTOS){
            branch=modelMapper.map(branchDTO,Branch.class);
            branch.setSchool(this.getSchoolByName(branchDTO.getSchoolName()));
            branches.add(branch);
        }
        return branches;
    }

    private School getSchoolByName(String schoolName) throws NoDataFoundException {
        School school=schoolRepository.findSchoolByName(schoolName);
        if(school==null)
            throw new NoDataFoundException("No School named with "+schoolName);
        return school;
    }
}
