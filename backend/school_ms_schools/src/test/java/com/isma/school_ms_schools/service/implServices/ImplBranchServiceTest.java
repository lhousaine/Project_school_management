package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.BranchDTO;
import com.isma.school_ms_schools.core.helpers.Address;
import com.isma.school_ms_schools.data.Entities.Branch;
import com.isma.school_ms_schools.core.helpers.ContactSchool;
import com.isma.school_ms_schools.data.Entities.School;
import com.isma.school_ms_schools.data.Repositories.BranchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.isA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ImplBranchServiceTest {
    @InjectMocks
    private ImplBranchService branchService;

    @Mock
    @Qualifier("ImplBranchConverter")
    private IConverter<Branch,BranchDTO> branchConverter;
    @Mock
    private BranchRepository branchRepository;
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
        school= new School();
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
    void getAll() throws NoDataFoundException {
        when(branchRepository.findAll()).thenReturn(branches);
        when(branchConverter.convertListToListDto(anyObject())).thenReturn(branchDTOs);
        List<BranchDTO> brDTOs=branchService.getAll();
        verify(branchRepository).findAll();
        verify(branchConverter).convertListToListDto(branches);
        assertNotNull(brDTOs);
        assertThat(brDTOs,isA(List.class));
        assertEquals(branches.size(),brDTOs.size());
    }

    @Test
    void getById() throws NoDataFoundException {
        when(branchRepository.getOne(1L)).thenReturn(branch1);
        when(branchConverter.convertToDto(anyObject())).thenReturn(branchDTO1);
        BranchDTO br=branchService.getById(1L);
        verify(branchRepository).getOne(1L);
        verify(branchConverter).convertToDto(branch1);
        assertNotNull(br);
        assertThat(br,isA(BranchDTO.class));
        assertEquals(branch1.getName(),br.getName());
    }

    @Test
    void create() throws DataAlreadyUsed, NoDataFoundException, ParseException {
        when(branchConverter.convertToEntity(anyObject())).thenReturn(branch1);
        when(branchRepository.getBranchByName(anyString())).thenReturn(null);
        when(branchRepository.save(branch1)).thenReturn(branch1);
        when(branchConverter.convertToDto(branch1)).thenReturn(branchDTO1);
        BranchDTO brDTO=branchService.create(branchDTO1);
        verify(branchConverter).convertToEntity(anyObject());
        verify(branchRepository).getBranchByName(anyString());
        verify(branchRepository).save(branch1);
        verify(branchConverter).convertToDto(branch1);
        assertNotNull(brDTO);
        assertThat(brDTO,isA(BranchDTO.class));
        assertEquals(brDTO.getName(),branch1.getName());
    }

    @Test
    void update() throws NoDataFoundException, DataAlreadyUsed {
        when(branchRepository.getOne(anyLong())).thenReturn(branch1);
        when(branchRepository.getBranchByName(anyString())).thenReturn(null);
        branch1.setName("abdlakrim branch");
        boolean done=branchService.update(anyLong(),branchDTO1);
        verify(branchRepository).getOne(anyLong());
        verify(branchRepository).getBranchByName(branch1.getName());
        verify(branchRepository).save(branch1);
        assertTrue(done);
    }

    @Test
    void delete() throws NoDataFoundException {
        when(branchRepository.getOne(1L)).thenReturn(branch1);
        boolean done=branchService.delete(1L);
        verify(branchRepository).getOne(1L);
        verify(branchRepository).deleteById(1L);
        assertTrue(done);
    }

    @Test
    void getBranchByName() throws NoDataFoundException {
        when(branchRepository.getBranchByName(anyString())).thenReturn(branch1);
        when(branchConverter.convertToDto(anyObject())).thenReturn(branchDTO1);
        BranchDTO br=branchService.getBranchByName(branchDTO1.getName());
        verify(branchRepository).getBranchByName(anyString());
        verify(branchConverter).convertToDto(branch1);
        assertNotNull(br);
        assertThat(br,isA(BranchDTO.class));
        assertEquals(branch1.getName(),br.getName());
    }

    @Test
    void getBrnchesBySchoolName() throws NoDataFoundException {
        when(branchRepository.findAllBySchoolName(anyString())).thenReturn(branches);
        when(branchConverter.convertListToListDto(anyObject())).thenReturn(branchDTOs);
        List<BranchDTO> brDTOs=branchService.getBranchesBySchoolName(school.getName());
        verify(branchRepository).findAllBySchoolName(anyString());
        verify(branchConverter).convertListToListDto(anyObject());
        assertNotNull(brDTOs);
        assertThat(brDTOs,isA(List.class));
        assertEquals(branchDTOs.size(),brDTOs.size());
    }
}