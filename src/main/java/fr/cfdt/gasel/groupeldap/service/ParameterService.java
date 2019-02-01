package fr.cfdt.gasel.groupeldap.service;

import fr.cfdt.gasel.groupeldap.connector.ebxdb.RoleRepository;
import fr.cfdt.gasel.groupeldap.connector.ebxdb.TypeMandatRepository;
import fr.cfdt.gasel.groupeldap.connector.ebxdb.TypeResponsabiliteRepository;
import fr.cfdt.gasel.groupeldap.connector.ebxdb.TypeStructureRepository;
import fr.cfdt.gasel.groupeldap.dto.OrganismeInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.ResponsabiliteInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.RoleDto;
import fr.cfdt.gasel.groupeldap.dto.TypeStructureDto;
import fr.cfdt.gasel.groupeldap.mapper.ParametreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author SZaoui
 */

@Service
public class ParameterService {

    @Autowired
    TypeStructureRepository typeStructureRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TypeResponsabiliteRepository typeResponsabiliteRepository;

    @Autowired
    TypeMandatRepository typeMandatRepository;

    @Autowired
    ParametreMapper parametreMapper;

    /**
     *
     * @return
     */
    public List<TypeStructureDto> getAllStructureTypes() {
        return parametreMapper.listTypeStructureModelToDto(typeStructureRepository.findAll());
    }

    /**
     *
     * @return
     */
    public List<RoleDto> getAllRoles() {
        return parametreMapper.listRoleModelToDto(roleRepository.findAll());
    }

    /**
     *
     * @return
     */
    public List<ResponsabiliteInstanceDto> getAllResponsabiliteInstance() {
        return parametreMapper.listTypeResponsabiliteModelToDto(typeResponsabiliteRepository.findAll());
    }

    /**
     *
     * @return
     */
    public List<OrganismeInstanceDto> getAllOrganismeInstance() {
        return parametreMapper.listTypeMandatModelToDto(typeMandatRepository.findAll());
    }
}
