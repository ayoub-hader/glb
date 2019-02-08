package fr.cfdt.gasel.groupeldap.service;

import fr.cfdt.gasel.groupeldap.connector.ebxdb.StructureRepository;
import fr.cfdt.gasel.groupeldap.connector.ebxdb.StructureRepositoryImpl;
import fr.cfdt.gasel.groupeldap.connector.groupdb.GroupRepository;
import fr.cfdt.gasel.groupeldap.dto.GroupDto;
import fr.cfdt.gasel.groupeldap.dto.PersonneDto;
import fr.cfdt.gasel.groupeldap.dto.RequestDto;
import fr.cfdt.gasel.groupeldap.enumeration.TypeStructureEnum;
import fr.cfdt.gasel.groupeldap.exception.TechnicalException;
import fr.cfdt.gasel.groupeldap.mapper.GroupMapper;
import fr.cfdt.gasel.groupeldap.model.Group;
import fr.cfdt.gasel.groupeldap.model.Structure;
import fr.cfdt.gasel.groupeldap.properties.LdapGroupProperties;
import fr.cfdt.gasel.groupeldap.util.DateUtils;
import fr.cfdt.gasel.ldap.GaselLDAPService;
import fr.cfdt.gasel.ldap.dto.GaselGroupeLDAPEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Author SZaoui
 */

