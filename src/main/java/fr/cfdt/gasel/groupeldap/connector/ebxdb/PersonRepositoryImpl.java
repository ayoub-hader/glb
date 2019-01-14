package fr.cfdt.gasel.groupeldap.connector.ebxdb;

import fr.cfdt.gasel.groupeldap.model.Personne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepositoryCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    @PersistenceContext(unitName = "secondEntityManagerFactory")
    EntityManager entityManager;

    @Override
    @Cacheable(value="membersByQuery")
    public List<Personne> personsByQuery(String query) {
        LOGGER.debug("Start PersonsByQuery query = {}", query);
        Query q = entityManager.createNativeQuery(query, Personne.class);
        LOGGER.debug("End PersonsByQuery query = {}", query);
        return q.getResultList();
    }
}
