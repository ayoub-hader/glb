package fr.cfdt.gasel.groupeldap.service;

import fr.cfdt.gasel.groupeldap.connector.PersonClient;
import fr.cfdt.gasel.groupeldap.connector.groupdb.GroupRepository;
import fr.cfdt.gasel.groupeldap.connector.groupdb.RequestRepository;
import fr.cfdt.gasel.groupeldap.dto.GroupDto;
import fr.cfdt.gasel.groupeldap.dto.PersonneDto;
import fr.cfdt.gasel.groupeldap.enumeration.BranchEnum;
import fr.cfdt.gasel.groupeldap.exception.TechnicalException;
import fr.cfdt.gasel.groupeldap.mapper.GroupMapper;
import fr.cfdt.gasel.groupeldap.model.Group;
import fr.cfdt.gasel.groupeldap.util.BusinessUtils;
import fr.cfdt.gasel.ldap.GaselLDAPService;
import fr.cfdt.gasel.ldap.dto.GaselGroupeLDAPEntry;
import fr.cfdt.gasel.schema.v0.ebx.personne.PersonneType;
import fr.cfdt.gasel.schema.v0.ebx.personne.SelectPersonneRequestType.Pagination;
import fr.cfdt.gasel.service.ebx.personne.v0.StandardException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Author SZaoui
 */

@Service
public class GroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonClient personClient;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private GaselLDAPService gaselLDAPService;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private PersonService personService;

    public List<GroupDto> getAllGroups(){
        return groupMapper.listGroupModelToDto(groupRepository.findAll());
    }

    /**
     *
     * @param groupId
     */
    public void deleteGroup(Long groupId) {
        //recuperer l'id de la requette
       Optional<Group> group = groupRepository.findById(groupId);
       Long idRequest = group.isPresent() && group.get().getRequest() != null ? group.get().getRequest().getIdRequest() : null;
        //delete group
        groupRepository.deleteById(groupId);
        //delete request
        if(idRequest != null){
            requestRepository.deleteById(idRequest);
        }
    }

    /**
     *
     * @param group
     * @return
     */
    public GroupDto createGroup(GroupDto group) throws TechnicalException {
        // creer le groupe dans la base
        GroupDto groupDto = groupMapper.groupModelToDto(groupRepository.save(groupMapper.groupDtoToModel(group)));
        GaselGroupeLDAPEntry gaselGroupeLDAPEntry = new GaselGroupeLDAPEntry();
        if(groupDto != null) {
            //creation du groupe dans Ldap
            gaselGroupeLDAPEntry.setCfdtIntituleGroup(groupDto.getName());
            gaselGroupeLDAPEntry.setCn(String.valueOf(groupDto.getId()));
            gaselGroupeLDAPEntry.setDescription(groupDto.getDescription());
            gaselGroupeLDAPEntry.setCfdtTypeGroup("personnalise");
            if(groupDto.getRequest() != null){
                //recuperer la liste des membre
                gaselGroupeLDAPEntry.setMember(getLdapMembers(groupDto.getRequest().getRequest()));
            }
        }
        boolean resultLdapCreation = gaselLDAPService.createLpdapGroup(gaselGroupeLDAPEntry);
        //suppression du groupe suite a une excepion ldap
        if(!resultLdapCreation){
            groupRepository.deleteById(groupDto.getId());
            groupDto = null;
        } else {
            // update members number dans la table groupLdap
            groupDto.setMembersNumber(String.valueOf(gaselGroupeLDAPEntry.getMember().size()));
            groupRepository.save(groupMapper.groupDtoToModel(groupDto));
        }
        return groupDto;
    }

    /**
     *
     * @param request
     * @return
     */
    public List<String> getLdapMembers(String request) throws TechnicalException {
        List<String> result;
        List<PersonneDto> listMembers = personService.getMembers(request , null , null).getContent();
        result = listMembers.stream().map(p -> "uid="+p.getNpa()+",ou=utilisateurs,dc=cfdt,dc=fr").collect(Collectors.toList());
        return result;
    }
}
