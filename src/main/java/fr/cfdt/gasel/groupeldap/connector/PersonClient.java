package fr.cfdt.gasel.groupeldap.connector;

import fr.cfdt.gasel.groupeldap.enumeration.BranchEnum;
import fr.cfdt.gasel.groupeldap.enumeration.InstanceEnum;
import fr.cfdt.gasel.schema.v0.ebx.personne.PersonneType;
import fr.cfdt.gasel.schema.v0.ebx.personne.SelectPersonneRequestType;
import fr.cfdt.gasel.schema.v0.ebx.personne.SelectPersonneRequestType.Pagination;
import fr.cfdt.gasel.schema.v0.ebx.personne.SelectPersonneResponseType;
import fr.cfdt.gasel.service.ebx.personne.v0.PersonneEBXInterface;
import fr.cfdt.gasel.service.ebx.personne.v0.StandardException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.xmlsoap.schemas.ws._2002._04.secext.Security;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Zaoui Soukaina
 */
@Component
public class PersonClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonClient.class);

    @Autowired
    private PersonneEBXInterface personneEBXInterface;

    /**
     *
     * @param predicate
     * @param brancheName
     * @param viewId
     * @param security
     * @param pagination
     * @return
     */
    public List<PersonneType.Gasel.Personne> findPersonneByPredicat(String predicate, String brancheName, String viewId, Security security, Pagination pagination) throws StandardException {
        LOGGER.info("Start findPersonneByPredicat");
        SelectPersonneResponseType response = null;
        List<PersonneType.Gasel.Personne> result = new ArrayList<>(0);
        SelectPersonneRequestType recherche = getSelectPersonneRequestType();
        recherche.setPredicate(predicate);
        recherche.setBranch(brancheName);
        recherche.setInstance(InstanceEnum.PERSONNE.getCode());
        recherche.setViewId(viewId);
        recherche.setPagination(pagination);
        if (personneEBXInterface != null) {
            response = selectPersonneOperation(recherche, security);
            if(response != null && response.getData().getGasel() != null && response.getData().getGasel().getPersonne() != null){
                result.addAll(response.getData().getGasel().getPersonne());
            }
        }
        LOGGER.info("End findPersonneByPredicat");
        return result;
    }

    /**
     *
     * @param request
     * @param security
     * @return
     * @throws StandardException
     */
    public SelectPersonneResponseType selectPersonneOperation(SelectPersonneRequestType request, Security security) throws StandardException {
        LOGGER.debug("selectPersonneOperation predicat : " + request.getPredicate());

        StopWatch time = new StopWatch();
        time.start();
        SelectPersonneResponseType response = personneEBXInterface.selectPersonneOperation(request, security);
        time.stop();

        LOGGER.debug("Temps consomme par l'operation selectPersonneOperation : " + time.getTotalTimeSeconds());
        return response;
    }

    /**
     *
     * @return
     */
    private SelectPersonneRequestType getSelectPersonneRequestType() {
        SelectPersonneRequestType recherche = new SelectPersonneRequestType();
        recherche.setBranch(BranchEnum.GASEL.getCode());
        recherche.setInstance(InstanceEnum.PERSONNE.getCode());
        return recherche;
    }

}
