package fr.cfdt.gasel.groupeldap.resource;

import fr.cfdt.gasel.groupeldap.dto.OrganismeInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.ResponsabiliteInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.RoleDto;
import fr.cfdt.gasel.groupeldap.dto.TypeStructureDto;
import fr.cfdt.gasel.groupeldap.service.ParameterService;
import fr.cfdt.gasel.service.ebx.parametres.v0.StandardException;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Author SZaoui
 */
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/parameters")
public class ParameterResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterResource.class);

    @Autowired
    ParameterService parameterService;

    /**
     *
     * @return
     */
    @GetMapping("/listeTypeStructure")
    @ApiModelProperty("Recupérer la liste des type structure")
    public Resources<TypeStructureDto> getAllTypeStructure(){
        LOGGER.info("Start getAllTypeStructure ");
        Resources<TypeStructureDto> hateoasList = null;
        try {
            List<TypeStructureDto> typeStructureDtos = parameterService.getAllStructureTypes();
            Link link = linkTo(methodOn(this.getClass()).getAllTypeStructure()).withSelfRel();
            hateoasList = new Resources<>(typeStructureDtos, link);
        } catch (StandardException e) {
            e.printStackTrace();
        }
        LOGGER.info("End getAllTypeStructure");
        return hateoasList;
    }

    /**
     *
     * @param type
     * @return
     */
    @GetMapping("{type}")
    @ApiModelProperty("Recupérer la liste des dénomination par type resp-mandat")
    public Resources<RoleDto> getAllDenominationByType(@ApiParam("type dénomination resp-mandat") @PathVariable String type){
        LOGGER.info("Start getAllDenominationByType ");
        Resources<RoleDto> hateoasList = null;
        List<RoleDto> result = null;
        try {
            List<RoleDto> tmp = parameterService.getAllRoles();
            if(tmp != null && !tmp.isEmpty() && type != null){
                result = tmp.stream().filter(r -> type.equalsIgnoreCase(splitRole(r.getCode()))).collect(Collectors.toList());
            }
            Link link = linkTo(methodOn(this.getClass()).getAllDenominationByType(type)).withSelfRel();
            hateoasList = new Resources<>(result, link);
        } catch (StandardException e) {
            e.printStackTrace();
        }
        LOGGER.info("End getAllDenominationByType");
        return hateoasList;
    }

    /**
     *
     * @return
     */
    @GetMapping("/listeResponsabiliteInstance")
    @ApiModelProperty("Recupérer la liste des Responsabilité Instance")
    public Resources<ResponsabiliteInstanceDto> getAllRespInstance(){
        LOGGER.info("Start getAllRespInstance ");
        Resources<ResponsabiliteInstanceDto> hateoasList = null;
        try {
            List<ResponsabiliteInstanceDto> responsabiliteInstanceDtos = parameterService.getAllResponsabiliteInstance();
            Link link = linkTo(methodOn(this.getClass()).getAllRespInstance()).withSelfRel();
            hateoasList = new Resources<>(responsabiliteInstanceDtos, link);
        } catch (StandardException e) {
            e.printStackTrace();
        }
        LOGGER.info("End getAllRespInstance");
        return hateoasList;
    }

    /**
     *
     * @return
     */
    @GetMapping("/listeOrganismeInstance")
    @ApiModelProperty("Recupérer la liste des Organisme Instance")
    public Resources<OrganismeInstanceDto> getAllOrganismeInstance(){
        LOGGER.info("Start getAllOrganismeInstance ");
        Resources<OrganismeInstanceDto> hateoasList = null;
        try {
            List<OrganismeInstanceDto> organismeInstanceDtos = parameterService.getAllOrganismeInstance();
            Link link = linkTo(methodOn(this.getClass()).getAllOrganismeInstance()).withSelfRel();
            hateoasList = new Resources<>(organismeInstanceDtos, link);
        } catch (StandardException e) {
            e.printStackTrace();
        }
        LOGGER.info("End getAllOrganismeInstance");
        return hateoasList;
    }

    /**
     * verifier si un role se termine par R ou M
     * @param roleCode
     * @return
     */
    private String splitRole(String roleCode){
        String result = null;
        String[] tmp = roleCode.split("-");
        if(tmp != null && tmp.length > 0){
            result = tmp[tmp.length-1];
        }
        return result;
    }

}
