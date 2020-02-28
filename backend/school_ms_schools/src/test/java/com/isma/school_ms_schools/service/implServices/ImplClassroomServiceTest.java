package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Repositories.ClassroomRepository;
import com.isma.school_ms_schools.service.iservices.IClassroomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;


class ImplClassroomServiceTest {
    @InjectMocks
    private IClassroomService classroomService;

    @Mock
    @Qualifier("ImplClassroomConverter")
    private IConverter branchConverter;

    @Mock
    private ClassroomRepository classroomRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}