package fr.cfdt.gasel.groupeldap.connector;

import fr.cfdt.gasel.groupeldap.LdapGroupApplicationTests;
import fr.cfdt.gasel.groupeldap.util.TestUtils;
import fr.cfdt.gasel.ldap.GaselLDAPService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
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
public class LdapClientTest {

    @Mock
    private GaselLDAPService gaselLDAPService;

    @InjectMocks
    private LdapClient ldapClient;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUsersByUids(){
        // When
        when(gaselLDAPService.getLDAPUsersByUids(any())).thenReturn(TestUtils.createListLdapUser());

        //then
        List<String> npas = new ArrayList<>();
        npas.add("001");
        npas.add("002");
        List<String> usersUidList = ldapClient.getLdapUsersByNpa(npas);

        assertThat(usersUidList, is(notNullValue()));
        assertEquals(2, usersUidList.size());
    }

}
