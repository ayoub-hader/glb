package fr.cfdt.gasel.groupeldap.service;

import fr.cfdt.gasel.groupeldap.LdapGroupApplicationTests;
import fr.cfdt.gasel.groupeldap.connector.ParameterClient;
import fr.cfdt.gasel.groupeldap.dto.OrganismeInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.ResponsabiliteInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.RoleDto;
import fr.cfdt.gasel.groupeldap.dto.TypeStructureDto;
import fr.cfdt.gasel.groupeldap.mapper.ParametreMapper;
import fr.cfdt.gasel.groupeldap.util.TestUtils;
import fr.cfdt.gasel.service.ebx.parametres.v0.StandardException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Author SZaoui
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {LdapGroupApplicationTests.class})
public class ParameterServiceTest {

    @Mock
    ParameterClient parameterClient;

    @Mock
    ParametreMapper parametreMapper;

    @InjectMocks
    ParameterService parameterService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllStructureTypes() throws StandardException {
        //when
        when(parameterClient.getAllTypeStructure()).thenReturn(TestUtils.createTypeStructureList());
        when(parametreMapper.listTypeStructureModelToDto(any())).thenReturn(TestUtils.createListTypeStructureDto());

        //then
        List<TypeStructureDto> result = parameterService.getAllStructureTypes();

        assertThat(result, is(notNullValue()));
        assertEquals(3, result.size());
    }

    @Test
    public void testGetAllRoles() throws StandardException {
        //when
        when(parameterClient.getListRoles()).thenReturn(TestUtils.createListRole());
        when(parametreMapper.listRoleModelToDto(any())).thenReturn(TestUtils.createListRoleDto());

        //then
        List<RoleDto> result = parameterService.getAllRoles();

        assertThat(result, is(notNullValue()));
        assertEquals(3, result.size());
    }

    @Test
    public void testGetAllTypeResponsabilite() throws StandardException {
        //when
        when(parameterClient.getAllTypeResponsabilite()).thenReturn(TestUtils.createListTypeResponsabilte());
        when(parametreMapper.listTypeResponsabiliteModelToDto(any())).thenReturn(TestUtils.createListTypeResponsabilteDto());

        //then
        List<ResponsabiliteInstanceDto> result = parameterService.getAllResponsabiliteInstance();

        assertThat(result, is(notNullValue()));
        assertEquals(3, result.size());
    }

    @Test
    public void testGetAllTypeMandat() throws StandardException {
        //when
        when(parameterClient.getAllTypeMandat()).thenReturn(TestUtils.createListTypeMandat());
        when(parametreMapper.listTypeMandatModelToDto(any())).thenReturn(TestUtils.createListTypeMandatDto());

        //then
        List<OrganismeInstanceDto> result = parameterService.getAllOrganismeInstance();

        assertThat(result, is(notNullValue()));
        assertEquals(3, result.size());
    }

}
