package fr.cfdt.gasel.groupeldap.resource;

import fr.cfdt.gasel.groupeldap.dto.GroupDto;
import fr.cfdt.gasel.groupeldap.service.CsvWriterService;
import fr.cfdt.gasel.groupeldap.service.GroupService;
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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupResource.class);

    @Autowired
    GroupService groupService;

    @Autowired
    CsvWriterService csvWriterService;

    @GetMapping("/")
    @ApiModelProperty("Recupérer la liste des groupes")
    public Resources<GroupDto> getAllGroups(){
        LOGGER.info("Start Get All groups ");

        List<GroupDto> groups = groupService.getAllGroups();

        Link link = linkTo(methodOn(this.getClass()).getAllGroups()).withSelfRel();
        Resources<GroupDto> hateoasList = new Resources<>(groups, link);

        LOGGER.info("End Get All groups");
        return hateoasList;
    }

    @DeleteMapping("{idGroup}")
    @ApiModelProperty("Supprimer un groupe par ID")
    public void deleteGroup(@ApiParam("Id du groupe à supprimer") @PathVariable Long idGroup){
        LOGGER.info("start delete group by ID = {}", idGroup);
        groupService.deleteGroup(idGroup);
        LOGGER.info("end delete group by ID = {}", idGroup);
    }

    @PostMapping("{group}")
    @ApiModelProperty("Créer un Groupe")
    public GroupDto saveGroup(GroupDto group){
        return groupService.createGroup(group);
    }

}
