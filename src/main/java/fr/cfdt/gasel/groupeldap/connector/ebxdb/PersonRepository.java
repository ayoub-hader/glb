package fr.cfdt.gasel.groupeldap.connector.ebxdb;

import fr.cfdt.gasel.groupeldap.model.Personne;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author SZaoui
 */
@Repository
public interface PersonRepository extends PagingAndSortingRepository<Personne , Long> {
    Optional<Personne> findById(Long id);
}
