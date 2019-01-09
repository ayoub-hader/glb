package fr.cfdt.gasel.groupeldap.connector.ebxDb;

import fr.cfdt.gasel.groupeldap.model.Personne;

import java.util.List;

public interface PersonRepositoryCustom {
    List<Personne> PersonsByQuery(String query);
}
