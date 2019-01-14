package fr.cfdt.gasel.groupeldap.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Ldap Group properties.
 */
@ConfigurationProperties(prefix = "group-ldap.client")
@Getter
@Setter
public class LdapGroupProperties {

    private String listUrl;
    private String personEbxProxy;
    private String structureEbxProxy;
    private String parameterEbxProxy;
    private String ldapBaseDn;
    private int ldapPort;
    private String ldapHostName;
    private String ldapBindPassword;
    private String ldapBindDN;
}
