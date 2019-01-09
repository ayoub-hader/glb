package fr.cfdt.gasel.groupeldap.service;

import fr.cfdt.gasel.groupeldap.connector.LdapClient;
import fr.cfdt.gasel.groupeldap.connector.ebxDb.PersonRepositoryImpl;
import fr.cfdt.gasel.groupeldap.dto.PersonneDto;
import fr.cfdt.gasel.groupeldap.mapper.PersonMapper;
import fr.cfdt.gasel.groupeldap.model.Personne;
import fr.cfdt.gasel.groupeldap.util.PagingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author SZaoui
 */

@Service
public class PersonService {

    @Autowired
    PersonRepositoryImpl personRepositoryImpl;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    LdapClient ldapClient;

    @Autowired
    PersonMapper personMapper;

    @Autowired
    PagingUtil pagingUtil;


    public List<PersonneDto> getMembers(String query , Integer page , Integer size){
        List<PersonneDto> result = null;
        LOGGER.info("Start service getMembers ");
        List<Personne> personEbx = personRepositoryImpl.PersonsByQuery(query);
        //recuperer la liste des personnes from LDAP pour faire l'intersection
        List<String> npas = personEbx.stream().map(Personne::getNpa).collect(Collectors.toList());
        List<String> npasLdap = ldapClient.getLdapUsersByNpa(npas);
        //faire l'intersection
        if(npasLdap != null && !npasLdap.isEmpty()){
            result = personMapper.listPersonneModelToDto(personEbx.stream().filter(p -> npasLdap.contains(p.getNpa())).collect(Collectors.toList()));
        }
        LOGGER.info("End service getMembers ");
        if(page != null && size != null){
            result = pagingUtil.getPage(result , page , size);
        }
        return result;
    }
}
