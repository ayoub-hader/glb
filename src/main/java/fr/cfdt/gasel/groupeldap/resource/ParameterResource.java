package fr.cfdt.gasel.groupeldap.resource;

import fr.cfdt.gasel.groupeldap.dto.OrganismeInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.ResponsabiliteInstanceDto;
import fr.cfdt.gasel.groupeldap.dto.RoleDto;
import fr.cfdt.gasel.groupeldap.dto.TypeStructureDto;
import fr.cfdt.gasel.groupeldap.service.ParameterService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
        List<TypeStructureDto> typeStructureDtos = parameterService.getAllStructureTypes();
        Link link = linkTo(methodOn(this.getClass()).getAllTypeStructure()).withSelfRel();
        LOGGER.info("End getAllTypeStructure");
        return new Resources<>(typeStructureDtos, link);
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
        List<RoleDto> tmp = parameterService.getAllRoles();
        if(tmp != null && !tmp.isEmpty() && type != null){
            result = tmp.stream().filter(r -> type.equalsIgnoreCase(splitRole(r.getCode()))).collect(Collectors.toList());
        }
        Link link = linkTo(methodOn(this.getClass()).getAllDenominationByType(type)).withSelfRel();
        hateoasList = new Resources<>(result, link);
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
        List<ResponsabiliteInstanceDto> responsabiliteInstanceDtos = parameterService.getAllResponsabiliteInstance();
        Link link = linkTo(methodOn(this.getClass()).getAllRespInstance()).withSelfRel();
        hateoasList = new Resources<>(responsabiliteInstanceDtos, link);
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
        List<OrganismeInstanceDto> organismeInstanceDtos = parameterService.getAllOrganismeInstance();
        Link link = linkTo(methodOn(this.getClass()).getAllOrganismeInstance()).withSelfRel();
        hateoasList = new Resources<>(organismeInstanceDtos, link);
        LOGGER.info("End getAllOrganismeInstance");
        return hateoasList;
    }

    /**
     *
     * @param codes
     * @return
     */
    @GetMapping("/typeStructure/{ids}")
    @ApiModelProperty("Recupérer type structure par codes")
    public Resources<TypeStructureDto> getTypeStructureByIds(@PathVariable String codes){
        LOGGER.info("Start getTypeStructureByIds ");
        Resources<TypeStructureDto> hateoasList = null;
        List<TypeStructureDto> typeStructureDtos = parameterService.getAllStructureTypes();
        List<String> listIds = Arrays.asList(codes.split(","));
        List<TypeStructureDto> tmp = typeStructureDtos.stream().filter(t -> listIds.contains(t.getId().toString())).collect(Collectors.toList());
        Link link = linkTo(methodOn(this.getClass()).getTypeStructureByIds(codes)).withSelfRel();
        hateoasList = new Resources<>(tmp, link);
        LOGGER.info("End getTypeStructureByIds");
        return hateoasList;
    }

    /**
     *
     * @param type
     * @return
     */
    @GetMapping("{type}/{ids}")
    @ApiModelProperty("Recupérer la liste des dénomination par ids")
    public Resources<RoleDto> getAllDenominationByIds(@ApiParam("type dénomination resp-mandat") @PathVariable String type , @PathVariable String ids){
        LOGGER.info("Start getAllDenominationByIds ");
        Resources<RoleDto> hateoasList = null;
        List<RoleDto> result = null;
        List<RoleDto> tmp = parameterService.getAllRoles();
        if(tmp != null && !tmp.isEmpty() && type != null){
            result = tmp.stream().filter(r -> type.equalsIgnoreCase(splitRole(r.getCode())) && ids.contains(r.getId().toString())).collect(Collectors.toList());
        }
        Link link = linkTo(methodOn(this.getClass()).getAllDenominationByIds(type , ids)).withSelfRel();
        hateoasList = new Resources<>(result, link);
        LOGGER.info("End getAllDenominationByIds");
        return hateoasList;
    }

    /**
     *
     * @return
     */
    @GetMapping("/listeResponsabiliteInstance/{ids}")
    @ApiModelProperty("Recupérer la liste des Responsabilité Instance par ids")
    public Resources<ResponsabiliteInstanceDto> getRespInstanceByIds(@PathVariable String ids){
        LOGGER.info("Start getRespInstanceByIds ");
        Resources<ResponsabiliteInstanceDto> hateoasList = null;
        List<ResponsabiliteInstanceDto> responsabiliteInstanceDtos = parameterService.getAllResponsabiliteInstance();
        List<String> listIds = Arrays.asList(ids.split(","));
        List<ResponsabiliteInstanceDto> tmp = responsabiliteInstanceDtos.stream().filter(t -> listIds.contains(t.getId().toString())).collect(Collectors.toList());
        Link link = linkTo(methodOn(this.getClass()).getRespInstanceByIds(ids)).withSelfRel();
        hateoasList = new Resources<>(tmp, link);
        LOGGER.info("End getRespInstanceByIds");
        return hateoasList;
    }

    /**
     *
     * @return
     */
    @GetMapping("/listeOrganismeInstance/{ids}")
    @ApiModelProperty("Recupérer la liste des Organisme Instance by ids")
    public Resources<OrganismeInstanceDto> getOrganismeInstanceByIds(@PathVariable String ids){
        LOGGER.info("Start getOrganismeInstanceByIds ");
        Resources<OrganismeInstanceDto> hateoasList = null;
        List<OrganismeInstanceDto> organismeInstanceDtos = parameterService.getAllOrganismeInstance();
        List<String> listIds = Arrays.asList(ids.split(","));
        List<OrganismeInstanceDto> tmp = organismeInstanceDtos.stream().filter(t -> listIds.contains(t.getId().toString())).collect(Collectors.toList());
        Link link = linkTo(methodOn(this.getClass()).getOrganismeInstanceByIds(ids)).withSelfRel();
        hateoasList = new Resources<>(tmp, link);
        LOGGER.info("End getOrganismeInstanceByIds");
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