@Service
@EnableConfigurationProperties(LdapGroupProperties.class)
public class GroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    private static final int MAX_SIZE_IN = 200;

    @Autowired
    private LdapGroupProperties ldapGroupProperties;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private GaselLDAPService gaselLDAPService;

    @Autowired
    private PersonService personService;

    @Autowired
    private StructureRepository structureRepository;

    @Autowired
    private StructureRepositoryImpl structureRepositoryImpl;

    public List<GroupDto> getAllGroups() {
        return groupMapper.listGroupModelToDto(groupRepository.findAll());
    }

    /**
     * @param groupIds
     */
    public void deleteGroup(List<Long> groupIds) {
        groupIds.forEach(groupId -> {
            //recuperer l'id de la requette
            Optional<Group> group = groupRepository.findById(groupId);
            Long idRequest = group.isPresent() && group.get().getRequest() != null ? group.get().getRequest().getIdRequest() : null;
            //delete group
            // supprimer le groupe ldap
            boolean resultLdap = gaselLDAPService.deleteLdapGroup(groupId.toString());
            if (resultLdap) {
                groupRepository.deleteById(groupId);
            }
        });
    }

    /**
     * @param group
     * @return
     */
    public GroupDto createGroup(GroupDto group) throws TechnicalException {
        // creer le groupe dans la base
        RequestDto requestDto = new RequestDto();
        requestDto.setRequest(createQuery(group));
        group.setRequest(requestDto);
        GroupDto groupDto = groupMapper.groupModelToDto(groupRepository.save(groupMapper.groupDtoToModel(group)));
        GaselGroupeLDAPEntry gaselGroupeLDAPEntry = new GaselGroupeLDAPEntry();
        if (groupDto != null) {
            //creation du groupe dans Ldap
            gaselGroupeLDAPEntry.setCfdtIntituleGroup(groupDto.getName());
            gaselGroupeLDAPEntry.setCn(String.valueOf(groupDto.getId()));
            gaselGroupeLDAPEntry.setDescription(groupDto.getDescription());
            gaselGroupeLDAPEntry.setCfdtTypeGroup("personnalise");
            if (groupDto.getRequest() != null) {
                //recuperer la liste des membre
                gaselGroupeLDAPEntry.setMember(getLdapMembers(groupDto.getRequest().getRequest()));
            }
        }
        boolean resultLdapCreation = gaselLDAPService.createLpdapGroup(gaselGroupeLDAPEntry);
        //suppression du groupe suite a une excepion ldap
        if (!resultLdapCreation) {
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
     * @param request
     * @return
     */
    public List<String> getLdapMembers(String request) throws TechnicalException {
        List<String> result;
        Page<PersonneDto> listMembers = personService.getMembers(request, null, null);
        List<PersonneDto> tmp = listMembers != null ? listMembers.getContent() : new ArrayList<>();
        result = tmp.stream().map(p -> "uid=" + p.getNpa() + ",ou=utilisateurs,dc=cfdt,dc=fr").collect(Collectors.toList());
        if (result == null || result.size() <= 0) {
            result.add("uid=required_member,ou=utilisateurs,dc=cfdt,dc=fr");
        }
        return result;
    }

    /**
     *
     * @param group
     * @return
     */
    private String createQuery(GroupDto group){
        StringBuilder query = new StringBuilder("select DISTINCT pers.ID, pers.NOM, pers.SYNDICAT_ , pers.ADHERENT_NPA , pers.NOMNAISSANCE , pers.PRENOM from EBX_REP_PERSONNE pers ");
        Date currentDate = new Date();
        Date diff = new Date(currentDate.getTime() - TimeUnit.DAYS.toMillis(ldapGroupProperties.getDelai()));
        if((group.getRespInstances() != null && !group.getRespInstances().isEmpty()) ||  (group.getDenominationsResp() != null && !group.getDenominationsResp().isEmpty())){
            query.append("LEFT JOIN EBX_REP_PERSONNE_adherent_responsabilite resp ON pers.ID = resp.ID ");
            query.append("LEFT JOIN EBX_REP_STRUCTURE struct ON resp.adherent_responsabilite_porteSurStructure_ = struct.ID ");
            query.append(basicCondition());
            if(group.getRespInstances() != null && !group.getRespInstances().isEmpty()){
                query.append(" and resp.ADHERENT_RESPONSABILITE_TYPERESPONSABILITE_ IN  (");
                query.append(group.getRespInstances());
                query.append(") and (resp.ADHERENT_RESPONSABILITE_DATEFIN is null or resp.ADHERENT_RESPONSABILITE_DATEFIN >= '");
                query.append(DateUtils.dateToString(diff,DateUtils.DATE_PATTERN_STRING));
                query.append("')");
            }
            if(group.getDenominationsResp() != null && !group.getDenominationsResp().isEmpty()){
                query.append(" and resp.adherent_responsabilite_aPourDenomination_ IN  (");
                query.append(group.getDenominationsResp());
                query.append(")");
            }
            if(group.getStructures() != null && !group.getStructures().isEmpty()){
                query.append(" and resp.adherent_responsabilite_porteSurStructure_ IN  (");
                query.append(group.getStructures());
                query.append(")");
            }
            if(group.getTypesStructure() != null && !group.getTypesStructure().isEmpty()){
                query.append(" and struct.TYPE_ IN (");
                query.append(inPattern(group.getTypesStructure()));
                query.append(")");
            }
        } else if((group.getOrganismeInstances() != null && !group.getOrganismeInstances().isEmpty()) || (group.getDenominationsMandat() != null && !group.getDenominationsMandat().isEmpty())){
            query.append("LEFT JOIN EBX_REP_PERSONNE_personneSuivie_mandats mandat ON pers.ID = mandat.ID ");
            query.append("LEFT JOIN EBX_REP_STRUCTURE struct ON mandat.personneSuivie_mandats_structureAyantAttribue_ = struct.ID ");
            query.append(basicCondition());
            if(group.getOrganismeInstances() != null && !group.getOrganismeInstances().isEmpty()) {
                query.append(" and mandat.PERSONNESUIVIE_MANDATS_TYPEMANDAT_ IN  (");
                query.append(group.getOrganismeInstances());
                query.append(") and (mandat.PERSONNESUIVIE_MANDATS_DATEFINMANDAT is null or mandat.PERSONNESUIVIE_MANDATS_DATEFINMANDAT >= '");
                query.append(DateUtils.dateToString(diff, DateUtils.DATE_PATTERN_STRING));
                query.append("')");
            }
            if(group.getDenominationsMandat() != null && !group.getDenominationsMandat().isEmpty()){
                query.append(" and mandat.personneSuivie_mandats_aPourDenomination_ IN  (");
                query.append(group.getDenominationsMandat());
                query.append(")");
            }
            if(group.getStructures() != null && !group.getStructures().isEmpty()){
                query.append(" and mandat.personneSuivie_mandats_structureAyantAttribue_ IN  (");
                query.append(group.getStructures());
                query.append(")");
            }
            if(group.getTypesStructure() != null && !group.getTypesStructure().isEmpty()){
                query.append(" and struct.TYPE_ IN  (");
                query.append(inPattern(group.getTypesStructure()));
                query.append(")");
            }
        }
        else if(group.getStructures() != null && !group.getStructures().isEmpty()){
            query.append(" LEFT JOIN EBX_REP_STRUCTURE sect on pers.SECTION_= sect.ID ");
            query.append(" LEFT JOIN EBX_REP_STRUCTURE synd on pers.SYNDICAT_= synd.ID");
            query.append(basicCondition());
            query.append(perimetrePersonneFromBaseRepliquee(group.getTypesStructure() , group.getStructures()));
        } else {
            query = new StringBuilder();
        }
        return query.toString();
    }

    /**
     *
     * @return
     */
    private String basicCondition(){
        StringBuilder query = new StringBuilder(" where pers.STATUT_ = (Select id from EBX_REP_ENUMERATION where code = 'AD') ");
        return  query.toString();
    }

    /**
     *
     * @param typeStructure
     * @param ids
     * @return
     */
    private String perimetrePersonneFromBaseRepliquee(String typeStructure , String ids) {
        StringBuilder perimetreQuery = new StringBuilder();
        if (TypeStructureEnum.SYND.getType().equalsIgnoreCase(typeStructure)) {
            perimetreQuery.append(" AND (pers.SYNDICAT_ IN (").append(ids).append(")");
        } else if (TypeStructureEnum.UTR.getType().equalsIgnoreCase(typeStructure)) {
            perimetreQuery.append(" AND (pers.SYNDICAT_ IN (").append(ids).append(")");
        } else if (TypeStructureEnum.FD.getType().equalsIgnoreCase(typeStructure)) {
            perimetreQuery.append(" AND (((pers.FEDERATION_  IN (").append(ids).append(")")
                    .append(" OR (pers.ADHERENT_FEDERATIONORIGINE_  IN (").append(ids).append(")").append(" AND synd.TYPE_ = 'UTR')")
                    .append(" and synd.SYNDICATUTR_TRANSMISSIONINFOADHERENTSFD = '1'))");
        } else if (TypeStructureEnum.SSE.getType().equalsIgnoreCase(typeStructure)
                || TypeStructureEnum.SSR.getType().equalsIgnoreCase(typeStructure)) {
            perimetreQuery.append(" AND (pers.SECTION_ IN (").append(ids).append(")");
        } else if (TypeStructureEnum.URI.getType().equalsIgnoreCase(typeStructure)) {
            perimetreQuery.append(" AND ((pers.ADHERENT_URICOTISATION_ IN (").append(ids).append(")")
                    .append(" and synd.SYNDICATUTR_TRANSMISSIONINFOADHERENTSURI = '1')");
        } else if (TypeStructureEnum.UD.getType().equalsIgnoreCase(typeStructure)) {
            perimetreQuery.append(" AND (pers.ADHERENT_UDCOTISATION_ IN (").append(ids).append(")");
        } else if (TypeStructureEnum.UCC.getType().equalsIgnoreCase(typeStructure)) {
            perimetreQuery.append(" AND (pers.PERSONNESUIVIE_CADRE = '1'");
        } else if (TypeStructureEnum.URR.getType().equalsIgnoreCase(typeStructure)) {
            List<String> listIds = Arrays.asList(ids.split(","));
            List<Structure> urrs = structureRepository.findStructuresByIds(listIds);
            List<String> matricules = urrs.stream().map(s -> s.getMatricule().toUpperCase().replace("URR", "URI")).collect(Collectors.toList());
            List<Structure> uris = structureRepository.findStructuresByMatricules(matricules);
            String idsUri = uris.stream().map(s -> s.getId().toString()).collect( Collectors.joining( "," ) );
            List<Integer> idsUtrFilles = structureRepositoryImpl.findPerimetreStructureByType("(" + ids + ")" , "'"+TypeStructureEnum.UTR.getType()+"'");
            if (uris != null) {
                perimetreQuery.append(" AND (((pers.ADHERENT_URICOTISATION_ IN (").append(idsUri).append(")");
                perimetreQuery.append(" AND synd.TYPE_ = 'UTR' ");
                perimetreQuery.append(" AND pers.STATUT_ = (Select id from EBX_REP_ENUMERATION where code = 'AD'))");
                if (idsUtrFilles != null && !idsUtrFilles.isEmpty()) {
                    perimetreQuery.append(" OR  ");
                    perimetreQuery.append(pagingInClause(idsUtrFilles , "pers.SYNDICAT_"));
                }
                perimetreQuery.append(")");

            } else {
                perimetreQuery.append(" AND (1=2");
            }
        } else if (TypeStructureEnum.UCR.getType().equalsIgnoreCase(typeStructure)) {
            perimetreQuery.append(" AND (synd.TYPE_ = 'UTR' OR pers.FEDERATION_ IN (" + ids + ")");
        } else if (TypeStructureEnum.UFR.getType().equalsIgnoreCase(typeStructure)) {
            List<Structure> fds = structureRepositoryImpl.lienAdminByIdsAndType("(" + ids + ")", "("+TypeStructureEnum.FD.getType()+")");
            for (Structure s : fds) {
                String codeSection = s.getMatricule().substring(s.getMatricule().length() - 2);
                perimetreQuery.append(" AND (pers.SECTION_ IN (select sect.ID from EBX_REP_STRUCTURE sect where sect.SECTION_CODESECTION LIKE '" + codeSection + "%')) AND (sect.TYPE_ = '" + TypeStructureEnum.SSR + "'");
            }
        } else if (TypeStructureEnum.UFFA.getType().equalsIgnoreCase(typeStructure)) {
            perimetreQuery.append(" AND (pers.PERSONNESUIVIE_PRIVE ='0'");
        } else if (TypeStructureEnum.SOS.getType().equalsIgnoreCase(typeStructure)) {
            List<String> listIds = Arrays.asList(ids.split(","));
            List<Integer> str = structureRepository.findSectionImpactePar(listIds);
            if (str != null && !str.isEmpty()) {
                String idsSect = str.stream().map(s -> s.toString()).collect( Collectors.joining( "," ));
                perimetreQuery.append(" AND pers.SECTION_ IN (").append(idsSect);
            } else {
                perimetreQuery.append(" AND (1=2 ");
            }
        } else if (TypeStructureEnum.SOF.getType().equalsIgnoreCase(typeStructure)) {
            List<String> listIds = Arrays.asList(ids.split(","));
            List<Integer> listeSynd = structureRepository.findSyndicatImpactePar(listIds);
            List<Integer> listeBranche = structureRepository.findBrancheImpactePar(listIds);
            List<Integer> listeGroupe = structureRepository.findGroupeImpactePar(listIds);
            List<Structure> listeFD = structureRepositoryImpl.lienAdminByIdsAndType("("+ ids +")", "('"+TypeStructureEnum.FD.getType()+"')");
            if ((listeSynd != null && !listeSynd.isEmpty()) || (listeBranche != null && !listeBranche.isEmpty()) || (listeGroupe != null && !listeGroupe.isEmpty()) || (listeFD != null && !listeFD.isEmpty())) {
                StringBuilder predicatStrOp = new StringBuilder();
                if (listeSynd != null && !listeSynd.isEmpty()) {
                    String idsSynd = listeSynd.stream().map(s -> s.toString()).collect(Collectors.joining("," ));
                    predicatStrOp.append("(").append("pers.SYNDICAT_ IN (").append(idsSynd).append(")");
                }
                if (listeBranche != null && !listeBranche.isEmpty()) {
                    if (predicatStrOp.toString().isEmpty()) {
                        predicatStrOp.append(" (");
                    } else {
                        predicatStrOp.append(" or ");
                    }
                    String idsBranche = listeBranche.stream().map(s -> s.toString()).collect(Collectors.joining( "," ));
                    predicatStrOp.append("pers.ADHERENT_BRANCHEPROFESSIONNELLE_ IN (").append(idsBranche).append(")");
                }
                if (listeGroupe != null && !listeGroupe.isEmpty()) {
                    if (predicatStrOp.toString().isEmpty()) {
                        predicatStrOp.append("(");
                    } else {
                        predicatStrOp.append(" or ");
                    }
                    String idsGroupe = listeGroupe.stream().map(s -> s.toString()).collect(Collectors.joining( "," ));
                    predicatStrOp.append("pers.PERSONNESUIVIE_GROUPE_ IN (").append(idsGroupe).append(")");
                }
                if (listeFD != null && !listeFD.isEmpty()) {
                    if (predicatStrOp.toString().isEmpty()) {
                        perimetreQuery.append(" AND (1=2 ");
                    } else {
                        String idsFd = listeFD.stream().map(s -> s.getId().toString()).collect(Collectors.joining( "," ));
                        predicatStrOp.append(") and pers.FEDERATION_ IN (" + idsFd).append(")");
                        perimetreQuery.append(" AND (").append(predicatStrOp);
                    }
                }
            } else {
                perimetreQuery.append(" AND (1=2 ");
            }

        } else if (TypeStructureEnum.UIS.getType().equalsIgnoreCase(typeStructure) || TypeStructureEnum.UIT.getType().equalsIgnoreCase(typeStructure)
                || TypeStructureEnum.UL.getType().equalsIgnoreCase(typeStructure) || TypeStructureEnum.UT.getType().equalsIgnoreCase(typeStructure)) {
            List<String> listIds = Arrays.asList(ids.split(","));
            List<Integer> listeCodeGeo = structureRepository.findCodeGeographiqueImpacte(listIds);
            List<Integer> listeDepartement = structureRepository.findDepartementImpacte(listIds);
            StringBuilder predicatStrOp = new StringBuilder();
            //personne liee aux codegeo de la structure
            if (listeCodeGeo != null && !listeCodeGeo.isEmpty()) {
                String idsCode = listeCodeGeo.stream().map(s -> s.toString()).collect(Collectors.joining( "," ));
                predicatStrOp.append(" (pers.ADHERENT_CODEGEOGRAPHIQUE_ IN (").append(idsCode).append(")");
            }
            //personne liee aux departements impactes de la structure
            if (listeDepartement != null && !listeDepartement.isEmpty()) {
                if (!predicatStrOp.toString().isEmpty()) {
                    predicatStrOp.append("or");
                } else {
                    predicatStrOp.append("(");
                }
                String idsDep = listeDepartement.stream().map(s -> s.toString()).collect(Collectors.joining( "," ));
                predicatStrOp.append(" pers.ADHERENT_UDCOTISATION_ IN (").append(idsDep).append("))");
            }
            if (predicatStrOp.toString().isEmpty()) {
                predicatStrOp.append("1=2");
            }
            perimetreQuery.append(" AND (").append(predicatStrOp);
        }
        if (!TypeStructureEnum.CONF.getType().equalsIgnoreCase(typeStructure)){
            perimetreQuery.append(")");
        }
        return perimetreQuery.toString();
    }

    /**
     *
     * @param list
     * @param columnName
     * @return
     */
    private String pagingInClause(List<Integer> list , String columnName){
        StringBuilder result = null;
        if(list != null && !list.isEmpty() && list.size() <= 200){
            result = new StringBuilder(columnName);
            result.append(" IN (");
            result.append(list.stream().map(s -> s.toString()).collect( Collectors.joining( "," )));
            result.append(" )");
        } else if(list != null && !list.isEmpty()){
            int i = 0;
            while(i < list.size()){
                List<Integer> tmp;
                if (i + MAX_SIZE_IN >  list.size()) {
                    tmp = list.subList(i, list.size());
                } else {
                    tmp = list.subList(i, i + MAX_SIZE_IN);
                }
                if(tmp != null){
                    if(result == null){
                        result = new StringBuilder();
                    } else {
                        result.append(" OR ");
                    }
                    result.append(columnName);
                    result.append(" IN (");
                    result.append(tmp.stream().map(s -> s.toString()).collect( Collectors.joining( "," )));
                    result.append(" ) ");
                }
                i = i + MAX_SIZE_IN;
            }
        }
        return result.toString();
    }

    private String inPattern(String input){
        String result = null;
        if(input != null && !input.isEmpty()){
            String tmp[] = input.split(",");
            StringBuilder stringBuilder = new StringBuilder();
            result = Arrays.stream(tmp).map(s -> "'"+s+"'").collect(Collectors.joining(","));
        }
        return result;
    }
}
