package fr.cfdt.gasel.groupeldap.service;

import fr.cfdt.gasel.groupeldap.LdapGroupApplicationTests;
import fr.cfdt.gasel.groupeldap.connector.ebxdb.RoleRepository;
import fr.cfdt.gasel.groupeldap.connector.ebxdb.TypeMandatRepository;
import fr.cfdt.gasel.groupeldap.connector.ebxdb.TypeResponsabiliteRepository;
import fr.cfdt.gasel.groupeldap.connector.ebxdb.TypeStructureRepository;
import fr.cfdt.gasel.groupeldap.dto.OrganismeInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.ResponsabiliteInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.RoleDto;
import fr.cfdt.gasel.groupeldap.dto.TypeStructureDto;
import fr.cfdt.gasel.groupeldap.mapper.ParametreMapper;
import fr.cfdt.gasel.groupeldap.util.TestUtils;
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
    TypeStructureRepository typeStructureRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    TypeResponsabiliteRepository typeResponsabiliteRepository;

    @Mock
    TypeMandatRepository typeMandatRepository;

    @Mock
    ParametreMapper parametreMapper;

    @InjectMocks
    ParameterService parameterService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllStructureTypes() {
        //when
        when(typeStructureRepository.findAll()).thenReturn(TestUtils.createTypeStructureList());
        when(parametreMapper.listTypeStructureModelToDto(any())).thenReturn(TestUtils.createListTypeStructureDto());

        //then
        List<TypeStructureDto> result = parameterService.getAllStructureTypes();

        assertThat(result, is(notNullValue()));
        assertEquals(3, result.size());
    }

    @Test
    public void testGetAllRoles() {
        //when
        when(roleRepository.findAll()).thenReturn(TestUtils.createListRole());
        when(parametreMapper.listRoleModelToDto(any())).thenReturn(TestUtils.createListRoleDto());

        //then
        List<RoleDto> result = parameterService.getAllRoles();

        assertThat(result, is(notNullValue()));
        assertEquals(3, result.size());
    }

    @Test
    public void testGetAllTypeResponsabilite() {
        //when
        when(typeResponsabiliteRepository.findAll()).thenReturn(TestUtils.createListTypeResponsabilte());
        when(parametreMapper.listTypeResponsabiliteModelToDto(any())).thenReturn(TestUtils.createListTypeResponsabilteDto());

        //then
        List<ResponsabiliteInstanceDto> result = parameterService.getAllResponsabiliteInstance();

        assertThat(result, is(notNullValue()));
        assertEquals(3, result.size());
    }

    @Test
    public void testGetAllTypeMandat() {
        //when
        when(typeMandatRepository.findAll()).thenReturn(TestUtils.createListTypeMandat());
        when(parametreMapper.listTypeMandatModelToDto(any())).thenReturn(TestUtils.createListTypeMandatDto());

        //then
        List<OrganismeInstanceDto> result = parameterService.getAllOrganismeInstance();

        assertThat(result, is(notNullValue()));
        assertEquals(3, result.size());
    }

}
