package fr.cfdt.gasel.groupeldap.connector;

import fr.cfdt.gasel.groupeldap.LdapGroupApplicationTests;
import fr.cfdt.gasel.groupeldap.enumeration.BranchEnum;
import fr.cfdt.gasel.groupeldap.util.TestUtils;
import fr.cfdt.gasel.schema.v0.ebx.structure.SelectStructureRequestType;
import fr.cfdt.gasel.schema.v0.ebx.structure.StructureType;
import fr.cfdt.gasel.service.ebx.structure.v0.StandardException;
import fr.cfdt.gasel.service.ebx.structure.v0.StructureEBXInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xmlsoap.schemas.ws._2002._04.secext.Security;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {LdapGroupApplicationTests.class})
public class StructureClientTest {
    @Mock
    StructureEBXInterface structureEBXInterface;

    @InjectMocks
    StructureClient structureClient;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetStructureByPredicate() throws StandardException {
        // When
        when(structureEBXInterface.selectStructureOperation(any() , any())).thenReturn(TestUtils.createSelectStructureResponse("200"));

        //Then
        List<StructureType.Gasel.Structure> structures = structureClient.findStructureByPredicat("", "", new Security(), new SelectStructureRequestType.Pagination(), BranchEnum.GASEL.getCode());

        assertThat(structures, is(notNullValue()));
        assertEquals(3, structures.size());
    }
}
