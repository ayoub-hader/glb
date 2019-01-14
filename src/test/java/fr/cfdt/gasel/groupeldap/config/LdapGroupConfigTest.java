package fr.cfdt.gasel.groupeldap.config;

import fr.cfdt.gasel.groupeldap.LdapGroupApplicationTests;
import fr.cfdt.gasel.groupeldap.exception.TechnicalException;
import fr.cfdt.gasel.ldap.GaselLDAPService;
import fr.cfdt.gasel.service.ebx.parametres.v0.ParametresEBXInterface;
import fr.cfdt.gasel.service.ebx.personne.v0.PersonneEBXInterface;
import fr.cfdt.gasel.service.ebx.structure.v0.StructureEBXInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    public void testPersonEbxProxy() throws TechnicalException {
        PersonneEBXInterface personneEBXInterface = ldapGroupConfig.personEbxProxy();
        assertThat(personneEBXInterface, is(notNullValue()));
    }

    @Test
    public void testStructureEbxProxy() throws TechnicalException {
        StructureEBXInterface structureEBXInterface = ldapGroupConfig.structureEbxProxy();
        assertThat(structureEBXInterface, is(notNullValue()));
    }

    @Test
    public void testParameterEbxProxy() throws TechnicalException {
        ParametresEBXInterface parametresEBXInterface = ldapGroupConfig.parameterEbxProxy();
        assertThat(parametresEBXInterface, is(notNullValue()));
    }

    @Test
    public void testLdapBean() throws KeyStoreException {
        GaselLDAPService gaselLDAPService = ldapGroupConfig.ldapBean();
        assertThat(gaselLDAPService, is(notNullValue()));
    }
    
}
