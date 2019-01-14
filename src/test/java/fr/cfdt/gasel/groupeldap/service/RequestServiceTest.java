package fr.cfdt.gasel.groupeldap.service;

import fr.cfdt.gasel.groupeldap.connector.groupdb.RequestRepository;
import fr.cfdt.gasel.groupeldap.mapper.RequestMapper;
import org.junit.Before;
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
