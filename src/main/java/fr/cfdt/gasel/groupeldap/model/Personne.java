package fr.cfdt.gasel.groupeldap.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author SZaoui
 */

@Getter
@Setter
@Entity
@Table(name = "EBX_REP_PERSONNE")
public class Personne implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "NOM")
    String nom;

    @ManyToOne
    @JoinColumn(name = "SYNDICAT_", nullable = false)
    Structure syndicat;

    @Column(name = "ADHERENT_NPA")
    String npa;

    @Column(name ="NOMNAISSANCE")
    String nomNaissance;

    @Column(name ="PRENOM")
    String prenom;
}
