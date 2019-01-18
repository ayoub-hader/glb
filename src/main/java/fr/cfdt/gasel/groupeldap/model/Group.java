package fr.cfdt.gasel.groupeldap.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Author SZaoui
 */
@Getter
@Setter
@Entity
@Table(name = "LDAP_GROUP")
public class Group {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_LDAP_GROUP")
    Long idGroup;

    @Column(name = "NOM_GROUPE")
    String name;

    @Column(name = "DESCRIPTION_GROUPE")
    String description;

    @Column(name = "NOMBRE_MEMBRES")
    String membersNumber;

    @Column(name = "STRUCTURES")
    String structures;

    @Column(name = "DENOMINATION_RESPONSABILITE")
    String denominationsResp;

    @Column(name = "DENOMINATION_MANDAT")
    String denominationsMandat;

    @Column(name = "RESPONSABILITE_INSTANCE")
    String respInstances;

    @Column(name = "ORGANISME_INSTANCE")
    String organismeInstances;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_REQUEST")
    private Request request;
}