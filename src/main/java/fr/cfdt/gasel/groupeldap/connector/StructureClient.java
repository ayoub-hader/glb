package fr.cfdt.gasel.groupeldap.connector;

import fr.cfdt.gasel.groupeldap.enumeration.BranchEnum;
import fr.cfdt.gasel.groupeldap.enumeration.InstanceEnum;
import fr.cfdt.gasel.schema.v0.ebx.structure.SelectStructureRequestType;
import fr.cfdt.gasel.schema.v0.ebx.structure.SelectStructureResponseType;
import fr.cfdt.gasel.schema.v0.ebx.structure.StructureType;
import fr.cfdt.gasel.service.ebx.structure.v0.StandardException;
import fr.cfdt.gasel.service.ebx.structure.v0.StructureEBXInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmlsoap.schemas.ws._2002._04.secext.Security;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Zaoui Soukaina
 */
@Component
public class StructureClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StructureClient.class);

    @Autowired
    private StructureEBXInterface structureEBXInterface;

    public List<StructureType.Gasel.Structure> findStructureByPredicat(String predicate, String viewId, Security security, SelectStructureRequestType.Pagination pagination, String branch) throws StandardException {
        SelectStructureResponseType response = null;
        List<StructureType.Gasel.Structure> result = new ArrayList<>();
        SelectStructureRequestType recherche = getSelectStructureRequestType();
        recherche.setPredicate(predicate);
        recherche.setPagination(pagination);
        recherche.setBranch(branch);
        recherche.setViewId(viewId);
        if (structureEBXInterface != null) {
            response = structureEBXInterface.selectStructureOperation(recherche, security);
            if(response != null && response.getData().getGasel() != null && response.getData().getGasel().getStructure() != null){
                result.addAll(response.getData().getGasel().getStructure());
            }
        }

        return result;
    }

    private SelectStructureRequestType getSelectStructureRequestType() {
        SelectStructureRequestType recherche = new SelectStructureRequestType();
        recherche.setBranch(BranchEnum.GASEL.getCode());
        recherche.setInstance(InstanceEnum.STRUCTURE.getCode());
        return recherche;
    }
}
