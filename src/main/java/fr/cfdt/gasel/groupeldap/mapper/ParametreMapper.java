package fr.cfdt.gasel.groupeldap.mapper;

import fr.cfdt.gasel.groupeldap.dto.OrganismeInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.ResponsabiliteInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.RoleDto;
import fr.cfdt.gasel.groupeldap.dto.TypeStructureDto;
import fr.cfdt.gasel.schema.v0.ebx.parametres.RoleType;
import fr.cfdt.gasel.schema.v0.ebx.parametres.TypeMandatType;
import fr.cfdt.gasel.schema.v0.ebx.parametres.TypeResponsabiliteType;
import fr.cfdt.gasel.schema.v0.ebx.parametres.TypeStructureType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParametreMapper {

    @Mapping(source = "uuid", target = "id")
    TypeStructureDto structureModelToDto(TypeStructureType.Parametres.TypeStructure typeStructure);

    List<TypeStructureDto> listTypeStructureModelToDto(List<TypeStructureType.Parametres.TypeStructure> typeStructures);

    RoleDto roleModelToDto(RoleType.Parametres.Role role);

    List<RoleDto> listRoleModelToDto(List<RoleType.Parametres.Role> roles);

    ResponsabiliteInstanceDto typeResponsabiliteModelToDto(TypeResponsabiliteType.Parametres.TypeResponsabilite typeResponsabilite);

    List<ResponsabiliteInstanceDto> listTypeResponsabiliteModelToDto(List<TypeResponsabiliteType.Parametres.TypeResponsabilite> typeResponsabilites);

    OrganismeInstanceDto typeMandatModelToDto(TypeMandatType.Parametres.TypeMandat typeMandat);

    List<OrganismeInstanceDto> listTypeMandatModelToDto(List<TypeMandatType.Parametres.TypeMandat> typeMandats);
}
