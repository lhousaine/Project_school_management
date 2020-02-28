package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Repositories.SessionRepository;
import com.isma.school_ms_schools.service.iservices.ISessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;

class ImplSessionServiceTest {
    @InjectMocks
    private ISessionService courseService;

    @Mock
    @Qualifier("ImplSessionConverter")
    private IConverter courseConverter;
    @Mock
    private SessionRepository sessionRepository;
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