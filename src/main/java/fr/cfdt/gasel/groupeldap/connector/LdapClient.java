package fr.cfdt.gasel.groupeldap.connector;

import fr.cfdt.gasel.ldap.GaselLDAPService;
import fr.cfdt.gasel.ldap.dto.GaselLDAPEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author SZaoui
 */

@Component
public class LdapClient {

    @Autowired
    GaselLDAPService gaselLDAPService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LdapClient.class);

    private static final int MAX_SIZE_PAGE = 1000;

    @Cacheable(value="ldapUsers")
    public List<String> getLdapUsersByNpa(List<String> npas) {
        LOGGER.info("start get users by npas from LDAP npas = {}", npas);
        List<GaselLDAPEntry> ldapUsers = new ArrayList<>();
        //paginer l'appel Ldap
        int i = 0;
        if(npas != null && !npas.isEmpty()){
            while(i < npas.size()){
                List<GaselLDAPEntry> tmp;
                if (i + MAX_SIZE_PAGE >  npas.size()) {
                    tmp = gaselLDAPService.getLDAPUsersByUids(npas.subList(i, npas.size()));
                } else {
                    tmp = gaselLDAPService.getLDAPUsersByUids(npas.subList(i, i + MAX_SIZE_PAGE));
                }
                if(tmp != null){
                    ldapUsers.addAll(tmp);
                }
                i = i + MAX_SIZE_PAGE;
            }
        }
        LOGGER.info("end get users by npas from LDAP");
        return ldapUsers.stream().map(GaselLDAPEntry::getUid).collect(Collectors.toList());
    }
}
