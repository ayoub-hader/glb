package fr.cfdt.gasel.groupeldap.batch;

import fr.cfdt.gasel.groupeldap.exception.TechnicalException;
import fr.cfdt.gasel.groupeldap.model.Group;
import fr.cfdt.gasel.groupeldap.service.CacheService;
import fr.cfdt.gasel.groupeldap.service.GroupService;
import fr.cfdt.gasel.ldap.Exception.UnableToFoundGaselLdapUserWithTheGivenInformationsException;
import fr.cfdt.gasel.ldap.GaselLDAPService;
import fr.cfdt.gasel.ldap.dto.GaselGroupeLDAPEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Author SZaoui
 */

public class UpdateLdapBatch implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateLdapBatch.class);

    @Autowired
    GroupService groupService;

    @Autowired
    CacheService cacheService;

    @Autowired
    GaselLDAPService gaselLDAPService;

    @PersistenceContext(unitName = "firstEntityManagerFactory")
    EntityManager entityManager;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws TechnicalException {
        updateLdap();
        return RepeatStatus.FINISHED;
    }

    @Transactional
    public void updateLdap() throws TechnicalException{
        LOGGER.info("start batch update group members Ldap");
        cacheService.evictCacheByValue("membersByQuery");
        // recuperer la liste des groupes depuis la base
        //test commit
        LOGGER.info("Batch update group members Ldap : recuperer la liste des groupes depuis la base");
        List<Group> groups = entityManager.createNativeQuery("select * from ldap_group" , Group.class).getResultList();
        //commencer le traitement pour chaque groupe
        for(Group group : groups){
            if(group.getRequest() != null){
                GaselGroupeLDAPEntry groupLdap = null;
                // executer la requete pour recuperer la liste des membre depuis la base repliquee
                LOGGER.info("Batch update group members Ldap : executer la requete pour le groupe ID = {}", group.getIdGroup());
                List<String> baseMembers = groupService.getLdapMembers(group.getRequest().getRequest());
                LOGGER.info("Batch update group members Ldap : recuperer la liste des membres depuis LDAP pour le groupe ID = {}", group.getIdGroup());
                try {
                    groupLdap = gaselLDAPService.getGroupeByCn(String.valueOf(group.getIdGroup()));
                } catch (UnableToFoundGaselLdapUserWithTheGivenInformationsException e){
                    LOGGER.info(e.getMessage());
                }
                if(groupLdap != null && !baseMembers.equals(groupLdap.getMember())){
                    if(baseMembers == null || baseMembers.size() <= 0){
                        baseMembers = new ArrayList<>();
                        baseMembers.add("");
                    }
                    boolean resultBatch = gaselLDAPService.updateLdapGroupMembers(baseMembers, String.valueOf(group.getIdGroup()));
                    //mise a jour du nombre des membre dans la table groupe ldap
                    if(resultBatch){
                        entityManager.joinTransaction();
                        entityManager.createNativeQuery("UPDATE ldap_group SET NOMBRE_MEMBRES = "+groupLdap.getMember().size()+" WHERE id_ldap_group = '"+group.getIdGroup()+"'", Group.class).executeUpdate();
                    }
                }
            }
        }
        LOGGER.info("end batch update group members Ldap");
    }

}
