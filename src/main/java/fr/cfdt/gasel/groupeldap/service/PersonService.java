package fr.cfdt.gasel.groupeldap.service;

import fr.cfdt.gasel.groupeldap.connector.LdapClient;
import fr.cfdt.gasel.groupeldap.connector.ebxdb.PersonRepositoryImpl;
import fr.cfdt.gasel.groupeldap.dto.PersonneDto;
import fr.cfdt.gasel.groupeldap.exception.TechnicalException;
import fr.cfdt.gasel.groupeldap.mapper.PersonMapper;
import fr.cfdt.gasel.groupeldap.model.Personne;
import fr.cfdt.gasel.groupeldap.properties.MessagesProperties;
import fr.cfdt.gasel.groupeldap.util.PagingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author SZaoui
 */

@Service
@EnableConfigurationProperties(MessagesProperties.class)
@PropertySource(ResourceUtils.CLASSPATH_URL_PREFIX + "messages.properties")
public class PersonService {

    @Autowired
    PersonRepositoryImpl personRepositoryImpl;

    @Autowired
    private MessagesProperties messagesProperties;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    LdapClient ldapClient;

    @Autowired
    PersonMapper personMapper;

    @Autowired
    PagingUtil pagingUtil;


    public Page<PersonneDto> getMembers(String query , Integer page , Integer size) throws TechnicalException{
        Page<PersonneDto> result;
        List<Personne> tmp = null;
        LOGGER.info("Start service getMembers ");
        try{
            List<Personne> personEbx = personRepositoryImpl.personsByQuery(query);
            //recuperer la liste des personnes from LDAP pour faire l'intersection
            List<String> npas = personEbx.stream().map(Personne::getNpa).collect(Collectors.toList());
            List<String> npasLdap = ldapClient.getLdapUsersByNpa(npas);
            //faire l'intersection
            if(npasLdap != null && !npasLdap.isEmpty()){
                tmp = personEbx.stream().filter(p -> npasLdap.contains(p.getNpa())).collect(Collectors.toList());
            }
        } catch (Exception e){
            LOGGER.error("Call to WS Get getLdapUsersByNpa failed, reason : {}", e.getMessage());
            throw new TechnicalException(messagesProperties.getTechnicalExceptionInGetLdapUsersByNpas());
        }
        List<Personne> pageCont;
        if(page != null && size != null && tmp != null){
            pageCont = pagingUtil.getPage(tmp , page , size);
            result = new PageImpl<>(personMapper.listPersonneModelToDto(pageCont), PageRequest.of(page - 1, size), tmp.size());
        } else if(tmp != null){
            result = new PageImpl<>(personMapper.listPersonneModelToDto(tmp));
        } else {
            result = new PageImpl<>(personMapper.listPersonneModelToDto(new ArrayList<>()));
        }
        LOGGER.info("End service getMembers ");
        return result;
    }
}
