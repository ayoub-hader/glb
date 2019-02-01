package fr.cfdt.gasel.groupeldap.mapper;

import fr.cfdt.gasel.groupeldap.dto.OrganismeInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.ResponsabiliteInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.RoleDto;
import fr.cfdt.gasel.groupeldap.dto.TypeStructureDto;
import fr.cfdt.gasel.groupeldap.model.Role;
import fr.cfdt.gasel.groupeldap.model.TypeMandat;
import fr.cfdt.gasel.groupeldap.model.TypeResponsabilite;
import fr.cfdt.gasel.groupeldap.model.TypeStructure;
import fr.cfdt.gasel.groupeldap.util.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Author SZaoui
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ParametreMapperTest {

    private ParametreMapper parametreMapper = new ParametreMapperImpl();

    @Test
    public void testStructureModelToDto(){
        TypeStructure type = TestUtils.createStructureTypeEbx(1);

        TypeStructureDto typeDto = parametreMapper.structureModelToDto(type);

        assertThat(typeDto, is(notNullValue()));
        assertEquals(typeDto.getCode(), type.getCode());
        assertEquals(typeDto.getLibelle(), type.getLibelle());

        // test null
        TypeStructure typeStructureNull = null;
        //Then
        TypeStructureDto typeStructureDtoNull = parametreMapper.structureModelToDto(typeStructureNull);
        assertThat(typeStructureDtoNull, is(nullValue()));

        //test list type mapping
        List<TypeStructure> typeStructures = TestUtils.createTypeStructureList();
        //Then
        List<TypeStructureDto> listTypeStructureDto = parametreMapper.listTypeStructureModelToDto(typeStructures);
        assertThat(listTypeStructureDto, is(notNullValue()));
        assertEquals(3, listTypeStructureDto.size());

        // list type is null
        List<TypeStructure> listTypeStructureNull = null;
        //Then
        List<TypeStructureDto> listTypeStructureDtoNull = parametreMapper.listTypeStructureModelToDto(listTypeStructureNull);
        assertThat(listTypeStructureDtoNull, is(nullValue()));
    }

    @Test
    public void testRoleModelToDto(){
        Role role = TestUtils.createRole(1);

        RoleDto roleDto = parametreMapper.roleModelToDto(role);

        assertThat(roleDto, is(notNullValue()));
        assertEquals(roleDto.getId(), role.getId());
        assertEquals(roleDto.getCode(), role.getCode());
        assertEquals(roleDto.getLibelle(), role.getLibelle());
        assertEquals(roleDto.getActif(), role.getActif());

        // test null
        Role roleNull = null;
        //Then
        RoleDto roleDtoNull = parametreMapper.roleModelToDto(roleNull);
        assertThat(roleDtoNull, is(nullValue()));

        //test list role mapping
        List<Role> roles = TestUtils.createListRole();
        //Then
        List<RoleDto> listRoleDto = parametreMapper.listRoleModelToDto(roles);
        assertThat(listRoleDto, is(notNullValue()));
        assertEquals(3, listRoleDto.size());

        // list roles is null
        List<Role> listRoleNull = null;
        //Then
        List<RoleDto> listRoleDtoNull = parametreMapper.listRoleModelToDto(listRoleNull);
        assertThat(listRoleDtoNull, is(nullValue()));
    }

    @Test
    public void testTypeResponsabiliteModelToDto(){
        TypeResponsabilite typeResponsabilite = TestUtils.createTypeResponsabilte(1);

        ResponsabiliteInstanceDto responsabiliteInstanceDto = parametreMapper.typeResponsabiliteModelToDto(typeResponsabilite);

        assertThat(responsabiliteInstanceDto, is(notNullValue()));
        assertEquals(responsabiliteInstanceDto.getId(), typeResponsabilite.getId());
        assertEquals(responsabiliteInstanceDto.getCode(), typeResponsabilite.getCode());
        assertEquals(responsabiliteInstanceDto.getLibelle(), typeResponsabilite.getLibelle());
        assertEquals(responsabiliteInstanceDto.getType(), typeResponsabilite.getType());

        // test null
        TypeResponsabilite typeResponsabiliteNull = null;
        //Then
        ResponsabiliteInstanceDto responsabiliteInstanceDtoNull = parametreMapper.typeResponsabiliteModelToDto(typeResponsabiliteNull);
        assertThat(responsabiliteInstanceDtoNull, is(nullValue()));

        //test list resp instance mapping
        List<TypeResponsabilite> typeResponsabilites = TestUtils.createListTypeResponsabilte();
        //Then
        List<ResponsabiliteInstanceDto> responsabiliteInstanceDtoList = parametreMapper.listTypeResponsabiliteModelToDto(typeResponsabilites);
        assertThat(responsabiliteInstanceDtoList, is(notNullValue()));
        assertEquals(3, responsabiliteInstanceDtoList.size());

        // list roles is null
        List<TypeResponsabilite> lisTypeResponsabiliteNull = null;
        //Then
        List<ResponsabiliteInstanceDto> listRespInstanceDtoNull = parametreMapper.listTypeResponsabiliteModelToDto(lisTypeResponsabiliteNull);
        assertThat(listRespInstanceDtoNull, is(nullValue()));
    }


    @Test
    public void testTypeMandatModelToDto(){
        TypeMandat typeMandat = TestUtils.createTypeMandat(1);

        OrganismeInstanceDto organismeInstanceDto = parametreMapper.typeMandatModelToDto(typeMandat);

        assertThat(organismeInstanceDto, is(notNullValue()));
        assertEquals(organismeInstanceDto.getId(), typeMandat.getId());
        assertEquals(organismeInstanceDto.getCode(), typeMandat.getCode());
        assertEquals(organismeInstanceDto.getLibelle(), typeMandat.getLibelle());
        assertEquals(organismeInstanceDto.getType(), typeMandat.getType());

        // test null
        TypeMandat typeMandatNull = null;
        //Then
        OrganismeInstanceDto organismeInstanceDtoNull = parametreMapper.typeMandatModelToDto(typeMandatNull);
        assertThat(organismeInstanceDtoNull, is(nullValue()));

        //test list organisme instance mapping
        List<TypeMandat> typeMandats = TestUtils.createListTypeMandat();
        //Then
        List<OrganismeInstanceDto> organismeInstanceDtoList = parametreMapper.listTypeMandatModelToDto(typeMandats);
        assertThat(organismeInstanceDtoList, is(notNullValue()));
        assertEquals(3, organismeInstanceDtoList.size());

        // list type mandat is null
        List<TypeMandat> lisTypeMandatNull = null;
        //Then
        List<OrganismeInstanceDto> listOrganismeInstanceDtoNull = parametreMapper.listTypeMandatModelToDto(lisTypeMandatNull);
        assertThat(listOrganismeInstanceDtoNull, is(nullValue()));
    }

}
