package fr.cfdt.gasel.groupeldap.service;

import fr.cfdt.gasel.groupeldap.connector.ebxdb.StructureRepository;
import fr.cfdt.gasel.groupeldap.dto.StructureDto;
import fr.cfdt.gasel.groupeldap.mapper.PersonMapper;
import fr.cfdt.gasel.groupeldap.model.Structure;
import fr.cfdt.gasel.groupeldap.util.PagingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StructureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StructureService.class);

    @Autowired
    StructureRepository structureRepository;

    @Autowired
    PersonMapper structureMapper;

    @Autowired
    PagingUtil pagingUtil;

    public List<StructureDto> getStructuresByType(String  type , Integer page , Integer size) {
        List<StructureDto> result = null;
        LOGGER.info("Start service getStructuresByType ");
        List<Structure> structuresEbx = structureRepository.findByType(type);
        LOGGER.info("End service getMembers ");
        if(page != null && size != null){
            result = pagingUtil.getPage(structuresEbx , page , size);
        }
        return result;
    }
}
