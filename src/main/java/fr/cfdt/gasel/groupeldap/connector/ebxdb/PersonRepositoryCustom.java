package fr.cfdt.gasel.groupeldap.connector.ebxdb;

import fr.cfdt.gasel.groupeldap.model.Personne;

import java.util.List;

public interface PersonRepositoryCustom {
    List<Personne> personsByQuery(String query);
}
