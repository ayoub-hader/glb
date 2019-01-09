package fr.cfdt.gasel.groupeldap.mapper;

import fr.cfdt.gasel.groupeldap.dto.StructureDto;
import fr.cfdt.gasel.groupeldap.model.Structure;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StructureMapper {
    StructureDto structureModelToDto(Structure personne);

    List<StructureDto> listStructureModelToDto(List<Structure> personnes);
}
