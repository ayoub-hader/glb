package fr.cfdt.gasel.groupeldap.resource;


import fr.cfdt.gasel.groupeldap.connector.groupdb.RequestRepository;
import fr.cfdt.gasel.groupeldap.dto.PersonneDto;
import fr.cfdt.gasel.groupeldap.dto.RequestDto;
import fr.cfdt.gasel.groupeldap.exception.TechnicalException;
import fr.cfdt.gasel.groupeldap.mapper.RequestMapper;
import fr.cfdt.gasel.groupeldap.model.Request;
import fr.cfdt.gasel.groupeldap.model.Structure;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

//    @GetMapping("/requests")
//    @ApiOperation(value = "Récupérer la liste des requettes")
//    public List<RequestDto> getAllRequests(){
//        LOGGER.info("Start Get All requests List ");
//
//        List<Request> reqTmp = requestRepository.findAll();
//        List<RequestDto> req = requestMapper.mapListRequestModeltodto(reqTmp);
//        LOGGER.info("End Get All persons List ");
//        return req;
//    }

    @GetMapping("/members/{query}/{page}/{size}")
    @ApiOperation(value = "Récupérer la liste des membres en exécutant la requête")
    public Page<PersonneDto> getMembersByQuery(@PathVariable String query , @PathVariable int page , @PathVariable int size) throws TechnicalException {
        LOGGER.info("Start Get getMembersByQuery ");
        Page<PersonneDto> members = personService.getMembers(query , page , size);
        LOGGER.info("End Get getMembersByQuery ");
        return members;
    }

//    @GetMapping("/Export/{query}")
//    @ApiOperation(value = "Exporter la liste des membres")
//    public byte[] exportMembers(@PathVariable String query , HttpServletResponse response) throws TechnicalException {
//        LOGGER.info("Start exportMembers");
//        List<PersonneDto> members = personService.getMembers(query , null , null).getContent();
//        List<String[]> lines = new ArrayList<>();
//        String[] header = {"Syndicat" , "Nom" , "Npa"};
//        lines.add(header);
//        for(PersonneDto member : members){
//            lines.add(new String[]{member.getSyndicat() != null ? member.getSyndicat().getMatricule() : "", member.getNom() + " " + member.getPrenom(), member.getNpa()});
//        }
//        byte[] result = null;
//        try {
//            result = csvWriterService.generateCsv(lines , response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        LOGGER.info("End exportMembers ");
//        return result;
//    }
//
//    @GetMapping("/Rechercher/{query}/{criteria}/{page}/{size}")
//    @ApiOperation(value = "Rechercher par nom, nom de naissance ou npa dans la liste des membres")
//    public Page<PersonneDto> rechercherMembers(@PathVariable String query ,@PathVariable String criteria, @PathVariable int page,@PathVariable int size) throws TechnicalException {
//        LOGGER.info("Start rechercherMembers");
//        List<PersonneDto> members = personService.getMembers(query , null , null).getContent();
//        List<PersonneDto> tmp = members.stream().filter(p -> (p.getNpa() != null && p.getNpa().startsWith(criteria)) || (p.getNom() != null && p.getNom().startsWith(criteria)) || (p.getNomNaissance() != null && p.getNomNaissance().startsWith(criteria))).collect(Collectors.toList());
//        List<PersonneDto> pageContent = pagingUtil.getPage(tmp, page, size);
//        LOGGER.info("End rechercherMembers ");
//        return new PageImpl<>(pageContent, PageRequest.of(page-1, size) , tmp.size());
//    }

}
