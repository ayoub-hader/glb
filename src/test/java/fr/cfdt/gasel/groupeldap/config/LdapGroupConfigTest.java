package fr.cfdt.gasel.groupeldap.config;

import fr.cfdt.gasel.groupeldap.LdapGroupApplicationTests;
import fr.cfdt.gasel.groupeldap.exception.TechnicalException;
import fr.cfdt.gasel.ldap.GaselLDAPService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.KeyStoreException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {LdapGroupApplicationTests.class})
public class LdapGroupConfigTest {
    @Autowired
    private LdapGroupConfig ldapGroupConfig;

    @Autowired
    private GroupDatasource groupdatasource;

    /**
     *
     * @throws TechnicalException
     */

    @Test
    public void testLdapBean() throws KeyStoreException {
        GaselLDAPService gaselLDAPService = ldapGroupConfig.ldapBean();
        assertThat(gaselLDAPService, is(notNullValue()));
    }
    
}
