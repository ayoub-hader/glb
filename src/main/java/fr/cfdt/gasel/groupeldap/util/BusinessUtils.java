package fr.cfdt.gasel.groupeldap.util;

/**
 * The type Business utils.
 *
 */
public class BusinessUtils {

    private BusinessUtils(){}

    public static org.xmlsoap.schemas.ws._2002._04.secext.Security securityTokenMapper() {
        org.xmlsoap.schemas.ws._2002._04.secext.Security securityEbx = new org.xmlsoap.schemas.ws._2002._04.secext.Security();
        org.xmlsoap.schemas.ws._2002._04.secext.UsernameToken token = new org.xmlsoap.schemas.ws._2002._04.secext.UsernameToken();
        token.setPassword("gasel");
        token.setUsername("gasel");
        securityEbx.setUsernameToken(token);
        return securityEbx;
    }



}
