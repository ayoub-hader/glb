//package fr.cfdt.gasel.groupeldap.connector;
//
//import fr.cfdt.gasel.groupeldap.LdapGroupApplicationTests;
//import fr.cfdt.gasel.groupeldap.util.TestUtils;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;
///**
// * Author Zaoui Soukaina
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = {LdapGroupApplicationTests.class})
//public class ParameterClientTest {
//
//    @Mock
//     parametresEBXInterface;
//
//    @InjectMocks
//    ParameterClient parameterClient;
//
//    @Before
//    public void before() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testGetParameterByPredicat() throws StandardException {
//        // When
//        when(parametresEBXInterface.selectTypeStructureOperation(any() , any())).thenReturn(TestUtils.createSelectParameterResponse("200"));
//
//        //Then
//        List<TypeStructureType.Parametres.TypeStructure> structureType = parameterClient.getAllTypeStructure();
//
//        assertThat(structureType, is(notNullValue()));
//        assertEquals(3, structureType.size());
//    }
//
//    @Test
//    public void testGetAllRoles() throws StandardException {
//        //when
//        SelectRoleResponseType tmp = new SelectRoleResponseType();
//        RoleType.Parametres param = new RoleType.Parametres();
//        param.getRole().addAll(TestUtils.createListRole());
//        RoleType data = new RoleType();
//        data.setParametres(param);
//        tmp.setData(data);
//        when(parametresEBXInterface.selectRoleOperation(any() , any())).thenReturn(tmp);
//
//        //then
//        List<RoleType.Parametres.Role> roles = parameterClient.getListRoles();
//
//        assertThat(roles, is(notNullValue()));
//        assertEquals(3, roles.size());
//    }
//
//    @Test
//    public void testGetAllTypeMnadat() throws StandardException {
//        //when
//        SelectTypeMandatResponseType tmp = new SelectTypeMandatResponseType();
//        TypeMandatType.Parametres param = new TypeMandatType.Parametres();
//        param.getTypeMandat().addAll(TestUtils.createListTypeMandat());
//        TypeMandatType data = new TypeMandatType();
//        data.setParametres(param);
//        tmp.setData(data);
//        when(parametresEBXInterface.selectTypeMandatOperation(any() , any())).thenReturn(tmp);
//
//        //then
//        List<TypeMandatType.Parametres.TypeMandat> typeMnadats = parameterClient.getAllTypeMandat();
//
//        assertThat(typeMnadats, is(notNullValue()));
//        assertEquals(3, typeMnadats.size());
//    }
//
//    @Test
//    public void testGetAllTypeResponsabilite() throws StandardException {
//        //when
//        SelectTypeResponsabiliteResponseType tmp = new SelectTypeResponsabiliteResponseType();
//        TypeResponsabiliteType.Parametres param = new TypeResponsabiliteType.Parametres();
//        param.getTypeResponsabilite().addAll(TestUtils.createListTypeResponsabilte());
//        TypeResponsabiliteType data = new TypeResponsabiliteType();
//        data.setParametres(param);
//        tmp.setData(data);
//        when(parametresEBXInterface.selectTypeResponsabiliteOperation(any() , any())).thenReturn(tmp);
//
//        //then
//        List<TypeResponsabiliteType.Parametres.TypeResponsabilite> typeResponsabilites = parameterClient.getAllTypeResponsabilite();
//
//        assertThat(typeResponsabilites, is(notNullValue()));
//        assertEquals(3, typeResponsabilites.size());
//    }
//
//}
