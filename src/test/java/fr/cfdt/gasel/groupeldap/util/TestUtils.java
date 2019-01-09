package fr.cfdt.gasel.groupeldap.util;

import fr.cfdt.gasel.groupeldap.dto.*;
import fr.cfdt.gasel.groupeldap.model.Group;
import fr.cfdt.gasel.groupeldap.model.Request;
import fr.cfdt.gasel.ldap.dto.GaselLDAPEntry;
import fr.cfdt.gasel.schema.v0.ebx.parametres.*;
import fr.cfdt.gasel.schema.v0.ebx.personne.PersonneType;
import fr.cfdt.gasel.schema.v0.ebx.personne.SelectPersonneResponseType;
import fr.cfdt.gasel.schema.v0.ebx.structure.SelectStructureResponseType;
import fr.cfdt.gasel.schema.v0.ebx.structure.StructureType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestUtils {

    //=============Parametres===============
    public static SelectTypeStructureResponseType createSelectParameterResponse(String responseCode){
        SelectTypeStructureResponseType resp = new SelectTypeStructureResponseType();
        TypeStructureType data = new TypeStructureType();
        TypeStructureType.Parametres param = new TypeStructureType.Parametres();
        param.getTypeStructure().addAll(createTypeStructureList());
        data.setParametres(param);
        resp.setData(data);
        return resp;
    }

    public static List<TypeStructureType.Parametres.TypeStructure> createTypeStructureList(){
        List<TypeStructureType.Parametres.TypeStructure> result = new ArrayList<>();
        result.add(createStructureTypeEbx(1));
        result.add(createStructureTypeEbx(2));
        result.add(createStructureTypeEbx(3));
        return result;
    }


    public static TypeStructureType.Parametres.TypeStructure createStructureTypeEbx(int index){
        ObjectFactory of = new ObjectFactory();
        TypeStructureType.Parametres.TypeStructure structureType = new TypeStructureType.Parametres.TypeStructure();
        structureType.setCode("code"+String.valueOf(index));
        structureType.setLibelle(of.createTypeStructureTypeParametresTypeStructureLibelle("libelle"+String.valueOf(index)));
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
    public static SelectPersonneResponseType createSelectPersonResponse(String responseCode){
        SelectPersonneResponseType resp = new SelectPersonneResponseType();
        PersonneType data = new PersonneType();
        PersonneType.Gasel gasel = new PersonneType.Gasel();
        gasel.getPersonne().addAll(createPersonList());
        data.setGasel(gasel);
        resp.setData(data);
        return resp;
    }

    public static List<PersonneType.Gasel.Personne> createPersonList(){
        List<PersonneType.Gasel.Personne> result = new ArrayList<>();
        result.add(createPersonEbx(1));
        result.add(createPersonEbx(2));
        result.add(createPersonEbx(3));
        return result;
    }


    public static PersonneType.Gasel.Personne createPersonEbx(int index){
        fr.cfdt.gasel.schema.v0.ebx.personne.ObjectFactory of = new fr.cfdt.gasel.schema.v0.ebx.personne.ObjectFactory();
        PersonneType.Gasel.Personne person = new PersonneType.Gasel.Personne();
        person.setId(index);
        person.setNom(of.createPersonneTypeGaselPersonneNom("name"+String.valueOf(index)));
        person.setPrenom(of.createPersonneTypeGaselPersonnePrenom("prenom"+String.valueOf(index)));
        return person;
    }

//    public static PersonneDto createPersonDto(int index){
//        PersonneDto result = new PersonneDto();
//        result.setIdPerson(index);
//        result.setFirstName("name"+String.valueOf(index));
//        result.setLastName("lastName"+String.valueOf(index));
//        return result;
//    }

//    public static List<PersonneDto> createPersonListDto(){
//        List<PersonneDto> result = new ArrayList<>();
//        PersonneDto person1 = createPersonDto(1);
//        PersonneDto person2 = createPersonDto(2);
//        PersonneDto person3 = createPersonDto(3);
//        return result;
//    }


    //===========================Structure================================

    public static SelectStructureResponseType createSelectStructureResponse(String responseCode){
        SelectStructureResponseType resp = new SelectStructureResponseType();
        StructureType data = new StructureType();
        StructureType.Gasel gasel = new StructureType.Gasel();
        gasel.getStructure().addAll(createStructureListEbx());
        data.setGasel(gasel);
        resp.setData(data);
        return resp;
    }

    public static List<StructureType.Gasel.Structure> createStructureListEbx(){
        List<StructureType.Gasel.Structure> result = new ArrayList<>();
        result.add(createStructureEbx(1));
        result.add(createStructureEbx(2));
        result.add(createStructureEbx(3));
        return result;
    }

    public static StructureType.Gasel.Structure createStructureEbx(int index){
        fr.cfdt.gasel.schema.v0.ebx.structure.ObjectFactory of = new fr.cfdt.gasel.schema.v0.ebx.structure.ObjectFactory();
        StructureType.Gasel.Structure structure = new StructureType.Gasel.Structure();
        structure.setAcronyme(of.createStructureTypeGaselStructureAcronyme("Acronyme"+String.valueOf(index)));
        structure.setMatricule(of.createStructureTypeGaselStructureMatricule("matricule"+String.valueOf(index)));
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

    public static RoleType.Parametres.Role createRole(int index){
        ObjectFactory objectFactory = new ObjectFactory();
        RoleType.Parametres.Role role = new RoleType.Parametres.Role();
        role.setId(index);
        role.setCode(objectFactory.createRoleTypeParametresRoleCode("code"+index));
        role.setLibelle(objectFactory.createRoleTypeParametresRoleLibelle("libelle"+index));
        role.setActif(objectFactory.createRoleTypeParametresRoleActif(true));
        return role;
    }

    public static  List<RoleType.Parametres.Role> createListRole(){
        List<RoleType.Parametres.Role> result = new ArrayList<>();
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

    public static  TypeResponsabiliteType.Parametres.TypeResponsabilite createTypeResponsabilte(int index){
        TypeResponsabiliteType.Parametres.TypeResponsabilite result = new TypeResponsabiliteType.Parametres.TypeResponsabilite();
        ObjectFactory of = new ObjectFactory();
        result.setId(index);
        result.setActif(of.createTypeResponsabiliteTypeParametresTypeResponsabiliteActif(true));
        result.setCode(of.createTypeResponsabiliteTypeParametresTypeResponsabiliteCode("code"+index));
        result.setLibelle(of.createTypeResponsabiliteTypeParametresTypeResponsabiliteLibelle("libelle"+index));
        result.setType(of.createTypeResponsabiliteTypeParametresTypeResponsabiliteType("type"+index));
        return result;
    }

    public static  List<TypeResponsabiliteType.Parametres.TypeResponsabilite> createListTypeResponsabilte(){
        List<TypeResponsabiliteType.Parametres.TypeResponsabilite> result = new ArrayList<>();
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

    public static TypeMandatType.Parametres.TypeMandat createTypeMandat(int index){
        TypeMandatType.Parametres.TypeMandat result = new TypeMandatType.Parametres.TypeMandat();
        ObjectFactory of = new ObjectFactory();
        result.setId(index);
        result.setActif(of.createTypeMandatTypeParametresTypeMandatActif(true));
        result.setCode(of.createTypeResponsabiliteTypeParametresTypeResponsabiliteCode("code"+index));
        result.setLibelle(of.createTypeResponsabiliteTypeParametresTypeResponsabiliteLibelle("libelle"+index));
        result.setType(of.createTypeMandatTypeParametresTypeMandatType("type"+index));
        return result;
    }

    public static  List<TypeMandatType.Parametres.TypeMandat> createListTypeMandat(){
        List<TypeMandatType.Parametres.TypeMandat> result = new ArrayList<>();
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
