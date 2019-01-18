package fr.cfdt.gasel.groupeldap.resource;

import fr.cfdt.gasel.groupeldap.dto.StructureDto;
import fr.cfdt.gasel.groupeldap.service.StructureService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Author SZaoui
 */
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/structures")
public class StructureResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(StructureResource.class);

    @Autowired
    StructureService structureService;

    @GetMapping("/{type}/{page}/{size}")
    @ApiOperation(value = "Récupérer la liste des structure par type")
    public Page<StructureDto> getStructures(@PathVariable String type , @PathVariable int page , @PathVariable int size) {
        LOGGER.info("Start Get getStructures ");
        Page<StructureDto> structures = structureService.getStructuresByType(type , page , size);
        LOGGER.info("End Get getStructures ");
        return structures;
    }

    @GetMapping("/{ids}")
    @ApiOperation(value = "Récupérer la liste des structure par type et ids")
    public List<StructureDto> getStructuresbyIds(@PathVariable String ids) {
        LOGGER.info("Start Get getStructuresbyIds ");
        List<String> listIds = Arrays.asList(ids.split(","));
        List<StructureDto> structures = structureService.getStructuresByIds(listIds);
        LOGGER.info("End Get getStructuresbyIds ");
        return structures;
    }
}
