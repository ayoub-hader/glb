package fr.cfdt.gasel.groupeldap.resource;

import fr.cfdt.gasel.groupeldap.batch.UpdateLdapBatch;
import fr.cfdt.gasel.groupeldap.dto.GroupDto;
import fr.cfdt.gasel.groupeldap.exception.TechnicalException;
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

    @Autowired
    UpdateLdapBatch updateLdapBatch;

    @GetMapping("/")
    @ApiModelProperty("Recupérer la liste des groupes")
    public Resources<GroupDto> getAllGroups() {
        LOGGER.info("Start Get All groups ");

        List<GroupDto> groups = groupService.getAllGroups();

        Link link = linkTo(methodOn(this.getClass()).getAllGroups()).withSelfRel();
        Resources<GroupDto> hateoasList = new Resources<>(groups, link);

        LOGGER.info("End Get All groups");
        return hateoasList;
    }

    @PostMapping("/deleteGroups")
    @ApiModelProperty("Supprimer une liste des groupes par ID")
    public List<Long> deleteGroup(@ApiParam("Ids des groupes à supprimes") @RequestBody List<Long> idsGroups) {
        LOGGER.info("start delete groups by ID");
        groupService.deleteGroup(idsGroups);
        LOGGER.info("end delete groups by ID ");
        return idsGroups;
    }

    @PostMapping("")
    @ApiModelProperty("Créer un Groupe")
    public GroupDto saveGroup(@RequestBody GroupDto group) throws TechnicalException {
        return groupService.createGroup(group);
    }

    @GetMapping("/updateLdapBatch")
    @ApiModelProperty("Batch update LDAP")
    public void updateLdap() throws TechnicalException {
        updateLdapBatch.updateLdap();
    }

}
