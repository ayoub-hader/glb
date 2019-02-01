package fr.cfdt.gasel.groupeldap.config;

import fr.cfdt.gasel.groupeldap.properties.LdapGroupProperties;
import fr.cfdt.gasel.ldap.GaselLDAPService;
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
