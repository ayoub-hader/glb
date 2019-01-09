package fr.cfdt.gasel.groupeldap.mapper;

import fr.cfdt.gasel.groupeldap.dto.OrganismeInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.ResponsabiliteInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.RoleDto;
import fr.cfdt.gasel.groupeldap.dto.TypeStructureDto;
import fr.cfdt.gasel.groupeldap.util.TestUtils;
import fr.cfdt.gasel.schema.v0.ebx.parametres.RoleType;
import fr.cfdt.gasel.schema.v0.ebx.parametres.TypeMandatType;
import fr.cfdt.gasel.schema.v0.ebx.parametres.TypeResponsabiliteType;
import fr.cfdt.gasel.schema.v0.ebx.parametres.TypeStructureType;
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
        TypeStructureType.Parametres.TypeStructure type = TestUtils.createStructureTypeEbx(1);

        TypeStructureDto typeDto = parametreMapper.structureModelToDto(type);

        assertThat(typeDto, is(notNullValue()));
        assertEquals(typeDto.getId(), type.getUuid());
        assertEquals(typeDto.getCode(), type.getCode());
        assertEquals(typeDto.getLibelle(), type.getLibelle().getValue());

        // test null
        TypeStructureType.Parametres.TypeStructure typeStructureNull = null;
        //Then
        TypeStructureDto typeStructureDtoNull = parametreMapper.structureModelToDto(typeStructureNull);
        assertThat(typeStructureDtoNull, is(nullValue()));

        //test list type mapping
        List<TypeStructureType.Parametres.TypeStructure> typeStructures = TestUtils.createTypeStructureList();
        //Then
        List<TypeStructureDto> listTypeStructureDto = parametreMapper.listTypeStructureModelToDto(typeStructures);
        assertThat(listTypeStructureDto, is(notNullValue()));
        assertEquals(3, listTypeStructureDto.size());

        // list type is null
        List<TypeStructureType.Parametres.TypeStructure> listTypeStructureNull = null;
        //Then
        List<TypeStructureDto> listTypeStructureDtoNull = parametreMapper.listTypeStructureModelToDto(listTypeStructureNull);
        assertThat(listTypeStructureDtoNull, is(nullValue()));
    }

    @Test
    public void testRoleModelToDto(){
        RoleType.Parametres.Role role = TestUtils.createRole(1);

        RoleDto roleDto = parametreMapper.roleModelToDto(role);

        assertThat(roleDto, is(notNullValue()));
        assertEquals(roleDto.getId(), role.getId());
        assertEquals(roleDto.getCode(), role.getCode().getValue());
        assertEquals(roleDto.getLibelle(), role.getLibelle().getValue());
        assertEquals(roleDto.getActif(), role.getActif().getValue());

        // test null
        RoleType.Parametres.Role roleNull = null;
        //Then
        RoleDto roleDtoNull = parametreMapper.roleModelToDto(roleNull);
        assertThat(roleDtoNull, is(nullValue()));

        //test list role mapping
        List<RoleType.Parametres.Role> roles = TestUtils.createListRole();
        //Then
        List<RoleDto> listRoleDto = parametreMapper.listRoleModelToDto(roles);
        assertThat(listRoleDto, is(notNullValue()));
        assertEquals(3, listRoleDto.size());

        // list roles is null
        List<RoleType.Parametres.Role> listRoleNull = null;
        //Then
        List<RoleDto> listRoleDtoNull = parametreMapper.listRoleModelToDto(listRoleNull);
        assertThat(listRoleDtoNull, is(nullValue()));
    }

    @Test
    public void testTypeResponsabiliteModelToDto(){
        TypeResponsabiliteType.Parametres.TypeResponsabilite typeResponsabilite = TestUtils.createTypeResponsabilte(1);

        ResponsabiliteInstanceDto responsabiliteInstanceDto = parametreMapper.typeResponsabiliteModelToDto(typeResponsabilite);

        assertThat(responsabiliteInstanceDto, is(notNullValue()));
        assertEquals(responsabiliteInstanceDto.getId(), typeResponsabilite.getId());
        assertEquals(responsabiliteInstanceDto.getCode(), typeResponsabilite.getCode().getValue());
        assertEquals(responsabiliteInstanceDto.getLibelle(), typeResponsabilite.getLibelle().getValue());
        assertEquals(responsabiliteInstanceDto.getType(), typeResponsabilite.getType().getValue());

        // test null
        TypeResponsabiliteType.Parametres.TypeResponsabilite typeResponsabiliteNull = null;
        //Then
        ResponsabiliteInstanceDto responsabiliteInstanceDtoNull = parametreMapper.typeResponsabiliteModelToDto(typeResponsabiliteNull);
        assertThat(responsabiliteInstanceDtoNull, is(nullValue()));

        //test list resp instance mapping
        List<TypeResponsabiliteType.Parametres.TypeResponsabilite> typeResponsabilites = TestUtils.createListTypeResponsabilte();
        //Then
        List<ResponsabiliteInstanceDto> responsabiliteInstanceDtoList = parametreMapper.listTypeResponsabiliteModelToDto(typeResponsabilites);
        assertThat(responsabiliteInstanceDtoList, is(notNullValue()));
        assertEquals(3, responsabiliteInstanceDtoList.size());

        // list roles is null
        List<TypeResponsabiliteType.Parametres.TypeResponsabilite> lisTypeResponsabiliteNull = null;
        //Then
        List<ResponsabiliteInstanceDto> listRespInstanceDtoNull = parametreMapper.listTypeResponsabiliteModelToDto(lisTypeResponsabiliteNull);
        assertThat(listRespInstanceDtoNull, is(nullValue()));
    }


    @Test
    public void testTypeMandatModelToDto(){
        TypeMandatType.Parametres.TypeMandat typeMandat = TestUtils.createTypeMandat(1);

        OrganismeInstanceDto organismeInstanceDto = parametreMapper.typeMandatModelToDto(typeMandat);

        assertThat(organismeInstanceDto, is(notNullValue()));
        assertEquals(organismeInstanceDto.getId(), typeMandat.getId());
        assertEquals(organismeInstanceDto.getCode(), typeMandat.getCode().getValue());
        assertEquals(organismeInstanceDto.getLibelle(), typeMandat.getLibelle().getValue());
        assertEquals(organismeInstanceDto.getType(), typeMandat.getType().getValue());

        // test null
        TypeMandatType.Parametres.TypeMandat typeMandatNull = null;
        //Then
        OrganismeInstanceDto organismeInstanceDtoNull = parametreMapper.typeMandatModelToDto(typeMandatNull);
        assertThat(organismeInstanceDtoNull, is(nullValue()));

        //test list organisme instance mapping
        List<TypeMandatType.Parametres.TypeMandat> typeMandats = TestUtils.createListTypeMandat();
        //Then
        List<OrganismeInstanceDto> organismeInstanceDtoList = parametreMapper.listTypeMandatModelToDto(typeMandats);
        assertThat(organismeInstanceDtoList, is(notNullValue()));
        assertEquals(3, organismeInstanceDtoList.size());

        // list type mandat is null
        List<TypeMandatType.Parametres.TypeMandat> lisTypeMandatNull = null;
        //Then
        List<OrganismeInstanceDto> listOrganismeInstanceDtoNull = parametreMapper.listTypeMandatModelToDto(lisTypeMandatNull);
        assertThat(listOrganismeInstanceDtoNull, is(nullValue()));
    }

}
