package fr.cfdt.gasel.groupeldap.mapper;

import fr.cfdt.gasel.groupeldap.dto.OrganismeInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.ResponsabiliteInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.RoleDto;
import fr.cfdt.gasel.groupeldap.dto.TypeStructureDto;
import fr.cfdt.gasel.groupeldap.model.Role;
import fr.cfdt.gasel.groupeldap.model.TypeMandat;
import fr.cfdt.gasel.groupeldap.model.TypeResponsabilite;
import fr.cfdt.gasel.groupeldap.model.TypeStructure;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParametreMapper {

    TypeStructureDto structureModelToDto(TypeStructure typeStructure);

    List<TypeStructureDto> listTypeStructureModelToDto(List<TypeStructure> typeStructures);

    RoleDto roleModelToDto(Role role);

    List<RoleDto> listRoleModelToDto(List<Role> roles);

    ResponsabiliteInstanceDto typeResponsabiliteModelToDto(TypeResponsabilite typeResponsabilite);

    List<ResponsabiliteInstanceDto> listTypeResponsabiliteModelToDto(List<TypeResponsabilite> typeResponsabilites);

    OrganismeInstanceDto typeMandatModelToDto(TypeMandat typeMandat);

    List<OrganismeInstanceDto> listTypeMandatModelToDto(List<TypeMandat> typeMandats);
}
