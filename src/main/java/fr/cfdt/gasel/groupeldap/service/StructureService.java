package fr.cfdt.gasel.groupeldap.service;

import fr.cfdt.gasel.groupeldap.connector.ebxdb.StructureRepository;
import fr.cfdt.gasel.groupeldap.dto.StructureDto;
import fr.cfdt.gasel.groupeldap.mapper.StructureMapper;
import fr.cfdt.gasel.groupeldap.model.Structure;
import fr.cfdt.gasel.groupeldap.util.PagingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StructureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StructureService.class);

    @Autowired
    StructureRepository structureRepository;

    @Autowired
    StructureMapper structureMapper;

    @Autowired
    PagingUtil pagingUtil;

    public Page<StructureDto> getStructuresByType(String  type , Integer page , Integer size) {
        LOGGER.info("Start service getStructuresByType ");
        List<Structure> structuresEbx = structureRepository.findByType(type);
        List<Structure> pageContent;
        if(page != null && size != null){
            pageContent = pagingUtil.getPage(structuresEbx , page , size);
        } else {
            pageContent = structuresEbx;
        }
        LOGGER.info("End service getStructuresByType ");
        return new PageImpl<>(structureMapper.listStructureModelToDto(pageContent), PageRequest.of(page-1, size) , structuresEbx.size());
    }
}
