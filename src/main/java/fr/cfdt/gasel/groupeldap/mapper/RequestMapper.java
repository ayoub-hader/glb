package fr.cfdt.gasel.groupeldap.mapper;

import fr.cfdt.gasel.groupeldap.dto.RequestDto;
import fr.cfdt.gasel.groupeldap.model.Request;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    RequestDto mapRequestModeltodto(Request request);

    List<RequestDto> mapListRequestModeltodto(List<Request> request);
}
