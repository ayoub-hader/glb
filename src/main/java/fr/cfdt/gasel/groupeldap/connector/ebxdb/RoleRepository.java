package fr.cfdt.gasel.groupeldap.connector.ebxdb;

import fr.cfdt.gasel.groupeldap.model.Role;
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
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Cacheable(value="roles")
    List<Role> findAll();
}
