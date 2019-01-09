package fr.cfdt.gasel.groupeldap.service;

import fr.cfdt.gasel.groupeldap.connector.ParameterClient;
import fr.cfdt.gasel.groupeldap.dto.OrganismeInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.ResponsabiliteInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.RoleDto;
import fr.cfdt.gasel.groupeldap.dto.TypeStructureDto;
import fr.cfdt.gasel.groupeldap.mapper.ParametreMapper;
import fr.cfdt.gasel.service.ebx.parametres.v0.StandardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author SZaoui
 */

@Service
public class ParameterService {

    @Autowired
    ParameterClient parameterClient;

    @Autowired
    ParametreMapper parametreMapper;

    /**
     *
     * @return
     * @throws StandardException
     */
    @Cacheable(value = "typesStructure")
    public List<TypeStructureDto> getAllStructureTypes() throws StandardException {
        return parametreMapper.listTypeStructureModelToDto(parameterClient.getAllTypeStructure());
    }

    @Cacheable(value = "roles")
    public List<RoleDto> getAllRoles() throws StandardException {
        return parametreMapper.listRoleModelToDto(parameterClient.getListRoles());
    }

    @Cacheable(value = "typeResponsabilites")
    public List<ResponsabiliteInstanceDto> getAllResponsabiliteInstance() throws StandardException {
        return parametreMapper.listTypeResponsabiliteModelToDto(parameterClient.getAllTypeResponsabilite());
    }

    @Cacheable(value = "typeMandats")
    public List<OrganismeInstanceDto> getAllOrganismeInstance() throws StandardException {
        return parametreMapper.listTypeMandatModelToDto(parameterClient.getAllTypeMandat());
    }
}
