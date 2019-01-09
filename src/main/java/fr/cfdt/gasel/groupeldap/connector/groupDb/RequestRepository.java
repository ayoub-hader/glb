package fr.cfdt.gasel.groupeldap.connector.groupDb;

import fr.cfdt.gasel.groupeldap.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author Zaoui Soukaina
 */
public interface RequestRepository extends JpaRepository<Request, Long> {

    Request save(Request request);
    List<Request> findAll();
}
