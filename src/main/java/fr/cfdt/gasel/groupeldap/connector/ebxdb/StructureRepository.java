package fr.cfdt.gasel.groupeldap.connector.ebxdb;

import fr.cfdt.gasel.groupeldap.model.Structure;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author SZaoui
 */
@Repository
public interface StructureRepository extends JpaRepository<Structure , Long> {

    @Cacheable(value="structuresByType")
    List<Structure> findByType(String type);


    @Query(value="SELECT * FROM EBX_REP_STRUCTURE WHERE ID IN ?1", nativeQuery = true)
    List<Structure> findStructuresByIds(@Param("ids") List<String> ids);

}
