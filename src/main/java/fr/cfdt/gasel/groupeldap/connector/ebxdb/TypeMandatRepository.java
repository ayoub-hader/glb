package fr.cfdt.gasel.groupeldap.connector.ebxdb;

import fr.cfdt.gasel.groupeldap.model.TypeMandat;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author SZaoui
 */
@Repository
public interface TypeMandatRepository extends JpaRepository<TypeMandat, Long> {

    @Cacheable(value = "typeMandats")
    List<TypeMandat> findAll();
}
