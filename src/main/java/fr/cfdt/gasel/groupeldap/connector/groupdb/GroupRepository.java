package fr.cfdt.gasel.groupeldap.connector.groupdb;

import fr.cfdt.gasel.groupeldap.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Athor SZaoui
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findAll();
    Group save(Group group);
}
