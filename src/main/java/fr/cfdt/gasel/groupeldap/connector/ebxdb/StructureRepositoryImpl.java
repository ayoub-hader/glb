package fr.cfdt.gasel.groupeldap.connector.ebxdb;

import fr.cfdt.gasel.groupeldap.model.Structure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@PropertySource("scripts/structureScripts.yml")
public class StructureRepositoryImpl implements StructureRepositoryCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(StructureRepositoryImpl.class);

    @PersistenceContext(unitName = "secondEntityManagerFactory")
    EntityManager entityManager;

    @Value("${structureFilleQuery}")
    private String structureFilleQuery;

    @Value("${lienAdministratifQuery}")
    private String lienAdministratifQuery;

    @Value("${structureFilleQueryByType}")
    private String structureFilleQueryByType;

    @Override
    public List<Integer> structureByQuery(String ids) {
        LOGGER.info("start query structure fille");
        Query q = entityManager.createNativeQuery(structureFilleQuery.replace("?1" , ids));
        LOGGER.info("end query structure fille");
        return q.getResultList();
    }

    @Override
    public List<Integer> findPerimetreStructureByType(String ids ,String type) {
        LOGGER.info("start query structure fille by Type");
        structureFilleQueryByType = structureFilleQueryByType.replace("?0" , ids);
        structureFilleQueryByType = structureFilleQueryByType.replace("?1" , type);
        Query q = entityManager.createNativeQuery(structureFilleQueryByType);
        LOGGER.info("end query structure fille by Type");
        return q.getResultList();
    }

    @Override
    public List<Structure> lienAdminByIdsAndType(String ids, String type) {
        LOGGER.info("start query lien administratif");
        lienAdministratifQuery = lienAdministratifQuery.replace("?2" , type);
        Query q = entityManager.createNativeQuery(lienAdministratifQuery.replace("?1" , ids), Structure.class);
        LOGGER.info("end query lien administratif");
        return q.getResultList();
    }
}
