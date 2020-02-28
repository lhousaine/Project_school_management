package com.isma.school_ms_schools.data.Converters.ImplConverters;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.BranchDTO;
import com.isma.school_ms_schools.core.helpers.Address;
import com.isma.school_ms_schools.data.Entities.Branch;
import com.isma.school_ms_schools.core.helpers.ContactSchool;
import com.isma.school_ms_schools.data.Entities.School;
import com.isma.school_ms_schools.data.Repositories.SchoolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.isA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ImplBranchConverterTest {

    @InjectMocks
    private ImplBranchConverter branchConverter;
    @Mock
    private SchoolRepository schoolRepository;
    @Mock
    private ModelMapper modelMapper;
    private School school;
    private Branch branch1;
    private Branch branch2;
    private BranchDTO branchDTO1;
    private BranchDTO branchDTO2;
    private List<Branch> branches;
    private List<BranchDTO> branchDTOs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        school=new School();
        school.setName("ecole descart");
        school.setDescription("ecole descart ecole descart");
        ContactSchool cnt=new ContactSchool("+2121234567890","lhou@gmail.com");
        Address adr=new Address("125","rue 125","zagora","maroc");
        branch1=new Branch("branch dawdiyat","branch dawdiyat branch dawdiyat",cnt,adr);
        branch1.setSchool(school);
        branch2=new Branch("branch sidi Abad","branch sidi Abad branch sidi Abad",cnt,adr);
        branch2.setSchool(school);

        branchDTO1=new BranchDTO("branch dawdiyat","branch dawdiyat branch dawdiyat",cnt,adr);
        branchDTO1.setSchoolName(school.getName());

        branchDTO2=new BranchDTO("branch sidi Abad","branch sidi Abad branch sidi Abad",cnt,adr);
        branchDTO2.setSchoolName(school.getName());

        branches=new ArrayList<>();
        branches.add(branch1);
        branches.add(branch2);

        branchDTOs=new ArrayList<>();
        branchDTOs.add(branchDTO1);
        branchDTOs.add(branchDTO2);
    }

    @Test
    void whenConvertBranchEntityToBranchDto_thenCorrect() {
        //arrange
        when(modelMapper.map(branch1,BranchDTO.class)).thenReturn(branchDTO1);
        //act
        BranchDTO bDTO =branchConverter.convertToDto(branch1);
        //verify
        verify(modelMapper).map(branch1,BranchDTO.class);
        assertThat(bDTO,isA(BranchDTO.class));
        assertEquals(branchDTO1.getId(), bDTO.getId());
        assertEquals(branchDTO1.getName(), bDTO.getName());
        assertEquals(branchDTO1.getDescription(), bDTO.getDescription());
        assertEquals(branchDTO1.getSchoolName(),branchDTO1.getSchoolName());
    }

    @Test
    void convertListToListDto() {
        for(int i=0;i<branches.size();i++){
            when(modelMapper.map(branches.get(i),BranchDTO.class)).thenReturn(branchDTOs.get(i));
        }
        List<BranchDTO> branchDtos1=branchConverter.convertListToListDto(branches);
        for(int i=0;i<branches.size();i++){
            verify(modelMapper).map(branches.get(i),BranchDTO.class);
        }
        //assertDoesNotThrow(NoDataFoundException::new);
        assertNotNull(branchDtos1);
        assertEquals(branchDtos1.size(),branchDTOs.size());
        assertEquals(branchDtos1.get(0).getId(), branchDTOs.get(0).getId());
    }

    @Test
    void whenConvertBranchDtoToBranchEntity_thenCorrect() throws NoDataFoundException {
        when(modelMapper.map(branchDTO1,Branch.class)).thenReturn(branch1);
        when(schoolRepository.findSchoolByName(anyString())).thenReturn(school);
        Branch br1= branchConverter.convertToEntity(branchDTO1);
        verify(modelMapper).map(branchDTO1,Branch.class);
        verify(schoolRepository).findSchoolByName(anyString());
        //assertDoesNotThrow(NoDataFoundException::new);
        assertNotNull(br1);
        assertThat(br1,isA(Branch.class));
        assertEquals(br1.getId(), branch1.getId());
        assertEquals(br1.getName(), branch1.getName());
        assertEquals(br1.getDescription(), branch1.getDescription());
    }

    @Test
    void convertListDtoToListEntity() throws NoDataFoundException {
        for(int i=0;i<branches.size();i++){
            when(modelMapper.map(branchDTOs.get(i),Branch.class)).thenReturn(branches.get(i));
        }
        when(schoolRepository.findSchoolByName(anyString())).thenReturn(school);
        List<Branch> branches1=branchConverter.convertListDtoToListEntity(branchDTOs);
        for(int i=0;i<branches.size();i++) {
            verify(modelMapper).map(branchDTOs.get(i), Branch.class);
        }
        assertNotNull(branches1);
        assertEquals(branches1.size(),branches.size());
        assertEquals(branches.get(0).getId(), branches.get(0).getId());
    }

}
