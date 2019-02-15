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

    List<Structure> findByTypeAndMatriculeContainsOrMatriculeContains(String type , String matriculeUpperCase , String matriculeLowerCase);

    @Query(value="SELECT * FROM EBX_REP_STRUCTURE WHERE ID IN ?1", nativeQuery = true)
    List<Structure> findStructuresByIds(@Param("ids") List<String> ids);

    @Query(value="SELECT * FROM EBX_REP_STRUCTURE WHERE MATRICULE IN ?1", nativeQuery = true)
    List<Structure> findStructuresByMatricules(@Param("matricules") List<String> matricules);

    @Query(value="SELECT sectionImpactePar_ FROM EBX_REP_STRUCTURE_sectionImpactePar WHERE ID IN ?1", nativeQuery = true)
    List<Integer> findSectionImpactePar(@Param("ids") List<String> ids);

    @Query(value="SELECT syndicatImpactePar_ FROM EBX_REP_STRUCTURE_syndicatImpactePar WHERE ID IN ?1", nativeQuery = true)
    List<Integer> findSyndicatImpactePar(@Param("ids") List<String> ids);

    @Query(value="SELECT brancheImpactePar_ FROM EBX_REP_STRUCTURE_brancheImpactePar WHERE ID IN ?1", nativeQuery = true)
    List<Integer> findBrancheImpactePar(@Param("ids") List<String> ids);

    @Query(value="SELECT groupeImpactePar_ FROM EBX_REP_STRUCTURE_groupeImpactePar WHERE ID IN ?1", nativeQuery = true)
    List<Integer> findGroupeImpactePar(@Param("ids") List<String> ids);

    @Query(value="SELECT codeGeographiqueImpacte_ FROM EBX_REP_STRUCTURE_codeGeographiqueImpacte WHERE ID IN ?1", nativeQuery = true)
    List<Integer> findCodeGeographiqueImpacte(@Param("ids") List<String> ids);

    @Query(value="SELECT departementImpacte_ FROM EBX_REP_STRUCTURE_departementImpacte WHERE ID IN ?1", nativeQuery = true)
    List<Integer> findDepartementImpacte(@Param("ids") List<String> ids);
}
