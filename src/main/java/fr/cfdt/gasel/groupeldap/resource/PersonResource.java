package fr.cfdt.gasel.groupeldap.resource;


import fr.cfdt.gasel.groupeldap.connector.groupdb.RequestRepository;
import fr.cfdt.gasel.groupeldap.dto.PersonneDto;
import fr.cfdt.gasel.groupeldap.exception.TechnicalException;
import fr.cfdt.gasel.groupeldap.mapper.RequestMapper;
import fr.cfdt.gasel.groupeldap.service.CsvWriterService;
import fr.cfdt.gasel.groupeldap.service.GroupService;
import fr.cfdt.gasel.groupeldap.service.PersonService;
import fr.cfdt.gasel.groupeldap.util.PagingUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author SZaoui
 */
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);

    @Autowired
    PersonService personService;

    @Autowired
    GroupService groupeService;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    CsvWriterService csvWriterService;

    @Autowired
    RequestMapper requestMapper;

    @Autowired
    PagingUtil pagingUtil;

    @GetMapping("/members/{query}/{page}/{size}/{orderDir}/{orderCol}")
    @ApiOperation(value = "Récupérer la liste des membres en exécutant la requête")
    public Page<PersonneDto> getMembersByQuery(@PathVariable String query , @PathVariable int page , @PathVariable int size , @PathVariable String orderDir,@PathVariable String orderCol) throws TechnicalException {
        LOGGER.info("Start Get getMembersByQuery ");
        Page<PersonneDto> members = personService.getMembers(query , page , size , orderDir , orderCol);
        LOGGER.info("End Get getMembersByQuery ");
        return members;
    }


    @GetMapping("/Rechercher/{query}/{criteria}/{page}/{size}/{orderDir}/{orderCol}")
    @ApiOperation(value = "Rechercher par nom, nom de naissance ou npa dans la liste des membres")
    public Page<PersonneDto> rechercherMembers(@PathVariable String query ,@PathVariable String criteria, @PathVariable int page,@PathVariable int size , @PathVariable String orderDir,@PathVariable String orderCol) throws TechnicalException {
        LOGGER.info("Start rechercherMembers");
        List<PersonneDto> members = personService.getMembers(query , null , null , orderDir , orderCol).getContent();
        List<PersonneDto> tmp = members.stream().filter(p -> (p.getNpa() != null && p.getNpa().startsWith(criteria)) || (p.getNom() != null && p.getNom().startsWith(criteria)) || (p.getNomNaissance() != null && p.getNomNaissance().startsWith(criteria))).collect(Collectors.toList());
        pagingUtil.sortColumn(tmp ,orderDir, orderCol , "personne");
        List<PersonneDto> pageContent = pagingUtil.getPage(tmp, page, size);
        LOGGER.info("End rechercherMembers ");
        return new PageImpl<>(pageContent, PageRequest.of(page-1, size) , tmp.size());
    }

}
