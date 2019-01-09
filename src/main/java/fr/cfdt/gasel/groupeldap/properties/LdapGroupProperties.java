package fr.cfdt.gasel.groupeldap.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The type Account preperties.
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
    //datasources
    private String firstDataSourceUrl;
    private String firstDataSourceUserName;
    private String firstDataSourcePassword;
    private String secondDataSourceUrl;
    private String secondDataSourceUserName;
    private String secondDataSourcePassword;
    private String driverName;
    private String dialect;

}
