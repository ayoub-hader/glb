package fr.cfdt.gasel.groupeldap.connector.ebxdb;

import fr.cfdt.gasel.groupeldap.model.Structure;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author SZaoui
 */
@Repository
public interface StructureRepository extends JpaRepository<Structure , Long> {

    @Cacheable(value="structuresByType")
    List<Structure> findByType(String type);

    List<Structure> findAllById(List<String> ids);
}
