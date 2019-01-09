package fr.cfdt.gasel.groupeldap.service;

import fr.cfdt.gasel.groupeldap.connector.groupDb.RequestRepository;
import fr.cfdt.gasel.groupeldap.mapper.RequestMapper;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RequestServiceTest {

    @Mock
    RequestRepository requestRepository;

    @Mock
    RequestMapper requestMapper;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

}
