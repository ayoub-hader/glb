package fr.cfdt.gasel.groupeldap.config;

import fr.cfdt.gasel.groupeldap.exception.TechnicalException;
import fr.cfdt.gasel.groupeldap.properties.LdapGroupProperties;
import fr.cfdt.gasel.ldap.GaselLDAPService;
import fr.cfdt.gasel.service.ebx.parametres.v0.ParametresEBXInterface;
import fr.cfdt.gasel.service.ebx.personne.v0.PersonneEBXInterface;
import fr.cfdt.gasel.service.ebx.structure.v0.StructureEBXInterface;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.security.KeyStoreException;

/**
 * Author SZaoui
 */
@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
@EnableConfigurationProperties(LdapGroupProperties.class)
public class LdapGroupConfig {

    @Autowired
    private LdapGroupProperties ldapGroupProperties;


    /**
     *
     * @return
     * @throws TechnicalException
     */
    @Bean(name = "personEbxProxy")
    public PersonneEBXInterface personEbxProxy() throws TechnicalException {
        return buildProxy(ldapGroupProperties.getPersonEbxProxy(), PersonneEBXInterface.class);
    }

    @Bean(name = "structureEbxProxy")
    public StructureEBXInterface structureEbxProxy() throws TechnicalException {
        return buildProxy(ldapGroupProperties.getStructureEbxProxy(), StructureEBXInterface.class);
    }

    @Bean(name = "parameterEbxProxy")
    public ParametresEBXInterface parameterEbxProxy() throws TechnicalException {
        return buildProxy(ldapGroupProperties.getParameterEbxProxy(), ParametresEBXInterface.class);
    }

    @Bean(name = "ldapBean")
    public GaselLDAPService ldapBean() throws KeyStoreException {
        GaselLDAPService ldapBean = new GaselLDAPService();
        ldapBean.setBaseDn(ldapGroupProperties.getLdapBaseDn());
        ldapBean.setBindDN(ldapGroupProperties.getLdapBindDN());
        ldapBean.setBindPassword(ldapGroupProperties.getLdapBindPassword());
        ldapBean.setHostName(ldapGroupProperties.getLdapHostName());
        ldapBean.setPort(ldapGroupProperties.getLdapPort());
        return ldapBean;
    }

    private <T> T buildProxy(String url, Class<T> clazz) {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(clazz);
        jaxWsProxyFactoryBean.setAddress(url);
        return (T) jaxWsProxyFactoryBean.create();
    }
}
