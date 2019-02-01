package fr.cfdt.gasel.groupeldap.connector.ebxdb;

import fr.cfdt.gasel.groupeldap.model.TypeResponsabilite;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author SZaoui
 */
@Repository
public interface TypeResponsabiliteRepository extends JpaRepository<TypeResponsabilite, Long> {

    @Cacheable(value = "typeResponsabilites")
    List<TypeResponsabilite> findAll();
}
