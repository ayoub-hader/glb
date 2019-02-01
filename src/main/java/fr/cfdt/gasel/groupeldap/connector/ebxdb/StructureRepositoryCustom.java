package fr.cfdt.gasel.groupeldap.connector.ebxdb;

import fr.cfdt.gasel.groupeldap.model.Personne;
import fr.cfdt.gasel.groupeldap.model.Structure;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 */
public interface StructureRepositoryCustom {
    List<Integer>  structureByQuery(String ids);

    List<Integer> findPerimetreStructureByType(String ids ,String type);

    List<Structure> lienAdminByIdsAndType(String ids , String type);
}
