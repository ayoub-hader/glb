package fr.cfdt.gasel.groupeldap.mapper;

import fr.cfdt.gasel.groupeldap.dto.PersonneDto;
import fr.cfdt.gasel.groupeldap.model.Personne;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Author SZaoui
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonneDto personneModelToDto(Personne personne);

    List<PersonneDto> listPersonneModelToDto(List<Personne> personnes);
}
