package fr.cfdt.gasel.groupeldap.connector;


import fr.cfdt.gasel.groupeldap.LdapGroupApplicationTests;
import fr.cfdt.gasel.groupeldap.mapper.PersonMapper;
import fr.cfdt.gasel.service.ebx.personne.v0.PersonneEBXInterface;
import fr.cfdt.gasel.service.ebx.personne.v0.StandardException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.any;

/**
 * Author SZaoui
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {LdapGroupApplicationTests.class})
public class PersonClientTest {

    @Mock
    PersonneEBXInterface personneEBXInterface;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    PersonClient personClient;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testgetPersonByPredicat() throws StandardException {
        // When
//        when(personneEBXInterface.selectPersonneOperation(any() , any())).thenReturn(TestUtils.createSelectPersonResponse("200"));
//        when(personMapper.mapListEmployeeToPerson(any())).thenReturn(TestUtils.createPersonListDto());
//
//        //Then
//        List<PersonneType.Gasel.Personne> persons = personClient.findPersonneByPredicat("", BranchEnum.GASEL.getCode(), "", new Security(), new Pagination());
//
//        assertThat(persons, is(notNullValue()));
//        assertEquals(3, persons.size());
    }
}
