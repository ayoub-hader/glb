package fr.cfdt.gasel.groupeldap.util;

import fr.cfdt.gasel.groupeldap.dto.*;
import fr.cfdt.gasel.groupeldap.model.*;
import fr.cfdt.gasel.ldap.dto.GaselLDAPEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestUtils {

    //=============Parametres===============
    public static List<TypeStructure> createTypeStructureList(){
        List<TypeStructure> result = new ArrayList<>();
        result.add(createStructureTypeEbx(1));
        result.add(createStructureTypeEbx(2));
        result.add(createStructureTypeEbx(3));
        return result;
    }


    public static TypeStructure createStructureTypeEbx(int index){
        TypeStructure structureType = new TypeStructure();
        structureType.setCode("code"+index);
        structureType.setLibelle("libelle"+index);
        return structureType;
    }

    public static TypeStructureDto createTypeStructureDto(int index){
        TypeStructureDto typeStructure = new TypeStructureDto();
        typeStructure.setCode("code"+index);
        typeStructure.setId(index);
        typeStructure.setLibelle("libelle"+index);
        return typeStructure;
    }

    public static List<TypeStructureDto> createListTypeStructureDto(){
        List<TypeStructureDto> result = new ArrayList<>();
        result.add(createTypeStructureDto(1));
        result.add(createTypeStructureDto(2));
        result.add(createTypeStructureDto(3));
        return  result;
    }

    //===================Personne===========================

    public static Personne createPersonEbx(int index){
        Personne person = new Personne();
        person.setId(Long.valueOf(index));
        person.setNom("name"+index);
        person.setPrenom("prenom"+index);
        return person;
    }

    public static PersonneDto createPersonDto(Long index){
        PersonneDto result = new PersonneDto();
        result.setId(index);
        result.setNom("name"+String.valueOf(index));
        result.setPrenom("lastName"+String.valueOf(index));
        return result;
    }

    public static List<PersonneDto> createPersonListDto(){
        List<PersonneDto> result = new ArrayList<>();
        PersonneDto person1 = createPersonDto(Long.valueOf(1));
        PersonneDto person2 = createPersonDto(Long.valueOf(2));
        PersonneDto person3 = createPersonDto(Long.valueOf(3));
        result.add(person1);
        result.add(person2);
        result.add(person3);
        return result;
    }

    public static Page<PersonneDto> createPersonPageDto(){
        List<PersonneDto> result = new ArrayList<>();
        PersonneDto person1 = createPersonDto(Long.valueOf(1));
        PersonneDto person2 = createPersonDto(Long.valueOf(2));
        PersonneDto person3 = createPersonDto(Long.valueOf(3));
        result.add(person1);
        result.add(person2);
        result.add(person3);
        return new PageImpl<>(result);
    }


    //===========================Structure================================


    public static List<Structure> createStructureListEbx(){
        List<Structure> result = new ArrayList<>();
        result.add(createStructureEbx(1));
        result.add(createStructureEbx(2));
        result.add(createStructureEbx(3));
        return result;
    }

    public static Structure createStructureEbx(int index){
        Structure structure = new Structure();
        structure.setAcronyme("Acronyme"+index);
        structure.setMatricule("matricule"+index);
        return structure;
    }

    //==========================Groupe=========================
    public static List<Group> createListGroup(){
        List<Group> list = new ArrayList<>();
        list.add(createGroup(Long.valueOf(1)));
        list.add(createGroup(Long.valueOf(2)));
        list.add(createGroup(Long.valueOf(3)));
        return list;
    }

    public static Group createGroup(Long index){
        Group result = new Group();
        Request req = new Request();
        req.setIdRequest(Long.valueOf(1));
        result.setIdGroup(index);
        result.setDescription("Description"+index);
//        result.setMembersNumber(String.valueOf(index));
        result.setName("Groupe"+index);
        result.setRequest(req);
        return  result;
    }

    public static List<GroupDto> createListGroupDto(){
        List<GroupDto> list = new ArrayList<>();
        list.add(createGroupDto(Long.valueOf(1)));
        list.add(createGroupDto(Long.valueOf(2)));
        list.add(createGroupDto(Long.valueOf(3)));
        return list;
    }

    public static GroupDto createGroupDto(Long index){
        GroupDto result = new GroupDto();
        RequestDto req = new RequestDto();
        req.setIdRequest(Long.valueOf(1));
        result.setId(index);
        result.setDescription("Description"+index);
        result.setMembersNumber(String.valueOf(index));
        result.setName("Groupe"+index);
        result.setRequest(req);
        return  result;
    }

//    =================================================ldapClient====================================
    public static GaselLDAPEntry createLdapUser(String npa){
        GaselLDAPEntry result = new GaselLDAPEntry();
        result.setUid(npa);
        return result;
    }

    public static List<GaselLDAPEntry> createListLdapUser(){
        List<GaselLDAPEntry> result = new ArrayList<>();
        result.add(createLdapUser("00021"));
        result.add(createLdapUser("00022"));
        return result;
    }

//    ================================Request======================================
    public static  Request createRequest(int index){
        Request request = new Request();
        request.setIdRequest(Long.valueOf(index));
        request.setCreationDate(new Date());
        request.setLastExecutionDate(new Date());
        request.setExecutor("Executor"+index);
        request.setRequest("Request"+index);
        return request;
    }

    public static  List<Request> createListRequest(){
        List<Request> result = new ArrayList<>();
        result.add(createRequest(1));
        result.add(createRequest(2));
        result.add(createRequest(3));
        return result;
    }

    // ==================== Role ====================

    public static Role createRole(int index){
        Role role = new Role();
        role.setId(index);
        role.setCode("code"+index);
        role.setLibelle("libelle"+index);
        role.setActif(true);
        return role;
    }

    public static  List<Role> createListRole(){
        List<Role> result = new ArrayList<>();
        result.add(createRole(1));
        result.add(createRole(2));
        result.add(createRole(3));
        return result;
    }

    public static RoleDto createRoleDto(int index){
        RoleDto role = new RoleDto();
        role.setId(index);
        role.setCode("code"+index);
        role.setLibelle("libelle"+index);
        role.setActif(true);
        return role;
    }

    public static  List<RoleDto> createListRoleDto(){
        List<RoleDto> result = new ArrayList<>();
        result.add(createRoleDto(1));
        result.add(createRoleDto(2));
        result.add(createRoleDto(3));
        return result;
    }

    // ============================ Responsablite instance =============================

    public static TypeResponsabilite createTypeResponsabilte(int index){
        TypeResponsabilite result = new TypeResponsabilite();
        result.setId(index);
        result.setCode("code"+index);
        result.setLibelle("libelle"+index);
        result.setType("type"+index);
        return result;
    }

    public static  List<TypeResponsabilite> createListTypeResponsabilte(){
        List<TypeResponsabilite> result = new ArrayList<>();
        result.add(createTypeResponsabilte(1));
        result.add(createTypeResponsabilte(2));
        result.add(createTypeResponsabilte(3));
        return result;
    }
    //DTO
    public static ResponsabiliteInstanceDto createTypeResponsabilteDto(int index){
        ResponsabiliteInstanceDto result = new ResponsabiliteInstanceDto();
        result.setId(index);
        result.setCode("code"+index);
        result.setLibelle("libelle"+index);
        result.setType("type"+index);
        return result;
    }

    public static  List<ResponsabiliteInstanceDto> createListTypeResponsabilteDto(){
        List<ResponsabiliteInstanceDto> result = new ArrayList<>();
        result.add(createTypeResponsabilteDto(1));
        result.add(createTypeResponsabilteDto(2));
        result.add(createTypeResponsabilteDto(3));
        return result;
    }

    // ============================ Organisme instance =============================

    public static TypeMandat createTypeMandat(int index){
        TypeMandat result = new TypeMandat();
        result.setId(index);
        result.setCode("code"+index);
        result.setLibelle("libelle"+index);
        result.setType("type"+index);
        return result;
    }

    public static  List<TypeMandat> createListTypeMandat(){
        List<TypeMandat> result = new ArrayList<>();
        result.add(createTypeMandat(1));
        result.add(createTypeMandat(2));
        result.add(createTypeMandat(3));
        return result;
    }
    //DTO
    public static OrganismeInstanceDto createTypeMandatDto(int index){
        OrganismeInstanceDto result = new OrganismeInstanceDto();
        result.setId(index);
        result.setCode("code"+index);
        result.setLibelle("libelle"+index);
        result.setType("type"+index);
        return result;
    }

    public static  List<OrganismeInstanceDto> createListTypeMandatDto(){
        List<OrganismeInstanceDto> result = new ArrayList<>();
        result.add(createTypeMandatDto(1));
        result.add(createTypeMandatDto(2));
        result.add(createTypeMandatDto(3));
        return result;
    }
}
