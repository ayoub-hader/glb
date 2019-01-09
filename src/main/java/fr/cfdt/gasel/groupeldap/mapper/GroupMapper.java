package fr.cfdt.gasel.groupeldap.mapper;

import fr.cfdt.gasel.groupeldap.dto.GroupDto;
import fr.cfdt.gasel.groupeldap.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(source = "idGroup", target = "id")
    GroupDto groupModelToDto(Group group);

    List<GroupDto> listGroupModelToDto(List<Group> groups);

    @Mapping(source = "id", target = "idGroup")
    Group groupDtoToModel(GroupDto group);
}
